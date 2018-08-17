package com.caliburn.sharepref.core;

import android.content.Context;

import com.caliburn.sharepref.support.Hawk;

/**
 * @author chentong
 * sharePref管理类
 * 支持参数可配置化
 * 支持多种数据类型任意object list map
 * 支持原有sp数据存储 getObj putObj
 * 支持数据迁移
 * 可扩展性强，后续可支持加密
 * 支持数据异步提交用apply并且兼容
 * 支持多sharepref文件
 * 支持项目可拆分多部分 动态配置, 基本库(sp+注解)
 * @date 2018-07-10
 */
public abstract  class SharePrefFacade extends SharedPrefEngine {

    /**
     * 必须初始化
     * @param context
     */
    public void init(Context context) {
        SharePrefHelper.getIns().init(context);
        //在这里初始化配置类
        create();
    }

    public  abstract void create();

    /**
     * 获得存储sharepref句柄 提升当前sp表最高权限
     * @param spName
     * @return
     */
    public Hawk getHawk(String spName) {
        return SharePrefHelper.getIns().getHawk(spName);
    }

    /**
     * 获得存储sharepref句柄 提升当前sp表最高权限
     *
     * @param spName
     * @return
     */
    public void deleteTable(String spName) {
        SharePrefHelper.getIns().deleteTable(spName);
    }

    /**
     * 迁移sp
     * 全部迁移
     * @param fromSharePref
     * @param toSharePref
     */
    public void migrateSharePref(String fromSharePref, String toSharePref) {
        SharePrefHelper.getIns().migrateSharePref(fromSharePref, toSharePref);
    }

    /**
     * 数据准确迁移
     *
     * @param fromSharePref
     * @param toSharePref
     * @param toTableclazz
     */
    public void exactlyDataMigrate(String fromSharePref, String toSharePref, Class toTableclazz) {
        SharePrefHelper.getIns().exactlyDataMigrate(fromSharePref,toSharePref,toTableclazz);
    }

}
