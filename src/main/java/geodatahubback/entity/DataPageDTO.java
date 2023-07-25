package geodatahubback.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/4/22
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataPageDTO extends PageDTO {

    @ApiModelProperty(value = "数据类别", example = "1")
    private String dataClass;

}

