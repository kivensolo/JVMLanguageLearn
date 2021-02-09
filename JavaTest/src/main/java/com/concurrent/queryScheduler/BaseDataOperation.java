package com.concurrent.queryScheduler;

/**
 * 数据操作-- 基类
 */
public class BaseDataOperation {
    public boolean exec(BaseDataCallback callback) throws DataException {
    	//dosomeThing by youself
		return false;
	}

	public boolean cancel() {
		return false;
	}
}
