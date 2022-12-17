package geodatahubback.module.system.controller;

import cn.hutool.core.util.IdUtil;
import geodatahubback.module.common.domain.JsonResult;
import geodatahubback.module.common.utils.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/11/29
 **/

@RestController
@CrossOrigin
public class DataItemController {




    @ApiOperation(value = "数据文件上传")
    @PostMapping("/uploadFile")
    public JsonResult uploadShp(@RequestPart("file") MultipartFile uploadShp) throws IOException {
        String originFileName = uploadShp.getOriginalFilename(); //eg: XXX.zip
        System.out.println(originFileName);
        return ResponseResult.success("file okkkk");
    }

    @ApiOperation(value = "创建数据条目")
    @PostMapping("/dataItem/add")
    public JsonResult addDataItem(@RequestPart("file") MultipartFile uploadShp) throws IOException {
        String originFileName = uploadShp.getOriginalFilename(); //eg: XXX.zip
        System.out.println(originFileName);
        return ResponseResult.success("file okkkk");
    }

}
