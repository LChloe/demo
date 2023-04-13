package com.example.demo.aop.annotation;

import java.lang.annotation.*;

/**
 * 方法花费多长时间注释
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogMethodTime {
    int value() default 999;
}
