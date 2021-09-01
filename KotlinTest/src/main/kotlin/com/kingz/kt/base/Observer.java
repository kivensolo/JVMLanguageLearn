package com.kingz.kt.base;

/**
 * Android lifecycle的Observer
 * @param <T>
 */
public interface Observer<T> {
    /**
     * Called when the data is changed.
     * @param t  The new data
     */
    void onChanged(T t);
}
