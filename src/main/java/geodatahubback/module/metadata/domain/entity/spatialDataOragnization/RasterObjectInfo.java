package geodatahubback.module.metadata.domain.entity.spatialDataOragnization;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/5
 **/
@Data
public class RasterObjectInfo {
    private String rasterObjectType;
    private Integer rowCount;
    private Integer columnCount;
    private Integer verticalCount;
}
