package geodatahubback.controller;

import geodatahubback.entity.CommonMetaAdd;
import geodatahubback.entity.JsonResult;
import geodatahubback.entity.form.AddMetaForm;
import geodatahubback.service.DataMetaService;
import geodatahubback.utils.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.rtf.RTFEditorKit;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/6
 **/

@RestController
public class DataMetaController {

    @Autowired
    DataMetaService dataMetaService;


    //元数据规范接口
    @ApiOperation(value = "新增元数据")
    @PostMapping(value = "/dataMeta/add2")
    public JsonResult addDataMeta(@RequestParam("name") String name,
                                  @RequestParam("xmlStr") String xmlStr){
        return dataMetaService.addDataMeta(name,xmlStr);
//        return dataMetaService.addDataMeta(commonMetaAdd);
    }

//    @ApiOperation(value = "新增元数据")
//    @PostMapping(value = "/dataMeta/add")
//    public JsonResult addDataMeta(@RequestBody CommonMetaAdd commonMetaAdd){
//        return dataMetaService.addDataMeta(commonMetaAdd);
//    }

    @ApiOperation(value = "查询某元数据")
    @GetMapping(value = "/dataMeta/get/{id}")
    public JsonResult getGeoMetaDataById(@PathVariable String id){

        return dataMetaService.getGeoMetaDataById(id);
    }

    @ApiOperation(value = "查询所有某元数据")
    @GetMapping(value = "/dataMeta/getAll")
    public JsonResult getAllGeoMetaData(){
        return dataMetaService.getAllGeoMetaData();
    }


}
