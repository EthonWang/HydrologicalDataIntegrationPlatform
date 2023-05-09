package geodatahubback.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.RateLimiter;
import geodatahubback.config.concurrent.RateLimit;
import geodatahubback.entity.JsonResult;
import geodatahubback.utils.ResponseResult;
import geodatahubback.entity.metadata.identification.TimePeriod;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/6
 **/

@Slf4j
@RestController
public class HelloController {

    private final RateLimiter limiter = RateLimiter.create(0.5);

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @RateLimit
    @CrossOrigin
    @ApiOperation(value = "hello")
    @GetMapping(value = "/hello")
    public JsonResult hello(){
        return ResponseResult.success("OKOOK");
    }

    @CrossOrigin
    @ApiOperation(value = "hello并发")
    @GetMapping(value = "/helloAll")
    public JsonResult helloAll(HttpServletResponse response) throws IOException {
        boolean tryAcquire = limiter.tryAcquire(2000, TimeUnit.MILLISECONDS);

        if (!tryAcquire) {
            log.warn("进入服务降级，时间{}", LocalDateTime.now().format(dtf));
            response.sendError(500);
            return ResponseResult.error();
        }else{
            log.info("获取令牌成功，时间{}", LocalDateTime.now().format(dtf));
            response.setStatus(200);
            return ResponseResult.success();
        }
    }

    @ApiOperation(value = "hello")
    @PostMapping(value = "/post")
    public JsonResult post(@RequestBody TimePeriod timePeriod){
        System.out.println(timePeriod.toString());
        return ResponseResult.success();
    }

    @ApiOperation(value = "helloGetCookie")
    @GetMapping(value = "/helloSetCookie")
    public JsonResult helloSetCookie(HttpServletResponse response){
        //创建cookie,cookie存储的是 一个字符串，并且每一个对象只能存储一个key-value
        Cookie cookie = new Cookie("username", "wangyijie");
        //设置cookie生效范围。即在哪条路径下有效，此路径及子路径
        cookie.setPath("/");
        //设置生存的时间。默认是会话级别，存在内存，关闭浏览器就死。设置存活时间后会保存到硬盘
        cookie.setMaxAge(60 * 10);//10分钟
        //发送cookie，添加到response，浏览器响应的时候就带着，在响应标头responseHeaders里
        response.addCookie(cookie);

        return ResponseResult.success();
    }

    @GetMapping(path = "/helloGetCookie")
    public JsonResult getCookie(@CookieValue("username") String c) {//仅获取名为code的cookie，取出来赋值给c
        System.out.println(c);
        return ResponseResult.success(c);
    }

    @ApiOperation(value = "helloSetSession")
    @GetMapping(value = "/helloSetSession")
    public JsonResult helloSetSession(HttpSession session){
        session.setAttribute("id", 1);
        session.setAttribute("name", "wangyije-session");
        return ResponseResult.success();
    }

    @GetMapping(path = "/helloGetSession")
    public JsonResult helloGetSession(HttpSession session) {
        JSONObject o=new JSONObject();
        o.put("id",session.getAttribute("id"));
        o.put("name",session.getAttribute("name"));

        return ResponseResult.success(o);
    }




}
