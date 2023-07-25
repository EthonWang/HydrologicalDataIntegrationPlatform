package geodatahubback.config;

import jwtspringbootstarterautoconfigure.interceptor.JwtCustomInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/7/7
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    JwtCustomInterceptor jwtCustomInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //添加映射路径
        registry.addMapping("/**")
                //是否发送Cookie
                .allowCredentials(true)
                //设置放行哪些原始域   SpringBoot2.4.4下低版本使用.allowedOrigins("*")
                .allowedOriginPatterns("*")
                //放行哪些请求方式
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
                //.allowedMethods("*") //或者放行全部
                //放行哪些原始请求头部信息
                .allowedHeaders("*")
                //暴露哪些原始请求头部信息
                .exposedHeaders("*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[]  swaggerPatterns =  {
                "/swagger-resources/**",
                "/webjars/**",
                "/v2/**",
                "/swagger-ui.html/**",
                "/doc.html/**" };
        registry.addInterceptor(jwtCustomInterceptor)
                .excludePathPatterns(swaggerPatterns)
                .addPathPatterns("/**");
    }
}