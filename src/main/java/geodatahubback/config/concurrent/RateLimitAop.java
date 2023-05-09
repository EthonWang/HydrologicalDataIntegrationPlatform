package geodatahubback.config.concurrent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.RateLimiter;
import com.rabbitmq.tools.json.JSONUtil;
import geodatahubback.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/5/9
 **/

@Slf4j
@Component
@Scope
@Aspect
public class RateLimitAop {

    @Autowired
    private HttpServletResponse response;

    private RateLimiter rateLimiter = RateLimiter.create(0.5); //比如说，我这里设置"并发数"为5

    @Pointcut("@annotation(geodatahubback.config.concurrent.RateLimit)")
    public void serviceLimit() {

    }

    @Around("serviceLimit()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Boolean flag = rateLimiter.tryAcquire(1000, TimeUnit.MILLISECONDS);
        Object obj = null;
        try {
            if (flag) {
                obj = joinPoint.proceed();
            }else{
                //1.1获取目标对象对应的字节码对象
                Class<?> targetCls=joinPoint.getTarget().getClass();

                //1.2.1 获取方法签名信息从而获取方法名和参数类型
                Signature signature=joinPoint.getSignature();

                String result = "目前请求量过大，请稍后再试";
                log.warn("限流："+signature);
                output(response, result);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
//        System.out.println("flag=" + flag + ",obj=" + obj);
        return obj;
    }

    public void output(HttpServletResponse response, String msg) throws IOException {

        ServletOutputStream outputStream = null;
        try {
            response.setStatus(503);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(JSON.toJSONString(ResponseResult.error(msg)));
            response.getWriter().flush();

//            outputStream = response.getOutputStream();
//            outputStream.write(msg.getBytes("UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            outputStream.flush();
//            outputStream.close();
        }
    }
}