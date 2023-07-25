package geodatahubback.controller;

import geodatahubback.entity.DataPageDTO;
import geodatahubback.entity.JsonResult;
import geodatahubback.entity.PageDTO;
import geodatahubback.entity.model.ModelItem;
import geodatahubback.service.ModelItemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/2/18
 **/
@CrossOrigin
@RestController
public class ModelItemController {

    @Autowired
    ModelItemService modelItemService;

    @ApiOperation(value = "获取模型列表")
    @GetMapping("/getModelList")
    public JsonResult getModelList(){
        return modelItemService.getModelList();
    }


    @ApiOperation(value = "根据id获取模型信息")
    @GetMapping("/getModelById/{id}")
    public JsonResult getModelById(@PathVariable("id") String id){
        return modelItemService.getModelById(id);
    }

    @ApiOperation(value = "创建模型条目")
    @PostMapping("/createModelItem")
    public JsonResult createModelItem(@RequestBody ModelItem modelItem){
        return modelItemService.createModelItem(modelItem);
    }

    @ApiOperation(value = "删除模型条目")
    @GetMapping("/deleteModelItem/{id}")
    public JsonResult deleteModelItem(@PathVariable("id") String id){
        return modelItemService.deleteModelItem(id);
    }

}
