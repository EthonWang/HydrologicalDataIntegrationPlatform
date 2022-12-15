package geodatahubback.module.metadata.controller;

import geodatahubback.module.common.domain.JsonResult;
import geodatahubback.module.common.utils.ResponseResult;
import geodatahubback.module.metadata.domain.entity.identification.TimePeriod;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/6
 **/
@RestController
public class HelloController {
    @ApiOperation(value = "hello")
    @GetMapping(value = "/hello")
    public JsonResult hello(){
        return ResponseResult.success();
    }

    @ApiOperation(value = "hello")
    @PostMapping(value = "/post")
    public JsonResult post(@RequestBody TimePeriod timePeriod){
        System.out.println(timePeriod.toString());
        return ResponseResult.success();
    }
}
