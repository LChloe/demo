package com.example.demo.aop.aspect;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.demo.aop.annotation.WebLogProcessor;
import com.example.demo.base.result.ResponseResult;
import com.example.demo.entity.OperationalAudit;
import com.example.demo.service.OperationalAuditService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 审计用户请求的url
 */
@Component
@Aspect
//@Order(1002)
public class WebLogProcessorAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogProcessorAspect.class);

    @Autowired
    private OperationalAuditService operationalAuditService;

    @Pointcut("@within(com.example.demo.aop.annotation.WebLogProcessor)" +
    " || @annotation(com.example.demo.aop.annotation.WebLogProcessor)")
    public void pointCut(){

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        OperationalAudit operationalAudit = new OperationalAudit();
        try {
            operationalAudit = auditRecord(joinPoint);
        } catch (Exception e) {
            LOGGER.error("Audit Log handle Error:", e);
        }
        //notice 返回类型不同服务可能需要调整
        ResponseResult result = (ResponseResult) joinPoint.proceed();
        auditStatus(operationalAudit, result);
        return result;
    }


    private OperationalAudit auditRecord(ProceedingJoinPoint joinPoint) {
        Map<String, Object> fullParams = getRequestParams(joinPoint);
        WebLogProcessor webAuthLogProcessor = getAnnotationParams(joinPoint);
        if (webAuthLogProcessor == null) {//判空做对应的处理
            return saveOperationalAudit(fullParams, null, new HashMap<>(), new String[10]);
        }else {
            String[] logParams = webAuthLogProcessor.params().split(",");
            //获取需要记录的值
            Map<String, Object> logParamsResult = getLogParamsResult(fullParams, logParams);
            //保存需要记录的值 以及对应的操作日志
            return saveOperationalAudit(fullParams, webAuthLogProcessor, logParamsResult, logParams);
        }
    }

    /**
     * 更新接口执行状态
     * @param operationalAudit 审计实体类
     * @param result 接口请求结果类
     */
    private void auditStatus(OperationalAudit operationalAudit, ResponseResult result) {
        if (operationalAudit.getId() != null) {
            operationalAudit.setExecStatus(Integer.valueOf(result.getCode()));
            operationalAudit.setExecMsg(result.getMessage());
            operationalAuditService.update(operationalAudit);
        }
    }

    /**
     * 获取请求的参数
     * @param joinPoint aop信息
     * @return 请求的全部变量
     */
    private Map<String, Object> getRequestParams(ProceedingJoinPoint joinPoint) {
        Map<String, Object> paramsMap = new HashMap<>();
        //获取请求参数的值
        //notice 使用hutool时
//        JSONConfig jsonConfig = new JSONConfig();
//        jsonConfig.setIgnoreNullValue(false);//不要忽略null值
//        JSONArray paramsValue = JSONObject.parseArray(JSONUtil.toJsonStr(joinPoint.getArgs(), jsonConfig));
        //notice 使用fastjson2
        JSONArray paramsValue = new JSONArray(joinPoint.getArgs());

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //获取请求参数的名称
        String[] paramsName = methodSignature.getParameterNames();
        for (int i = 0; i < paramsName.length; i++) {
            paramsMap.put(paramsName[i], paramsValue.get(i));
        }
        return paramsMap;
    }

    /**
     * 获取对应的WebAuthLogProcessor注释信息
     * @param joinPoint aop信息
     * @return 审计注释信息
     */
    private WebLogProcessor getAnnotationParams(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //理论上不会报错
        return methodSignature.getMethod().getAnnotation(WebLogProcessor.class);
    }

    /**
     * 分析需要记录的审计变量
     * @param fullParamsMap 所有的变量
     * @param logParams 需要检索的列表
     * @return 返回检索到的变量
     */
    private Map<String, Object> getLogParamsResult(Map<String, Object> fullParamsMap, String[] logParams) {
        Map<String, Object> result = new HashMap<>();
        for (String paramName : logParams) {
            handleLogParams(paramName, fullParamsMap, result);
        }
        return result;
    }

    private void handleLogParams(String param, Map<String, Object> matchMap, Map<String, Object> result) {
//        String regex = "\\s*[\\{\\[].*[\\}\\]]\\s*";
        if (matchMap.containsKey(param)) {
            result.put(param, matchMap.get(param));
//            matchMap.remove(param);
        }else {
            for (String key : matchMap.keySet()) {
                boolean flag = false;
                if (key.equals(param)) {
                    result.put(param, matchMap.get(key));
                    flag = true;
                }else {
                    if (matchMap.get(key) instanceof JSONObject) {
                        handleLogParams(param, (JSONObject) matchMap.get(key), result);
                    } else if (matchMap.get(key) instanceof Map) {
                        handleLogParams(param, (JSONObject) matchMap.get(key), result);
                    } else if (matchMap.get(key) instanceof String) {
                        if (isJSONObjectString((String) matchMap.get(key))){
                            JSONObject jsonObject = JSONObject.parseObject(((String) matchMap.get(key)));
                            handleLogParams(param, jsonObject, result);
                        }
                    }
                    //todo 如果是List JSONArrayString 暂时觉得没必要
                }
                if (flag) {
                    break;
                }
            }
        }
    }

    /**
     * 判断是不是JSONObjectString
     * @param text 需要检查的字符串
     * @return true or false
     */
    private boolean isJSONObjectString(String text) {
        if(StringUtils.isEmpty(text)){
            return false;
        }
        boolean isJsonObject = true;
        try {
            JSONObject.parseObject(text);
        } catch (Exception e) {
            isJsonObject = false;
        }
        return isJsonObject;
    }

    /**
     * 判断是不是JSONArrayString
     * @param text 需要检查的字符串
     * @return true or false
     */
    private boolean isJSONArrayString(String text) {
        if(StringUtils.isEmpty(text)){
            return false;
        }
        boolean isJsonArray = true;
        try {
            JSONArray.parseArray(text);  //fastjson2
//            JSONObject.parseArray(text); //fastjson1
        } catch (Exception e) {
            isJsonArray = false;
        }
        return isJsonArray;
    }

    /**
     * 操作审计日志保存
     * @param fullParams 请求的所有变量
     * @param webAuthLogProcessor 审计注释信息
     * @param logParamsResult 审计日志变量的值
     * @param logParams 审计日志变量名
     */
    private OperationalAudit saveOperationalAudit(Map<String, Object> fullParams, WebLogProcessor webAuthLogProcessor, Map<String, Object> logParamsResult, String[] logParams) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        OperationalAudit operationalAudit = new OperationalAudit();

        if (webAuthLogProcessor == null) {
            operationalAudit.setPrefix("Audit Log");
            operationalAudit.setServiceName("null");
            operationalAudit.setTitle("变更请求");
        } else {
            operationalAudit.setPrefix(webAuthLogProcessor.prefix());
            operationalAudit.setServiceName(webAuthLogProcessor.serviceName());
            operationalAudit.setTitle(webAuthLogProcessor.title());
        }

        operationalAudit.setRequestUrl(request.getRequestURL().toString());
        operationalAudit.setRequestMethod(request.getMethod());
        operationalAudit.setServletPath(request.getServletPath());

        //notice 更换成自身的验证的身份
        String oaId = request.getHeader("X-USER-OAID");
        operationalAudit.setOaId(StringUtils.isBlank(oaId) ? "0" : oaId);


        operationalAudit.setFullParams(new JSONObject(fullParams).toString());
        operationalAudit.setLogParamsResult(new JSONObject(logParamsResult).toString());
        operationalAudit.setLogParams(Arrays.toString(logParams));

        return operationalAuditService.save(operationalAudit);

    }
}
