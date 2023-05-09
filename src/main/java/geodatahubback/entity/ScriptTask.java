package geodatahubback.entity;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/2/18
 **/
@Data
public class ScriptTask {
    @Id
    @ApiModelProperty(value = "id",hidden = true)
    String id;
    String name; //模型名称

    String userId;

    @ApiModelProperty(value = "创建时间",hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    Date createTime=new Date(); //创建时间

    String status;

    String fileWebAddress;


}
