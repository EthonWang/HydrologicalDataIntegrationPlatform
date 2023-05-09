package geodatahubback.service;

import geodatahubback.dao.ModelItemDao;
import geodatahubback.entity.JsonResult;
import geodatahubback.entity.PageDTO;
import geodatahubback.entity.model.ModelItem;
import geodatahubback.utils.ParseXMLToJSON;
import geodatahubback.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/2/18
 **/
@Service
public class ModelItemService {

    @Autowired
    ModelItemDao modelItemDao;

    public JsonResult getModelList() {

        List<ModelItem> all = modelItemDao.findAll();
        return ResponseResult.success(all);
    }

    public JsonResult getModelById(String id) {
        ModelItem modelItem = modelItemDao.findById(id).get();
        return ResponseResult.success(modelItem);
    }

    public JsonResult createModelItem(ModelItem modelItem) {
        modelItem.setMetaXmlJson(ParseXMLToJSON.parse(modelItem.getMetaXmlStr()));
        modelItemDao.save(modelItem);
        return ResponseResult.success();
    }

    public JsonResult deleteModelItem(String id) {
        modelItemDao.deleteById(id);
        return ResponseResult.success();
    }
}
