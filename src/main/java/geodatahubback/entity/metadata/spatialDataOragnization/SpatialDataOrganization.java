package geodatahubback.entity.metadata.spatialDataOragnization;

import lombok.Data;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/5
 **/
@Data
public class SpatialDataOrganization {
    private String spatialObjectsSystem;
    private PointAndVectorObjectInfo pointAndVectorObjectInfo;
    private RasterObjectInfo rasterObjectInfo;

}
