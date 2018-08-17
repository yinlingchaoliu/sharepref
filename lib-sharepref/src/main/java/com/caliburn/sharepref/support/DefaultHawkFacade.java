package com.caliburn.sharepref.support;

import java.util.Map;

class DefaultHawkFacade implements HawkFacade {

    private final Storage storage;
    private final Converter converter;
    private final Serializer serializer;
    private final LogInterceptor logInterceptor;

    public DefaultHawkFacade(HawkBuilder builder) {
        storage = builder.getStorage();
        converter = builder.getConverter();
        serializer = builder.getSerializer();
        logInterceptor = builder.getLogInterceptor();
    }

    @Override
    public <T> void put(String key, T value) {
        // Validate
        CommonUtils.checkNull("Key", key);
        log("Hawk.put -> key: " + key + ", value: " + value);

        // 1. Convert to text
        String plainText = converter.toString(value);
        log("Hawk.put -> Converted to " + plainText);
        if (plainText == null) {
            log("Hawk.put -> Converter failed");
        }

        // 3. Serialize the given object along with the cipher text
        String serializedText = serializer.serialize(plainText, value);
        log("Hawk.put -> Serialized to " + serializedText);
        if (serializedText == null) {
            log("Hawk.put -> Serialization failed");
        }

        // 4. Save to the storage
        storage.put(key, serializedText);

    }

    @Override
    public <T> T get(String key) {
        log("Hawk.get -> key: " + key);
        if (key == null) {
            log("Hawk.get -> null key, returning null value ");
            return null;
        }

        // 1. Get serialized text from the storage
        String serializedText = storage.get(key);
        log("Hawk.get -> Fetched from storage : " + serializedText);
        if (serializedText == null) {
            log("Hawk.get -> Fetching from storage failed");
            return null;
        }

        // 2. Deserialize
        DataInfo dataInfo = serializer.deserialize(serializedText);
        log("Hawk.get -> Deserialized");
        if (dataInfo == null) {
            log("Hawk.get -> Deserialization failed");
            return null;
        }

        // 4. Convert the text to original data along with original type
        T result = null;
        try {
            result = converter.fromString(dataInfo.getCipherText(), dataInfo);
            log("Hawk.get -> Converted to : " + result);
        } catch (Exception e) {
            log("Hawk.get -> Converter failed");
        }

        return result;
    }

    @Override
    public <T> T get(String key, T defaultValue) {
        T t = get(key);
        if (t == null) return defaultValue;
        return t;
    }

    @Override
    public <T> void putObj(String key, T value) {
        storage.putObj(key, value);
    }

    @Override
    public <T> Object getObj(String key, T defaultValue) {
        return storage.getObj(key, defaultValue);
    }

    @Override
    public Map<String, ?> getAll() {
        return storage.getAll();
    }

    @Override
    public long count() {
        return storage.count();
    }

    @Override
    public void deleteAll() {
        storage.deleteAll();
    }

    @Override
    public void delete(String key) {
        storage.delete(key);
    }

    @Override
    public boolean contains(String key) {
        return storage.contains(key);
    }

    @Override
    public boolean isBuilt() {
        return true;
    }

    @Override
    public void destroy() {
    }

    private void log(String message) {
        logInterceptor.onLog(message);
    }
}
