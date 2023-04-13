package com.example.demo.aop.annotation;

import java.lang.annotation.*;

/**
 * desc：中文描述，展示前端
 * name：请求参数，为实体类的类名
 * fullyQualifiedName：实体类的全限定名
 * belongPath：归属服务的接口请求路由，提供给前端使用(微服务使用)
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JaversExpandInfo {
    String desc();
    String name();
    String fullyQualifiedName() default "";
    //todo 记得根据具体项目调整belongPath
    String belongPath() default "tiano-publish";

}

