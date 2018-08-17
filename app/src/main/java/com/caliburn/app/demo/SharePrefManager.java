package com.caliburn.app.demo;

import com.caliburn.app.demo.config.IStudentSharedPref;
import com.caliburn.sharepref.core.SharePrefFacade;
import com.caliburn.app.demo.config.ISharedPref;

/**
 * 继承SharePrefFacade类
 * 引用
 */
public class SharePrefManager extends SharePrefFacade {

    private ISharedPref mISharedPrefDelegate;
    private IStudentSharedPref mIStudentPrefDelegate;

    @Override
    public void create() {
        mISharedPrefDelegate = create(ISharedPref.class);
        mIStudentPrefDelegate = create(IStudentSharedPref.class);
    }

    public static ISharedPref ISharedPref() {
        return getIns().mISharedPrefDelegate;
    }

    public static IStudentSharedPref getIStudentPref() {
        return getIns().mIStudentPrefDelegate;
    }

    private SharePrefManager() {
    }

    private static class SingleHolder {
        private static final SharePrefManager sharePrefManager = new SharePrefManager();
    }

    public static SharePrefManager getIns() {
        return SingleHolder.sharePrefManager;
    }

}
