package com.concurrent.queryScheduler;

public interface IExecutable {
    /**
     * 任务执行
     * @param stub  类型可以为与业务相关的类，此处暂用String代替
     * @return 是否执行完毕(不一定成功)
     */
    boolean exec(String stub);
}
