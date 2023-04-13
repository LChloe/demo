package com.example.demo.aop.aspect;

import com.example.demo.base.result.ResponseResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * controller统一返回结果包装
 */
@Component
@Aspect
@Order(1002)
public class UnityResultAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(UnityResultAspect.class);

    @Pointcut("@within(com.example.demo.aop.annotation.UnityResult)" +
            " || @annotation(com.example.demo.aop.annotation.UnityResult)" +
            " || execution(* com.example.demo.controller..*.*(..))"
    )
    public void pointCut(){
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object result = joinPoint.proceed();
            return ResponseResult.success(result);
        } catch (Exception e){//todo 可分更加复杂的报错分类。例如无权限，未登录等等
            LOGGER.error(e.getMessage(), e);
            return ResponseResult.fail(e.getMessage());
        }
    }
}
