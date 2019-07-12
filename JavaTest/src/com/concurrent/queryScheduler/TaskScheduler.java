package com.concurrent.queryScheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 控制{@link BaseDataOperation}数据操作的Scheduler.
 * 具备DataOperation流程控制和回调控制。
 *
 * 学习XulQueryScheduler流程控制时，抽离其核心流程
 */
public class TaskScheduler implements IExecutable {

    //Scheduler状态码
    private static final int ST_UNKNOWN = -1;
	private static final int ST_RUNNING = -2;
	private static final int ST_SUCCESS = 0;
	private static final int ST_ERROR = 1;
	private static final int ST_FAILED = 2;

	//数据回调状态码
	public static final int ON_SUCCESS = 0;
	public static final int ON_ERROR = 1;
	public static final int ON_FINISH = 2;
    private static final int MAX_EVENT_NUM = ON_FINISH + 1;

	//初始状态值
	private int _status = ST_UNKNOWN;

	//挂载任务数据回调Map
	HashMap<PendingDataCallback, Boolean> _taskMap = new HashMap<>();
    //操作行为列表
	private ArrayList<ScheduledOperation> _scheduledOperation = new ArrayList<>();
    //数据回调
	private BaseDataCallback _callback;
    //等待状态的任务列表
	private ArrayList<IExecutable>[] _pendingSchedulers;

	//并行执行任务数 用于BarrierExecutable将Barrier open
	private volatile AtomicInteger _parallelExecutableCount = new AtomicInteger(0);

    /**
     * 执行操作
     * 包含数据操作和数据回调
     */
	static class ScheduledOperation {
		BaseDataOperation operation;
		BaseDataCallback dataCallback;

		public ScheduledOperation(BaseDataOperation operation, BaseDataCallback dataCallback) {
			this.operation = operation;
			this.dataCallback = dataCallback;
		}
	}

	 /**
     * 创建有数据回调的Scheduler
     * @return QueryScheduler对象
     */
    static public TaskScheduler create(BaseDataCallback callback) {
		return new TaskScheduler(callback);
	}

    /**
     * 创建无数据回调的Scheduler
     * @return QueryScheduler对象
     */
	static public TaskScheduler create() {
		return new TaskScheduler(null);
	}

    public TaskScheduler(BaseDataCallback _callback) {
        this._callback = _callback;
    }

    @Override
    public boolean exec(String reason) {
        return exec();
    }

    public boolean exec() {
		if (_scheduledOperation == null || _scheduledOperation.isEmpty()) {
		    //操作列表是否为空
			_status = ST_SUCCESS;
			if (_callback != null) {
				_callback.onResult(null, _status, null);
			}
			return true;
		}
		//操作列表非空
		_status = ST_RUNNING;
		for (ScheduledOperation operation : _scheduledOperation) {
			try {
				if (operation.operation.exec(operation.dataCallback)) {
				    //数据操作对象携带callback执行任务
					continue;
				}
			} catch (DataException e) {
				System.out.println(e);
			}
			dropAnchorOnException();
			return false;
		}
		return true;
	}

	private void dropAnchorOnException(){
		if (_status == ST_RUNNING) {
			_status = ST_ERROR;
			_notifyError();
			//激活等待的任务Scheduler
			_activatePendingSchedulers(ON_ERROR, null);
		}
	}

    /**
     * 将两个scheduler对联进行任务执行关联
     * @param scheduler  后置scheduler
     * @param condition  前置scheduler处于什么条件下，后置scheduler将内部并行任务计数器减一。
	 *                   并行计数器为0时，执行后置scheduler{@code exec()}
     * @return 当前任务Scheduler
     */
	public TaskScheduler schedule(TaskScheduler scheduler, int condition) {
		if (condition >= MAX_EVENT_NUM) { return this; }
		return this.schedule(scheduler.obtainBarrierExecutable(), condition);
	}

