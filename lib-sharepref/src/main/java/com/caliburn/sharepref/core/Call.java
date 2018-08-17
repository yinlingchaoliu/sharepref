package com.caliburn.sharepref.core;

public interface Call<T> {

    T get();

    T get(T defaultValue);

    void put(T value);

    Object getObj();

    Object getObj(T defaultValue);

    void putObj(T value);

    /**
     * 后缀增加key
     * @param key
     * @return
     */
    Call<T> setKey(String key);

    /**
     * 完全自定义key
     * @param key
     * @return
     */
    Call<T> setCustomKey(String key);

    void remove();

    /**
     * 不直接提供removeAll方法,避免误删
     * 提供sp表名，通过表名来清理整张sp表内容
     * @return
     */
    String getSpName();
}