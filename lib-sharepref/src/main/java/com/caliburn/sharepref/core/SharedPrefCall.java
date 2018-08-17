package com.caliburn.sharepref.core;

import android.text.TextUtils;

import com.caliburn.sharepref.support.Hawk;

import java.util.Set;

/**
 * modified by chenrong
 * 增加sharepref表名称
 */
class SharedPrefCall<T> implements Call<T> {

    private final ServiceMethod<T> serviceMethod;
    private final String spName;
    private String mKey = "";
    private String mCustomKey = "";//自定义key
    private Hawk hawk;

    public SharedPrefCall(String spName, ServiceMethod<T> serviceMethod) {
        this.spName = spName;
        this.serviceMethod = serviceMethod;
        SharePrefHelper.getIns().register(spName);
        hawk = SharePrefHelper.getIns().getHawk(spName);
    }

    @Override
    public T get() {
        String key = getKey();

        String defaultValue = serviceMethod.getDefault();
        Class<T> cls = serviceMethod.getTypeClass();

        try {
            if (cls == String.class) {
                return cls.cast(hawk.get(key, defaultValue));
            } else if (cls == Boolean.class) {
                return cls.cast(hawk.get(key, !TextUtils.isEmpty(defaultValue) && Boolean.parseBoolean(defaultValue)));
            } else if (cls == Integer.class) {
                return cls.cast(hawk.get(key, TextUtils.isEmpty(defaultValue) ? 0 : Integer.parseInt(defaultValue)));
            } else if (cls == Float.class) {
                return cls.cast(hawk.get(key, TextUtils.isEmpty(defaultValue) ? 0 : Float.parseFloat(defaultValue)));
            } else if (cls == Long.class) {
                return cls.cast(hawk.get(key, TextUtils.isEmpty(defaultValue) ? 0 : Long.parseLong(defaultValue)));
            } else if (cls == Set.class) {
                return cls.cast(hawk.get(key, defaultValue));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hawk.get(key);
    }

    @Override
    public T get(T defaultValue) {
        String key = getKey();
        T t = hawk.get(key, null);
        if (t == null) {
            return defaultValue;
        }
        return t;
    }

    @Override
    public void put(T t) {
        String key = getKey();
        try {
            hawk.put(key, t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getObj() {
        String key = getKey();
        return hawk.getObj(key, null);
    }

    @Override
    public Object getObj(T defaultValue) {
        String key = getKey();
        return hawk.getObj(key, defaultValue);
    }

    @Override
    public void putObj(T value) {
        String key = getKey();
        hawk.putObj(key, value);
    }

    @Override
    public Call<T> setKey(String key) {
        mKey = key;
        return this;
    }

    /**
     * key获取规则
     *
     * @return key
     */
    private String getKey() {

        if (!TextUtils.isEmpty(mCustomKey)) {
            return mCustomKey.trim();
        }

        String defaultKey = serviceMethod.getKey();
        if (!TextUtils.isEmpty(mKey)) {
            return defaultKey + mKey.trim();
        }

        return defaultKey;
    }

    @Override
    public Call<T> setCustomKey(String key) {
        mCustomKey = key;
        return this;
    }

    @Override
    public void remove() {
        String key = getKey();
        hawk.delete(key);
    }

    @Override
    public String getSpName() {
        return this.spName;
    }

}
