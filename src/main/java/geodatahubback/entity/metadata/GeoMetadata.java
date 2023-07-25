package geodatahubback.entity.metadata;

import com.fasterxml.jackson.annotation.JsonFormat;
import geodatahubback.entity.metadata.distribution.Distribution;
import geodatahubback.entity.metadata.entityAndAttribute.EntityAndAttribute;
import geodatahubback.entity.metadata.metadataReference.MetadataReference;
import geodatahubback.entity.metadata.spatialDataOragnization.SpatialDataOrganization;
import geodatahubback.entity.metadata.spatialReference.SpatialReference;
import geodatahubback.entity.metadata.dataQuality.DataQuality;
import geodatahubback.entity.metadata.identification.Identification;
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
