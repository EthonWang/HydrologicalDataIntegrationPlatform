package geodatahubback.module.metadata.domain.form;

import geodatahubback.module.metadata.domain.entity.dataQuality.DataQuality;
import geodatahubback.module.metadata.domain.entity.distribution.Distribution;
import geodatahubback.module.metadata.domain.entity.entityAndAttribute.EntityAndAttribute;
import geodatahubback.module.metadata.domain.entity.identification.Identification;
import geodatahubback.module.metadata.domain.entity.metadataReference.MetadataReference;
import geodatahubback.module.metadata.domain.entity.spatialDataOragnization.SpatialDataOrganization;
import geodatahubback.module.metadata.domain.entity.spatialReference.SpatialReference;
import lombok.Data;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/6
 **/
@Data
public class AddMetaForm {
    private Identification identification;

    private DataQuality dataQuality;

    private SpatialDataOrganization spatialDataOrganization;

    private SpatialReference spatialReference;

    private EntityAndAttribute entityAndAttribute;

    private Distribution distribution;

    private MetadataReference metadataReference;

}
