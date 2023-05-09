package geodatahubback.entity.script;

import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/3/20
 **/
@Data
public class ScriptItem {
    @Id
    @ApiModelProperty(value = "id",hidden = true)
    String id= IdUtil.objectId();
    String name;

    @ApiModelProperty(value = "创建时间",hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    Date createTime=new Date(); //创建时间

    String description;

    String fileName;

    List<ArgumentDef> arguments;

    String pythonVersion;

}
