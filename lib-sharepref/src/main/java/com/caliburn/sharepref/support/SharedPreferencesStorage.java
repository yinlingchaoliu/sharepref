package com.caliburn.sharepref.support;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

/**
 * sp通用支撑类
 * 支持泛型
 *
 * @author chentong
 * @date 2018-07-09
 */
public class SharedPreferencesStorage implements Storage {

    private SharedPreferences sharedPreferences;

    public SharedPreferencesStorage(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    /**
     * 定制化入参
     *
     * @param context
     * @param spName
     */
    public SharedPreferencesStorage(Context context, String spName) {
        CommonUtils.checkNull("context", context);
        CommonUtils.checkNull("spName", spName);
        sharedPreferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    public SharedPreferences getPreferences() {
        return sharedPreferences;
    }

    @Override
    public <T> void put(String key, T value) {
        CommonUtils.checkNull("key", key);
        if (value == null) {
            delete(key);
            return;
        }
        SharedPreferences sharedPreference = getPreferences();
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString(key, String.valueOf(value));
        SharedPreferencesCompat.apply(editor);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key) {
        return (T) getPreferences().getString(key, null);
    }

    public <T> T get(String key, T defaultValue) {
        T t = get(key);
        if (t == null) return defaultValue;
        return t;
    }

    @Override
    public <T> void putObj(String key, T value) {
        CommonUtils.checkNull("key", key);
        SharedPreferences sharedPreference = getPreferences();
        SharedPreferences.Editor editor = sharedPreference.edit();

        if (value == null) {
            editor.remove(key);
            SharedPreferencesCompat.apply(editor);
            return;
        }
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Set) {
            editor.putStringSet(key, (Set<String>) value);
        }
        SharedPreferencesCompat.apply(editor);
    }

    @Override
    public <T> Object getObj(String key, T defaultValue) {
        CommonUtils.checkNull("key", key);
        if (defaultValue instanceof String) {
            return getPreferences().getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            return getPreferences().getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Integer) {
            return getPreferences().getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Long) {
            return getPreferences().getLong(key, (Long) defaultValue);
        } else if (defaultValue instanceof Float) {
            return getPreferences().getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Set) {
            return getPreferences().getStringSet(key, (Set<String>) defaultValue);
        }
        return null;
    }

    @Override
    public void delete(String key) {
        SharedPreferences sharedPreference = getPreferences();
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    @Override
    public boolean contains(String key) {
        return getPreferences().contains(key);
    }

    @Override
    public void deleteAll() {
        SharedPreferences sharedPreference = getPreferences();
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    @Override
    public long count() {
        return getPreferences().getAll().size();
    }

    @Override
    public Map<String, ?> getAll() {
        return getPreferences().getAll();
    }
}
