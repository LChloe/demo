package com.example.demo.aop.annotation;

import java.lang.annotation.*;

/**
 * 审计用户的url注释
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebAuthLogProcessor {
    int value() default 999;
}
