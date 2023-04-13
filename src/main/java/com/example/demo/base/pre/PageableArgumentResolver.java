package com.example.demo.base.pre;

import com.example.demo.aop.annotation.PageableArg;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.ArrayList;
import java.util.List;

public class PageableArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger logger = LoggerFactory.getLogger(PageableArgumentResolver.class);

    /** 每页大小 */
    private static final String PAGE_SIZE = "pageSize";

    /** 页码 */
    private static final String PAGE_NUM = "pageNum";

    /** 排序参数 */
    private static final String ORDER = "order";

    /**
     * 判断是否需要做参数转化
     * @param methodParameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterAnnotation(PageableArg.class) != null;
    }

    /**
     * 分页参数转化
     * @param methodParameter
     * @param modelAndViewContainer
     * @param nativeWebRequest
     * @param webDataBinderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter,
            ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest,
            WebDataBinderFactory webDataBinderFactory) throws Exception {
        String pageSizeStr = nativeWebRequest.getParameter(PAGE_SIZE);
        String pageNumStr  = nativeWebRequest.getParameter(PAGE_NUM);
        // 默认从第0页开始
        int pageSize = StringUtils.isEmpty(pageSizeStr) ? 10 : Integer.parseInt(pageSizeStr);
        // 默认从每页10条记录
        int pageNum = StringUtils.isEmpty(pageNumStr) ? 0 : Integer.parseInt(pageNumStr);

        // 设置多字段排序
        String order = nativeWebRequest.getParameter(ORDER);
        Sort sort = buildSort(order);
        return PageRequest.of(pageNum, pageSize, sort);
    }

    /**
     * 解析排序参数，生成Sort对象
     * @param orderStr  sortcanshu
     * @return Sort对象
     */
    private Sort buildSort(String orderStr) {
        if (StringUtils.isEmpty(orderStr)) {
            return Sort.unsorted();
        }
        try {
            String[] orderArray = orderStr.split(",");
            List<Sort.Order> orderList = new ArrayList<>();
            for (String singleOrder: orderArray) {
                if (!singleOrder.contains(":")) {
                    continue;
                }
                String[] singleOrderArray = singleOrder.split(":");
                if (singleOrderArray.length == 2) {
                    String property = singleOrderArray[0];    // 排序字段
                    String direc = singleOrderArray[1];       // 排序规则
                    Sort.Order order = new Sort.Order(Sort.Direction.fromString(direc), property);
                    orderList.add(order);
                }
            }
            // 没有排序字段时，返回null
            if (CollectionUtils.isEmpty(orderList)) {
                return Sort.unsorted();
            }
            return Sort.by(orderList);
        } catch (Exception e) {
            logger.error("parse sort arguments error, e : ", e);
            return Sort.unsorted();
        }
    }
}
