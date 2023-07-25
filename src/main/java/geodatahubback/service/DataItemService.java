package geodatahubback.service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import geodatahubback.dao.DataItemDao;
import geodatahubback.dao.DataItemDao;
import geodatahubback.entity.DataPageDTO;
import geodatahubback.entity.JsonResult;
import geodatahubback.entity.PageDTO;
import geodatahubback.entity.data.DataItem;
import geodatahubback.utils.ParseXMLToJSON;
import geodatahubback.utils.ResponseResult;
import org.apache.velocity.runtime.directive.Parse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/2/18
 **/
@Service
public class DataItemService {
    @Autowired
    DataItemDao dataItemDao;

    @Autowired
    CommonService commonService;

    public JsonResult getDataList(DataPageDTO dataPageDTO) {
        Pageable pageable = commonService.getPageable(dataPageDTO);
        Page<DataItem> all=null;
        if(dataPageDTO.getDataClass().equals("all")){
            all= dataItemDao.findAllByNameLikeIgnoreCaseAndKeywordsLikeIgnoreCase(
                    dataPageDTO.getSearchText(),dataPageDTO.getSearchText(),pageable);
        }else{
            all= dataItemDao.findAllByDataClassAndNameLikeIgnoreCaseAndKeywordsLikeIgnoreCase(
                    dataPageDTO.getDataClass(),dataPageDTO.getSearchText(),dataPageDTO.getSearchText(),pageable);
        }

//        all=dataItemDao.findAll(pageable);

//        Page<DataItem> all = dataItemDao.findAll(pageable);
        return ResponseResult.success(all);
    }

    public JsonResult getDataListNoPage() {
        try{
            List<DataItem> dataItemList= dataItemDao.findAll();
            return ResponseResult.success(dataItemList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.error();
        }

    }

    public JsonResult getDataById(String id) {
        DataItem dataItem = dataItemDao.findById(id).get();
        return ResponseResult.success(dataItem);
    }

    public JsonResult createDataItem(DataItem dataItem) {
        dataItem.setMetaXmlJson(ParseXMLToJSON.parse(dataItem.getMetaXmlStr()));
        dataItemDao.save(dataItem);
        return ResponseResult.success();
    }

    public JsonResult deleteDataItem(String id) {
        dataItemDao.deleteById(id);
        return ResponseResult.success();
    }

    public JsonResult getDataListProcess() {
        try{
            List<DataItem> dataItemList= dataItemDao.findAllByProcessable("true");
            return ResponseResult.success(dataItemList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.error();
        }
    }


}
