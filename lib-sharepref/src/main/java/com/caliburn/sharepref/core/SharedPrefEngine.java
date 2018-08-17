package com.caliburn.sharepref.core;

import com.caliburn.sharepref.annotation.SPNAME;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @desc SharedPrefEngine
 * @date 2017/2/3.
 * 增加sharepref 表名称
 * 解析核心
 */
class SharedPrefEngine {

    private final Map<Method, ServiceMethod> mServiceMethodCache = new LinkedHashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T create(final Class<T> service) {
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
                new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object... args)
                            throws Throwable {
                        if (method.getDeclaringClass() == Object.class) {
                            return method.invoke(this, args);
                        }
                        SPNAME spNameAnnotation = service.getAnnotation(SPNAME.class);
                        String spName = spNameAnnotation.value();
                        ServiceMethod serviceMethod = loadServiceMethod(method);
                        SharedPrefCall sharedPrefCall = new SharedPrefCall<>(spName, serviceMethod);
                        return new ExecutorCallbackCall<>(sharedPrefCall);
                    }
                });
    }

    private ServiceMethod loadServiceMethod(Method method) {
        ServiceMethod result;
        synchronized (mServiceMethodCache) {
            result = mServiceMethodCache.get(method);
            if (result == null) {
                result = new ServiceMethod.Builder(method).build();
                mServiceMethodCache.put(method, result);
            }
        }
        return result;
    }

}
