package com.caliburn.sharepref.core;

import com.caliburn.sharepref.annotation.DEFAULT;
import com.caliburn.sharepref.annotation.KEY;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @desc ServiceMethod
 * @date 2017/2/3.
 */
class ServiceMethod<T> {

    private final Class mTypeClass;
    private String mKey;
    private String mDefault;

    ServiceMethod(Builder<T> builder) {
        mKey = builder.mKey;
        mDefault = builder.mDefault;
        mTypeClass = builder.mTypeClass;
    }

    public String getKey() {
        return mKey;
    }

    public String getDefault() {
        return mDefault;
    }

    public Class getTypeClass() {
        return mTypeClass;
    }

    public static final class Builder<T> {
        final Method method;
        final Annotation[] methodAnnotations;
        final Class mTypeClass;
        String mKey;
        String mDefault;
        String mSpName;

        public Builder(Method method) {
            this.method = method;
            this.methodAnnotations = method.getAnnotations();

            ParameterizedType returnType = (ParameterizedType) method.getGenericReturnType();
            Type[] types = returnType.getActualTypeArguments();
            mTypeClass = (Class) types[0];
        }

        public ServiceMethod build() {
            for (Annotation annotation : methodAnnotations) {
                parseMethodAnnotation(annotation);
            }

            return new ServiceMethod<>(this);
        }

        private void parseMethodAnnotation(Annotation annotation) {
            if (annotation instanceof KEY) {
                mKey = ((KEY) annotation).value();
            } else if (annotation instanceof DEFAULT) {
                mDefault = ((DEFAULT) annotation).value();
            }
        }

    }
}
