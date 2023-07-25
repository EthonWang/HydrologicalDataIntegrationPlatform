package geodatahubback.service;

import geodatahubback.entity.CommonMetaAdd;
import geodatahubback.entity.JsonResult;
import geodatahubback.entity.data.DataMeta;
import geodatahubback.utils.ParseXMLToJSON;
import geodatahubback.utils.ResponseResult;
import geodatahubback.dao.DataMetaDao;
import geodatahubback.entity.metadata.GeoMetadata;
import geodatahubback.entity.form.AddMetaForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/6
 **/
@Service
public class DataMetaService {

    @Autowired
    DataMetaDao dataMetaDao;


    public JsonResult addDataMeta(String name, String xmlStr) {
        DataMeta dataMeta=new DataMeta();
        dataMeta.setMetaXml(xmlStr);
        dataMeta.setMetaJson(ParseXMLToJSON.parse(xmlStr));
        dataMeta.setName(name);

        dataMetaDao.save(dataMeta);
        return ResponseResult.success("添加成功");
    }


    public JsonResult getGeoMetaDataById(String id) {
        DataMeta dataMeta= dataMetaDao.findById(id).get();
        return ResponseResult.success(dataMeta);
    }

    public JsonResult getAllGeoMetaData() {
        List<DataMeta> dataMetaList = dataMetaDao.findAll();
        return ResponseResult.success(dataMetaList);
    }

}
