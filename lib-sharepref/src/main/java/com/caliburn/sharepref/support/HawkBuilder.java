package com.caliburn.sharepref.support;

import android.util.Log;

import com.google.gson.Gson;

class HawkBuilder {

    private Storage cryptoStorage;
    private Converter converter;
    private Parser parser;
    private Serializer serializer;
    private LogInterceptor logInterceptor;

    private HawkBuilder() {

    }

    public HawkBuilder(Storage storage) {
        CommonUtils.checkNull("Storage", storage);
        this.cryptoStorage = storage;
    }

    public HawkBuilder setStorage(Storage storage) {
        this.cryptoStorage = storage;
        return this;
    }

    public HawkBuilder setParser(Parser parser) {
        this.parser = parser;
        return this;
    }

    public HawkBuilder setSerializer(Serializer serializer) {
        this.serializer = serializer;
        return this;
    }

    public HawkBuilder setLogInterceptor(LogInterceptor logInterceptor) {
        this.logInterceptor = logInterceptor;
        return this;
    }

    public HawkBuilder setConverter(Converter converter) {
        this.converter = converter;
        return this;
    }

    LogInterceptor getLogInterceptor() {
        if (logInterceptor == null) {
            logInterceptor = new LogInterceptor() {
                @Override
                public void onLog(String message) {
                    //TODO 在此打开存储日志开关
                    //Log.d("chentong", message);
                }
            };
        }
        return logInterceptor;
    }

    Storage getStorage() {
        return cryptoStorage;
    }

    Converter getConverter() {
        if (converter == null) {
            converter = new HawkConverter(getParser());
        }
        return converter;
    }

    Parser getParser() {
        if (parser == null) {
            parser = new GsonParser(new Gson());
        }
        return parser;
    }


    Serializer getSerializer() {
        if (serializer == null) {
            serializer = new HawkSerializer(getLogInterceptor());
        }
        return serializer;
    }

}
