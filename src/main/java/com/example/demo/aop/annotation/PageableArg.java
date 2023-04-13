package com.example.demo.aop.annotation;

import java.lang.annotation.*;

/**
 *  分页参数注解
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageableArg {

}
