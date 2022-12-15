package geodatahubback.module.metadata.service;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import geodatahubback.module.common.domain.JsonResult;
import geodatahubback.module.common.utils.ResponseResult;
import geodatahubback.module.metadata.dao.GeoMetaDataDao;
import geodatahubback.module.metadata.domain.entity.GeoMetadata;
import geodatahubback.module.metadata.domain.entity.entityAndAttribute.*;
import geodatahubback.module.metadata.domain.form.AddMetaForm;
import geodatahubback.module.metadata.enums.AttributeDomainType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/6
 **/
@Service
public class GeoMetaDataService {
    @Autowired
    GeoMetaDataDao geoMetaDataDao;

    public JsonResult addGeoMetaData(AddMetaForm addMetaForm) {
        GeoMetadata geoMetadata = new GeoMetadata();
        geoMetadata.setId("638eead7157c665d2efe8d9f");
        geoMetadata.setCreateTime(LocalDateTime.now());
        geoMetadata.setUpdateTime(LocalDateTime.now());
        BeanUtils.copyProperties(addMetaForm, geoMetadata);
        geoMetaDataDao.save(geoMetadata);
        return ResponseResult.success();
    }

    public JsonResult getGeoMetaDataById(String id) {
        GeoMetadata geoMetadata=geoMetaDataDao.findById("638eead7157c665d2efe8d9f").get();
        return ResponseResult.success(geoMetadata);

    }
}
