package geodatahubback.entity.model;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.ir.ObjectNode;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/2/18
 **/
@Data
public class ModelItem {
    @Id
    @ApiModelProperty(value = "id",hidden = true)
    String id= IdUtil.objectId();
    String name; //模型名称

    String keywords;
    String description;

    String metaXmlStr=""; //模型描述 xml 的 string

    JSONObject metaXmlJson;

    @ApiModelProperty(value = "创建时间",hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    Date createTime=new Date(); //创建时间


}
