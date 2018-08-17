package com.caliburn.sharepref.support;

import java.util.Map;

/**
 * @author chentong
 * @date 2018-07-09
 * @see SharedPreferencesStorage
 */
interface Storage {

    <T> void put(String key, T value);

    <T> T get(String key);

    <T> T get(String key, T defaultValue);

    <T> void putObj(String key, T value);

    <T> Object getObj(String key, T defaultValue);

    void delete(String key);

    void deleteAll();

    long count();

    boolean contains(String key);

    Map<String, ?> getAll();

}