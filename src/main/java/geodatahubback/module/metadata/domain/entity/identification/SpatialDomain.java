package geodatahubback.module.metadata.domain.entity.identification;

import lombok.Data;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/5
 **/
@Data
public class SpatialDomain {
    private Double westBoundingCoordinate;
    private Double eastBoundingCoordinate;
    private Double northBoundingCoordinate;
    private Double southBoundingCoordinate;
}
