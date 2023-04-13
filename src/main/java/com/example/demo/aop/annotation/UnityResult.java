package com.example.demo.aop.annotation;

import java.lang.annotation.*;

/**
 * 统一返回结果注释
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UnityResult {
}