    /**
     * 添加任务进入指定条件下的pending列表
     * @param execTarget IExecutable对象
     * @param condition  pending任务对应的触发状态
     * @return  QueryScheduler对象
     */
	public TaskScheduler schedule(IExecutable execTarget, int condition) {
		if (condition >= MAX_EVENT_NUM) { return this; }

		if (_pendingSchedulers == null) {
		    //初始化pending队列
			_pendingSchedulers = new ArrayList[MAX_EVENT_NUM];
		}
        //查找指定条件的pending任务列表
		ArrayList<IExecutable> pendingList = _pendingSchedulers[condition];
		if (pendingList == null) {
			pendingList = _pendingSchedulers[condition] = new ArrayList<>();
		}
		//将Executable对象放入当前状态槽的列表中
		pendingList.add(execTarget);
		return this;
	}

	/**
	 * 在orgCallBack外包裹一层callBack，在onResult\onError时，做额外事务处理。
	 * @param orgCallback 初始callbacl
	 * @return PendingDataCallback对象
	 */
	public BaseDataCallback wrap(final BaseDataCallback orgCallback){
		PendingDataCallback pendingDataCallback = new PendingDataCallback(){
			@Override
			public boolean schedulePendingExec(BaseDataOperation operation, BaseDataCallback dataCallback) {
				//pending任务执行的时候， 将pending操作添加到操作行为列表
				_scheduledOperation.add(new ScheduledOperation(operation,dataCallback));
				if (_status == ST_RUNNING) {
					try {
						return operation.exec(this);
					} catch (DataException e) {
						e.printStackTrace();
					}
					dropAnchorOnException();
					return false;
				}
				return true;
			}

			@Override
			public void onResult(String stub, int code, Object data) {
				if (_taskMap == null) {
					return;
				}
				_taskMap.remove(this);
				if (orgCallback != null) {
					orgCallback.onResult(stub, code, data);
				}
				onClauseSuccess(stub);
			}

			@Override
			public void onError(String taskName, int code) {
				if (_taskMap == null) {
					return;
				}
				_taskMap.remove(this);
				if (orgCallback != null) {
					orgCallback.onError(taskName, code);
				}
				onClauseError(taskName);
			}

		};
		_taskMap.put(pendingDataCallback, Boolean.FALSE);
		return pendingDataCallback;
	}

	private void onClauseSuccess(String stub) {
		//pending任务的回调为空的情况下
		if (_taskMap.isEmpty()) {
			if (_status == ST_RUNNING) {
				//此次任务执行完毕
				_status = ST_SUCCESS;
				if (_callback != null) {
					_callback.onResult(null, 0, null);
				}
				//激活在
				_activatePendingSchedulers(ON_SUCCESS, stub);
			} else if (_status == ST_ERROR) {
				_status = ST_FAILED;
			}
			_activatePendingSchedulers(ON_FINISH, stub);
		}
	}

	private void onClauseError(String stub) {
		if (_status == ST_RUNNING) {
			_status = ST_ERROR;
			_notifyError();
			_activatePendingSchedulers(ON_ERROR, stub);
		}

		if (_status == ST_ERROR && _taskMap.isEmpty()) {
			_status = ST_FAILED;
			_activatePendingSchedulers(ON_FINISH, stub);
		}
	}



    /**
     * 创建一个Exectable的Barrier
     * @return IExecutable对象
     */
	public IExecutable obtainBarrierExecutable() {
	    //并行的执行任务 +1
		_parallelExecutableCount.incrementAndGet();
		return new BarrierExecutable(this);
	}

	private static class BarrierExecutable implements IExecutable {
		TaskScheduler _target;

		public BarrierExecutable(TaskScheduler target) {
			_target = target;
		}

		@Override
		public boolean exec(String task) {
			TaskScheduler target = _target;
			if (target == null) {
			    //若无目标执行者，直接执行结束
				return false;
			}

			_target = null;
			boolean execNow = (target._parallelExecutableCount.decrementAndGet() == 0);
			return execNow && target.exec(task);
		}
	}

	private void _notifyError() {
		if (_callback != null) {
			_callback.onError(null, 0);
			_callback = null;
		}
	}

	private void _activatePendingSchedulers(int event, String reson) {
		if (_pendingSchedulers == null) {
			return;
		}

		final ArrayList<IExecutable> pendingScheduler = _pendingSchedulers[event];
		if (pendingScheduler == null) {
			return;
		}
        for (IExecutable executor : pendingScheduler) {
            executor.exec(reson);
        }
	}
}
