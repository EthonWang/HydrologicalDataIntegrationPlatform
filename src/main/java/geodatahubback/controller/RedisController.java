package geodatahubback.controller;

import geodatahubback.entity.JsonResult;
import geodatahubback.utils.ResponseResult;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/3/15
 **/

@RestController
public class RedisController {

//    @Autowired
//    private RedisTemplate redisTemplate;

    @Resource
    private RedisTemplate<String, String> redisTemplate;


    @GetMapping("/saveStr/{key}/{value}")
    public JsonResult<Object> redisSave(@PathVariable("key") String key,
                                        @PathVariable("value") String value){
        redisTemplate.opsForValue().set(key, value);
        return ResponseResult.success();
    }

    @GetMapping("/saveMap/{key}")
    public JsonResult<Object> saveMap(String key){
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("field1", "value1");
        hashMap.put("field2", "value2");
        hashMap.put("field3", "value3");
        redisTemplate.opsForHash().putAll("myhash", hashMap);

        return ResponseResult.success(key,hashMap);
    }

    @GetMapping("/redisGet/{key}")
    public JsonResult<Object> redisGet(String key){
        Map<Object, Object> myhash = redisTemplate.opsForHash().entries("myhash");
        return ResponseResult.success(myhash);
    }

    @GetMapping("/hashAdd/{key}/{valueId}")
    public JsonResult<Object> hashAdd(@PathVariable("key") String key,
                                       @PathVariable("valueId") String valueId){
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", valueId);
        hashMap.put("type", valueId);
        redisTemplate.opsForHash().putAll(key, hashMap);

        return ResponseResult.success(hashMap);
    }

    @GetMapping("/ZSetAdd/{key}/{value}/{source}")
    public JsonResult<Object> saveZSet(@PathVariable("key") String key,
                                       @PathVariable("value") String value,
                                       @PathVariable("source") Double source){
        redisTemplate.opsForZSet().add(key,value,source);
        return ResponseResult.success(redisTemplate.opsForZSet().rangeWithScores(key, 0, -1));
    }

    @GetMapping("/zSetInc/{key}/{value}/{delta}")
    public JsonResult<Object> addZSet(@PathVariable("key") String key,
                                      @PathVariable("value") String value,
                                      @PathVariable("delta") Double delta){
        redisTemplate.opsForZSet().incrementScore(key,value,delta);
        return ResponseResult.success(redisTemplate.opsForZSet().rangeWithScores(key, 0, -1));
    }



}
