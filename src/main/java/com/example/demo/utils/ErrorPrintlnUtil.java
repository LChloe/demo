package com.example.demo.utils;

import com.example.demo.base.result.ApiResultVo;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class ErrorPrintlnUtil {
    public static Logger LOGGER = LoggerFactory.getLogger(ErrorPrintlnUtil.class);

    public static void printlnErrorHttpResponse(String description, String url, long intervalTime, int code, String result, Integer timeout){
        //response.getEntity()所得到的流是不可重复读取的，所得的实体只能读取一次,读取一次后,流就关闭了。EntityUtils.toString(responseEntity)被调用一次后就会自动销毁
        LOGGER.error("Third Party Dependency request {} return error, url:{}", description, url);
        LOGGER.error("Third Party Dependency timeout:{}  Time-consuming:{}" , timeout, intervalTime);
        try {
            LOGGER.error("Third Party Dependency HTTP Status:{}", code);
            LOGGER.error("Third Party Dependency Result is ->:{}",  result);
        }catch (Exception e){
            LOGGER.error("println result error:", e);
        }
    }

    public static void printlnErrorImmutablePair(String description, String url, long intervalTime, ImmutablePair<Integer, String> responseData, Integer timeout){
        LOGGER.error("Third Party Dependency request {} return error, url:{}", description, url);
        LOGGER.error("Third Party Dependency timeout:{}  Time-consuming:{}" , timeout, intervalTime);
        try {
            LOGGER.error("Third Party Dependency HTTP Status:{}", responseData.getLeft());
            LOGGER.error("Third Party Dependency Result is ->:{}", responseData.getRight());
        }catch (Exception e){
            LOGGER.error("println result error:", e);
        }
    }

    public static void printlnErrorApiResultVo(String description, String url, long intervalTime, ApiResultVo apiResultVo, Integer timeout){
        if (404 == apiResultVo.getResultCode()){  //因无法确定是无实例还是挂了，可根据有实例的发布流判断
//            LOGGER.warn("Third Party Dependency request {} return error, url:{}", description, url);
//            LOGGER.warn("Third Party Dependency timeout:{}  Time-consuming:{}" , timeout, intervalTime);
//            try {
//                LOGGER.warn("Third Party Dependency HTTP Status:{}", apiResultVo.getResultCode());
//                LOGGER.warn("Third Party Dependency Result is ->:{}", apiResultVo.getResult());
//                LOGGER.warn("Third Party Dependency ResultStatus is ->:{}", apiResultVo.getResultStatus());
//            }catch (Exception e){
//                LOGGER.error("println result error:", e);
//            }
        }else {
            LOGGER.error("Third Party Dependency request {} return error, url:{}", description, url);
            LOGGER.error("Third Party Dependency timeout:{}  Time-consuming:{}" , timeout, intervalTime);
            try {
                LOGGER.error("Third Party Dependency HTTP Status:{}", apiResultVo.getResultCode());
                LOGGER.error("Third Party Dependency Result is ->:{}", apiResultVo.getResult());
                LOGGER.error("Third Party Dependency ResultStatus is ->:{}", apiResultVo.getResultStatus());
            }catch (Exception e){
                LOGGER.error("println result error:", e);
            }
        }
    }

    public static void printlnErrorApiString(String description, String url, long intervalTime, String result, Integer timeout){
        LOGGER.error("Third Party Dependency request {} return error, url:{}", description, url);
        LOGGER.error("Third Party Dependency timeout:{}  Time-consuming:{}" , timeout, intervalTime);
        try {
            LOGGER.error("Third Party Dependency Result:{}", result);
        }catch (Exception e){
            LOGGER.error("println result error:", e);
        }
    }

    public static void printlnWarnApiString(String description, String url, long intervalTime, String result, Integer timeout){
        LOGGER.warn("Third Party Dependency request {} return error, url:{}", description, url);
        LOGGER.warn("Third Party Dependency timeout:{}  Time-consuming:{}" , timeout, intervalTime);
        try {
            LOGGER.warn("Third Party Dependency Result:{}", result);
        }catch (Exception e){
            LOGGER.warn("println result error:", e);
        }
    }

    public static void printlnErrorSDKInfo(String description, long intervalTime, String errorInfo){
        LOGGER.error("Third Party Dependency request {}, Time-consuming:{}", description, intervalTime);
        try {
            LOGGER.error("Third Party Dependency SDK Error Info:{}", errorInfo);
        }catch (Exception e){
            LOGGER.error("println result error:", e);
        }
    }

//    public static void printlnAuditLog(HttpServletRequest request, OaUser currentOaUser){
//        if (currentOaUser != null){
//            LOGGER.info("Audit Log Tiano RequestURL:{}, RequestMethod:{}, OaId:{}, ServletPath:{}", request.getRequestURL(), request.getMethod(), currentOaUser.getId(), request.getServletPath());
//        }else {
//            LOGGER.info("Audit Log Tiano RequestURL:{}, RequestMethod:{}, ServletPath:{}", request.getRequestURL(), request.getMethod(), request.getServletPath());
//        }
//
//    }

    public static void printlnAuditLog(HttpServletRequest request){
        LOGGER.info("Audit Log Cradle RequestURL:{}, RequestMethod:{}, OaId:{}, ServletPath:{}", request.getRequestURL(), request.getMethod(), request.getHeader("X-USER-OAID"), request.getServletPath());
    }
}
