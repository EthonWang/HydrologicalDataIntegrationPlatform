package geodatahubback.controller;

import geodatahubback.entity.JsonResult;
import geodatahubback.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/3/15
 **/

@RestController
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/redisSave/{key}/{value}")
    public JsonResult<Object> redisSave(@PathVariable("key") String key,
                                        @PathVariable("value") String value){
        redisTemplate.opsForValue().set(key, value);
        return ResponseResult.success();
    }

    @GetMapping("/redisGet/{key}")
    public JsonResult<Object> redisGet(String key){
        String value= (String) redisTemplate.opsForValue().get(key);
        return ResponseResult.success(value);
    }

}
