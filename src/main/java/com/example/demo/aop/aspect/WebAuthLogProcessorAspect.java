package com.example.demo.aop.aspect;

import com.example.demo.utils.ErrorPrintlnUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 审计用户请求的url
 */
@Component
@Aspect
//@Order(1002)
public class WebAuthLogProcessorAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebAuthLogProcessorAspect.class);

    @Pointcut("@within(com.example.demo.aop.annotation.WebAuthLogProcessor)" +
    " || @annotation(com.example.demo.aop.annotation.WebAuthLogProcessor)")
    public void pointCut(){

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        check(joinPoint);
        return joinPoint.proceed();
    }

    private void check(ProceedingJoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ErrorPrintlnUtil.printlnAuditLog(request);
    }
}
