package com.example.demo.config;

import com.example.demo.base.pre.PageableArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.Charset;
import java.util.List;


@Configuration
public class WebConfig implements WebMvcConfigurer  {

    /**
     * 重写addCorsMappings方法实现配置cors跨域限制等。
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("*//**")//配置允许跨域的路径
                .allowedOrigins("*")//配置允许访问的跨域资源的请求域名
                .allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "TRACE")//配置允许访问该跨域资源服务器的请求方法，如：POST、GET、PUT、DELETE等
                .allowedHeaders("*"); //配置允许请求header的访问，如 ：X-TOKEN
    }

    /**
     * 添加分页解析器
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new PageableArgumentResolver());
    }

    /**
     * 重写addResourceHandlers来配置路径访问等，Spring Boot中默认使用ResourceHttpRequestHandler来映射类路径下的/static、/public、/resources和/METAINF/resources目录或者ServletContext的根目录中的静态文件直接映射为 /****。
     * @param registry
     */
//    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //静态资源路径 css,js,img等
//        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
//        //视图
//        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
//        //mapper.xml
//        registry.addResourceHandler("/mapper/**").addResourceLocations("classpath:/mapper/");
//        super.addResourceHandlers(registry);
    }


    /**
     * 重写addInterceptors() 方法来配置拦截器（实现了HandlerInterceptor接口）等。这里实现的addInterceptors方法对应的是xml文件中<mvc:interceptors>配置。
     * @param registry
     */
    //    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        //todo 添加自定义拦截器 myInteceptor实现接口HandlerInterceptor 不要使用WebContentInterceptor
        registry.addInterceptor(new WebContentInterceptor()) //添加自定义拦截器 myInteceptor实现接口HandlerInterceptor
                .addPathPatterns("/**") //添加拦截路径
                .excludePathPatterns(//排除拦截路径
                        "/statics/**/*.*");
//        super.addInterceptors(registry);
    }

    /**
     * 重写addViewControllers方法配置view视图映射等。
     * @param registry
     */
//    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/home");//默认视图跳转
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        super.addViewControllers(registry);
    }

    /**
     * 重写configureMessageConverters方法来对消息进行转换。MessageConverter用于对http请求的返回结果进行转换，以fastjon、编码格式application/json;charset=UTF-8进行转换。
     * @param converters
     */
//    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter converter  = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        converters.add(converter);
    }

    /**
     * 重写addFormatters方法来添加数据格式化器，比如将字符串转换为日期类型，可通过DateFormatter类来实现自动转换。
     *
     * formatters和converters用于对日期格式进行转换，默认已注册了Number和Date类型的formatters，支持@NumberFormat和@DateTimeFormat注解，需要自定义formatters和converters可以实现addFormatters方法。
     * @param registry
     */
//    @Override
    public void addFormatters(FormatterRegistry registry) {
//        super.addFormatters(registry);
        registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
    }

    /**
     * 重写configureViewResolvers()方法来配置视图解析器，主要是配置视图的前后缀。
     * @param registry
     */
//    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".html");
        viewResolver.setCache(false);
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setOrder(0);
        registry.viewResolver(viewResolver);
//        super.configureViewResolvers(registry);
    }

    //简化版本
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.jsp("/", ".jsp");
//    }

}

