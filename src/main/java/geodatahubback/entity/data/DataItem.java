package geodatahubback.entity.data;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import geodatahubback.entity.metadata.GeoMetadata;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/15
 **/

@Data
public class DataItem {
    @Id
    @ApiModelProperty(value = "id",hidden = true)
    String id= IdUtil.objectId();
    String name; //数据名称

    String processable;
    String dataClass;
    String keywords;
    String description;

    String realName;
    String storeName;
    String fileWebAddress;
    String fileSize;

    String remoteDataUrl;

    String metaXmlStr; //数据描述xml的string

    JSONObject metaXmlJson;

    @ApiModelProperty(value = "创建时间",hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    Date createTime=new Date(); //创建时间
}
