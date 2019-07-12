package com.concurrent.queryScheduler;

/**
 * TaskScheduler验证
 * 输出记录为：
 * >>> obj-A doSomeThing.....
 * >>> exec opration of task-a！
 * >>> obj-B doSomeThing.....
 * >>> exec opration of task-b！
 * >>> Exec operation of A ....!!
 * >>> task-a is onResult ！
 * >>> Exec operation of B .... !!!
 * >>> task-b is onResult ！！
 * >>> _AllScheduler onResult !!!
 */
public class QueryTest {
    public static void main(String[] args) {
        TaskScheduler mObjAScheduler = TaskScheduler.create();
        TaskScheduler mObjBScheduler = TaskScheduler.create();
        TaskScheduler _AllScheduler = TaskScheduler.create(new BaseDataCallback() {
            @Override
            public void onResult(String taskName, int code, Object data) {
                super.onResult(taskName, code, data);
                System.out.println("_AllScheduler onResult !!!");
            }

            @Override
            public void onError(String taskName, int code) {
                super.onError(taskName, code);
                System.out.println("_AllScheduler onError. reason:" + taskName);
            }
        });
        mObjAScheduler.schedule(_AllScheduler, TaskScheduler.ON_FINISH);
        mObjBScheduler.schedule(_AllScheduler, TaskScheduler.ON_FINISH);

        BaseDataCallback callback_a = mObjAScheduler.wrap(new BaseDataCallback() {
            @Override
            public void onResult(String taskName, int code, Object data) {
                super.onResult(taskName, code, data);
                System.out.println(taskName + " is onResult ！");
            }
        });

        BaseDataCallback callback_b = mObjBScheduler.wrap(new BaseDataCallback() {
            @Override
            public void onResult(String taskName, int code, Object data) {
                super.onResult(taskName, code, data);
                System.out.println(taskName + " is onResult ！！");
            }
        });

        //具体操作对象A
        BaseDataOperation operation_A = new BaseDataOperation() {
            @Override
            public boolean exec(BaseDataCallback callback){
                System.out.println("Exec operation of A ....!!");
                try {
                    Thread.sleep(1000);
                    callback.onResult("task-a",0,null);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            }
        };

        //具体操作对象B
        BaseDataOperation operation_B = new BaseDataOperation() {
            @Override
            public boolean exec(BaseDataCallback callback){
                System.out.println("Exec operation of B .... !!!");
                  try {
                    Thread.sleep(500);
                    callback.onResult("task-b",0,null);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            }
        };
        sendTaskA(callback_a, operation_A);
        sendTaskB(callback_b, operation_B);
        mObjAScheduler.exec();
        mObjBScheduler.exec();
    }

    private static void sendTaskB(BaseDataCallback callback, BaseDataOperation operation) {
        try {
            System.out.println("obj-B doSomeThing.....");
            if (callback instanceof PendingDataCallback) {
                ((PendingDataCallback) callback).schedulePendingExec(operation, callback);
            } else {
                operation.exec(callback);
            }
            System.out.println("exec opration of task-b！");
        } catch (DataException e) {
            e.printStackTrace();
        }
    }

    private static void sendTaskA(BaseDataCallback callback, BaseDataOperation operation) {
        try {
            System.out.println("obj-A doSomeThing.....");
            Thread.sleep(500);
            if (callback instanceof PendingDataCallback) {
                ((PendingDataCallback) callback).schedulePendingExec(operation, callback);
            } else {
                operation.exec(callback);
            }
            System.out.println("exec opration of task-a！");
        } catch (InterruptedException | DataException e) {
            e.printStackTrace();
        }

    }
}
