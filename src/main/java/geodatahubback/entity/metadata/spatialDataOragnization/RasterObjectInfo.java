package geodatahubback.entity.metadata.spatialDataOragnization;

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
