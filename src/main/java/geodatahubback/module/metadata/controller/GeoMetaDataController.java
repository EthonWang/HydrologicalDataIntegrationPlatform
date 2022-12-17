package geodatahubback.module.metadata.controller;

import geodatahubback.module.common.domain.JsonResult;
import geodatahubback.module.metadata.domain.form.AddMetaForm;
import geodatahubback.module.metadata.service.GeoMetaDataService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/6
 **/
@RestController
public class GeoMetaDataController {

    @Autowired
    GeoMetaDataService geoMetaDataService;


    //元数据规范接口




    //业务逻辑
    @ApiOperation(value = "新增元数据")
    @PostMapping(value = "/geoMetaData/add")
    public JsonResult addGeoMetaData(@RequestBody AddMetaForm addMetaForm){

        return geoMetaDataService.addGeoMetaData(addMetaForm);
    }

    @ApiOperation(value = "查询某元数据")
    @GetMapping(value = "/geoMetaData/get/{id}")
    public JsonResult getGeoMetaDataById(@PathVariable String id){

        return geoMetaDataService.getGeoMetaDataById(id);
    }

    @ApiOperation(value = "查询所有某元数据")
    @GetMapping(value = "/geoMetaData/query")
    public JsonResult getAllGeoMetaData(){

        return geoMetaDataService.getAllGeoMetaData();
    }


}
