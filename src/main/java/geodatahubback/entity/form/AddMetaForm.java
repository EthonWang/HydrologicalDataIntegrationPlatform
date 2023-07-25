package geodatahubback.entity.form;

import geodatahubback.entity.metadata.dataQuality.DataQuality;
import geodatahubback.entity.metadata.distribution.Distribution;
import geodatahubback.entity.metadata.entityAndAttribute.EntityAndAttribute;
import geodatahubback.entity.metadata.identification.Identification;
import geodatahubback.entity.metadata.metadataReference.MetadataReference;
import geodatahubback.entity.metadata.spatialDataOragnization.SpatialDataOrganization;
import geodatahubback.entity.metadata.spatialReference.SpatialReference;
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
