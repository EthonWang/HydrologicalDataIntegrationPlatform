package geodatahubback.module.system.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import geodatahubback.module.metadata.domain.entity.GeoMetadata;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/15
 **/

@Data
public class DataItem {

    @Id
    String id;

    String name;
    Long size;
    String relativePath;

    GeoMetadata geoMetadata;


    @ApiModelProperty(required = true,example = "1997-01-01 00:00:00",value = "日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;
}
