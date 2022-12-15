package geodatahubback.module.metadata.domain.entity.spatialReference;

import lombok.Data;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/5
 **/
@Data
public class SpatialReference {
    private String horizontalCoordSystemDefinition;
    private  String verticalCoordSystemDefinition;
}
