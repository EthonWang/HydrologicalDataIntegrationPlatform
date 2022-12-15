package geodatahubback.module.metadata.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import geodatahubback.module.metadata.domain.entity.dataQuality.DataQuality;
import geodatahubback.module.metadata.domain.entity.distribution.Distribution;
import geodatahubback.module.metadata.domain.entity.entityAndAttribute.EntityAndAttribute;
import geodatahubback.module.metadata.domain.entity.identification.Identification;
import geodatahubback.module.metadata.domain.entity.metadataReference.MetadataReference;
import geodatahubback.module.metadata.domain.entity.spatialDataOragnization.SpatialDataOrganization;
import geodatahubback.module.metadata.domain.entity.spatialReference.SpatialReference;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/3
 **/
@Data
public class GeoMetadata {
    @Id
    private String id;

    private Identification identification;

    private DataQuality dataQuality;

    private SpatialDataOrganization spatialDataOrganization;

    private SpatialReference spatialReference;

    private EntityAndAttribute entityAndAttribute;

    private Distribution distribution;

    private MetadataReference metadataReference;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
