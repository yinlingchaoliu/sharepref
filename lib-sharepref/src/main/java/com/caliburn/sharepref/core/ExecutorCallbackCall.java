package com.caliburn.sharepref.core;

/**
 * 支持hawk存储
 * 支持原sharepre方式存储
 */
class ExecutorCallbackCall<T> implements Call<T> {

    private final Call<T> delegate;

    public ExecutorCallbackCall(Call<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public T get() {
        return delegate.get();
    }

    @Override
    public T get(T defaultValue) {
        return delegate.get(defaultValue);
    }

    @Override
    public void put(T t) {
        delegate.put(t);
    }

    @Override
    public Object getObj() {
        return delegate.getObj();
    }

    @Override
    public Object getObj(T defaultValue) {
        return delegate.getObj(defaultValue);
    }

    @Override
    public void putObj(T value) {
        delegate.putObj(value);
    }

    @Override
    public Call<T> setKey(String key) {
        return delegate.setKey(key);
    }

    @Override
    public Call<T> setCustomKey(String key) {
        return delegate.setCustomKey(key);
    }

    @Override
    public void remove() {
        delegate.remove();
    }

    @Override
    public String getSpName() {
        return delegate.getSpName();
    }
}