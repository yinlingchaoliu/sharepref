package com.caliburn.sharepref.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author chentong
 * @desc SPNAME
 * @date 2018/7/10.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RUNTIME)
public @interface SPNAME {
    String value() default "";
}