package com.example.demo.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1002)
public class LogMethodTimeAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogMethodTimeAspect.class);

    @Pointcut("@within(com.example.demo.aop.annotation.LogMethodTime)" +
            " || @annotation(com.example.demo.aop.annotation.LogMethodTime)" +
            " || execution(* com.example.demo.controller..*.*(..))"
    )
    public void pointCut(){

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        // 获取方法名(类全路径+.+方法名)
        String classFullName = joinPoint.getSignature().getDeclaringTypeName();
        String className = classFullName.substring(classFullName.lastIndexOf(".") + 1);
        // 比如：com.xxx.XxxService.xxxMethod
        String name = className + "." + joinPoint.getSignature().getName();
        // 记录开始时间
        Object result = joinPoint.proceed();
        // 记录用时(大于50ms)的接口
        long end = System.currentTimeMillis() - start;
        if (end > 50){
            LOGGER.info("timeConsuming Log demo method:" + name + ",useTime:" + end + "ms more than 50 ============");
        }
        return result;
    }


}
