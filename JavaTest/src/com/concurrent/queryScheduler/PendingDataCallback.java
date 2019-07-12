package com.concurrent.queryScheduler;

/**
 * 可以Pending的数据回调类
 */
public class PendingDataCallback extends BaseDataCallback {
	/**
	 * 插入需要执行的pending任务
	 * @param operation
	 * @param dataCallback
	 * @return 插入成功
	 */
   public boolean schedulePendingExec(BaseDataOperation operation, BaseDataCallback dataCallback) {
		return false;
	}
}
