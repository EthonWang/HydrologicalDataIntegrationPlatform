package geodatahubback.controller;

import geodatahubback.entity.DataPageDTO;
import geodatahubback.entity.JsonResult;
import geodatahubback.entity.PageDTO;
import geodatahubback.entity.data.DataItem;
import geodatahubback.service.DataItemService;
import geodatahubback.utils.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/11/29
 **/

@RestController
@CrossOrigin
public class DataItemController {

    @Autowired
    DataItemService dataItemService;

//    @ApiOperation(value = "数据文件上传")
//    @PostMapping("/uploadFile")
//    public JsonResult uploadShp(@RequestPart("file") MultipartFile uploadShp) throws IOException {
//        String originFileName = uploadShp.getOriginalFilename(); //eg: XXX.zip
//        System.out.println(originFileName);
//        return ResponseResult.success("file okkkk");
//    }

    @ApiOperation(value = "创建数据条目")
    @PostMapping("/createDataItem")
    public JsonResult createDataItem(@RequestBody DataItem dataItem){
        return dataItemService.createDataItem(dataItem);
    }

    @ApiOperation(value = "获取数据列表--分页")
    @PostMapping("/getDataList")
    public JsonResult getDataList(@RequestBody DataPageDTO dataPageDTO){
        return dataItemService.getDataList(dataPageDTO);
    }

    @ApiOperation(value = "获取数据列表--不分页")
    @GetMapping("/getDataListNoPage")
    public JsonResult getDataListNoPage(){
        return dataItemService.getDataListNoPage();
    }

    @ApiOperation(value = "获取数据列表--脚本选择数据使用")
    @GetMapping("/getDataListProcess")
    public JsonResult getDataListProcess(){
        return dataItemService.getDataListProcess();
    }

    @ApiOperation(value = "根据id获取数据信息")
    @GetMapping("/getDataById/{id}")
    public JsonResult getDataById(@PathVariable("id") String id){
        return dataItemService.getDataById(id);
    }

    @ApiOperation(value = "删除数据条目")
    @GetMapping("/deleteDataItem/{id}")
    public JsonResult deleteDataItem(@PathVariable("id") String id){
        return dataItemService.deleteDataItem(id);
    }

}
