package com.caliburn.sharepref.support;

import java.util.Map;

/**
 * 重构hawk 支持多SharedPreferences
 * 暂不支持数据加密
 */
public class Hawk {

    private HawkFacade hawkFacade = new HawkFacade.EmptyHawkFacade();

    private Hawk() {
    }

    /**
     * 初始化一张spname表
     *
     * @param storage
     */
    public Hawk(Storage storage) {
        CommonUtils.checkNull("storage", storage);
        HawkBuilder builder = new HawkBuilder(storage);
        hawkFacade = new DefaultHawkFacade(builder);
    }

    public <T> void put(String key, T value) {
        hawkFacade.put(key, value);
    }

    public <T> T get(String key) {
        return hawkFacade.get(key);
    }

    public <T> T get(String key, T defaultValue) {
        return hawkFacade.get(key, defaultValue);
    }

    public <T> void putObj(String key, T value) {
        hawkFacade.putObj(key, value);
    }

    public <T> Object getObj(String key, T defaultValue) {
        return hawkFacade.getObj(key, defaultValue);
    }

    public Map<String, ?> getAll() {
        return hawkFacade.getAll();
    }

    public long count() {
        return hawkFacade.count();
    }

    public void deleteAll() {
        hawkFacade.deleteAll();
    }

    public void delete(String key) {
        hawkFacade.delete(key);
    }

    public boolean contains(String key) {
        return hawkFacade.contains(key);
    }

    public boolean isBuilt() {
        return hawkFacade.isBuilt();
    }

    public void destroy() {
        hawkFacade.destroy();
    }

}
