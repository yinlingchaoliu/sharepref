package com.caliburn.sharepref.core;

import android.content.Context;

import com.caliburn.sharepref.annotation.KEY;
import com.caliburn.sharepref.support.Hawk;
import com.caliburn.sharepref.support.SharedPreferencesStorage;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @author chentong
 * date:2018/8/15
 */
class SharePrefHelper {

    private Hashtable<String, Hawk> hawkHashtable = new Hashtable<>();
    private static Context appContext;

    private SharePrefHelper() {
    }

    public static SharePrefHelper getIns() {
        return SingleHolder.sharePrefHelper;
    }

    private static class SingleHolder {
        private static final SharePrefHelper sharePrefHelper = new SharePrefHelper();
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        checkNull("context", context);
        appContext = context;
    }

    /**
     * 注册表
     *
     * @param spName
     */
    public void register(String spName) {
        checkNull("spname", spName);
        checkNull("appContext", appContext);
        if (contains(spName)) {
            return;
        }
        SharedPreferencesStorage storage = new SharedPreferencesStorage(appContext, spName);
        Hawk hawk = new Hawk(storage);
        hawkHashtable.put(spName, hawk);
    }

    public Hawk getHawk(String spName) {
        checkNull("spname", spName);
        return hawkHashtable.get(spName);
    }

    /**
     * 清空当前表数据
     *
     * @param spName
     */
    public void deleteTable(String spName) {
        getHawk(spName).deleteAll();
    }

    /**
     * 迁移sharepref
     * 全部迁移
     * 优点：整张表迁移
     * 缺点：重复字段会覆盖值
     *
     * @param fromSharePref
     * @param toSharePref
     */
    public void migrateSharePref(String fromSharePref, String toSharePref) {
        checkNull("fromSharePref", fromSharePref);
        checkNull("toSharePref", toSharePref);
        register(fromSharePref);
        register(toSharePref);
        Hawk fromHawk = getHawk(fromSharePref);
        Hawk toHawk = getHawk(toSharePref);
        Map<String, ?> allMap = fromHawk.getAll();
        if (allMap == null || allMap.isEmpty()) {
            return;
        }
        for (Map.Entry<String, ?> entry : allMap.entrySet()) {
            toHawk.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 数据准确迁移
     * 按照新表结构 准确迁移数据
     *
     * @param fromSharePref
     * @param toSharePref
     * @param toTableclazz
     */
    public void exactlyDataMigrate(String fromSharePref, String toSharePref, Class toTableclazz) {

        checkNull("fromSharePref", fromSharePref);
        checkNull("toSharePref", toSharePref);
        checkNull("tableclazz", toTableclazz);

        List<String> allTableKeyList = getAllKey(toTableclazz);

        register(fromSharePref);
        register(toSharePref);
        Hawk fromHawk = getHawk(fromSharePref);
        Hawk toHawk = getHawk(toSharePref);
        Map<String, ?> allMap = fromHawk.getAll();
        if (allMap == null || allMap.isEmpty()) {
            return;
        }

        for (Map.Entry<String, ?> entry : allMap.entrySet()) {
            String key = entry.getKey();
            if (containsTable(allTableKeyList, key)) {
                toHawk.put(key, entry.getValue());
            }
        }

    }

    /*********以下辅助小工具*********/

    /**
     * 判断该表是否包含该key
     * 或者以key开头
     *
     * @param tableList
     * @param key
     * @return
     */
    private boolean containsTable(List<String> tableList, String key) {
        if (tableList.contains(key)) {
            return true;
        }

        for (String tableKey : tableList) {
            if (key.startsWith(tableKey)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 获得该表所有key值
     *
     * @param clazz
     * @return
     */
    public List<String> getAllKey(Class clazz) {

        List<String> list = new ArrayList<>();
        Method[] methodArray = clazz.getDeclaredMethods();
        if (methodArray == null) return list;
        for (Method method : methodArray) {
            Annotation methodAnnotation = method.getAnnotation(KEY.class);
            String value = ((KEY) methodAnnotation).value();
            list.add(value);
        }

        return list;
    }

    /**
     * 是否存在该表
     *
     * @param spName
     * @return
     */
    private boolean contains(String spName) {
        return hawkHashtable.containsKey(spName);
    }

    /**
     * 判断输入是否为空
     *
     * @param message
     * @param value
     */
    private void checkNull(String message, Object value) {
        if (value == null) {
            throw new NullPointerException(message + " should not be null");
        }
    }
}