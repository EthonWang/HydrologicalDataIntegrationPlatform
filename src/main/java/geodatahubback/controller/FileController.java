package geodatahubback.controller;

import geodatahubback.entity.JsonResult;
import geodatahubback.entity.metadata.identification.TimePeriod;
import geodatahubback.service.FileService;
import geodatahubback.utils.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/4/19
 **/

@CrossOrigin
@RestController
public class FileController {

    @Autowired
    FileService fileService;

    @ApiOperation(value = "统一上传文件")
    @PostMapping(value = "/uploadFile")
    public JsonResult uploadFile(@RequestPart("upFile") MultipartFile upFile){
        return fileService.uploadFile(upFile);
    }


}
