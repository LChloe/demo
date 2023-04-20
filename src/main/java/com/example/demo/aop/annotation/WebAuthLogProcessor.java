package com.example.demo.aop.annotation;

import java.lang.annotation.*;

/**
 * 审计用户的url注释
 * 描述POST、PUT、DELETE等变更方法
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebAuthLogProcessor {
    int value() default 999;
    String prefix() default "Audit Log";
    //示例：新增发布流
    String title() default "变更请求";//审计日志输出
    //示例：appId,id,dockerKubeClusterId,yamlTemplateId
    String params() default "";//需要记录的body字段
    String serviceName() default "Demo";//notice 注意，不同服务使用自身的服务唯一标识
}
