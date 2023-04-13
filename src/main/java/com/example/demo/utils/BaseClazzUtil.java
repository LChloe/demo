package com.example.demo.utils;

import com.example.demo.aop.annotation.JaversExpandInfo;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BaseClazzUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BaseClazzUtil.applicationContext = applicationContext;
    }

    public static List<Map<String,String>> JaversExpandInfoAnnotationList() {
        List<Map<String,String>> result = new ArrayList<>();
        Map<String, Object> annotations = applicationContext.getBeansWithAnnotation(JaversExpandInfo.class);
        for (Map.Entry<String, Object> entry: annotations.entrySet()){
            Map<String,String> annotation = new HashMap<>();
            Object value = entry.getValue();

//            Class<?> aClass = AopUtils.getTargetClass(value);//得到的是com.sun.proxy.$Proxy158代理类
//            Class<?> aClass = ClassUtils.getUserClass(value);//得到的是com.sun.proxy.$Proxy158代理类


//            Object bean = applicationContext.getBean(entry.getKey());
//            bean.getClass().getAnnotation(JaversExpandInfo.class);//得到的是com.sun.proxy.$Proxy158代理类


            // 如果Spring的版本为4.3.8或以上 直接调用AopProxyUtils.getSingletonTarget(value)就可以获取target对象了
//            while (value instanceof Advised) {
//                TargetSource targetSource = ((Advised) value).getTargetSource();
//                if (targetSource instanceof SingletonTargetSource) {
//                    value = ((SingletonTargetSource) targetSource).getTarget();
//                }
//            }

//            value = AopProxyUtils.getSingletonTarget(value);
//            Class<?> aClass = AopUtils.getTargetClass(value);


//            JaversExpandInfo javersExpandInfo = aClass.getAnnotation(JaversExpandInfo.class);


            JaversExpandInfo javersExpandInfo = AnnotationUtils.findAnnotation(value.getClass(), JaversExpandInfo.class);
            if (javersExpandInfo == null){
                continue;
            }
            annotation.put("name", javersExpandInfo.name());
            annotation.put("desc", javersExpandInfo.desc());
//            System.out.println(javersExpandInfo.desc() + ":" + javersExpandInfo.name());
            result.add(annotation);
        }
        return result;
    }
}
