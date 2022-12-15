package geodatahubback.module.metadata.domain.entity.identification;

import geodatahubback.module.metadata.domain.entity.entityAndAttribute.EnumeratedDomain;
import lombok.Data;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/5
 **/
@Data
public class Identification {
    private String citation;

    private Description description;

    private TimePeriod timePeriod;

    private Status Status;

    private SpatialDomain spatialDomain;

    private String keywords;

    private String accessConstraints;

    private String useConstraints;

    private EnumeratedDomain eeee;
}
