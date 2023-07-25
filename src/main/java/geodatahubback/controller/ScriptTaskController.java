package geodatahubback.controller;

import cn.hutool.core.util.IdUtil;
import geodatahubback.entity.JsonResult;
import geodatahubback.service.ScriptTaskService;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.C;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/11/29
 **/

@CrossOrigin
@RestController
public class ScriptTaskController {

    @Autowired
    ScriptTaskService scriptTaskService;

    @ApiOperation(value = "获取脚本执行任务列表")
    @GetMapping("/getScriptTaskList/{userId}")
    public JsonResult getScriptTaskList(@PathVariable("userId") String userId){
        return scriptTaskService.getScriptTaskList(userId);
    }

}
