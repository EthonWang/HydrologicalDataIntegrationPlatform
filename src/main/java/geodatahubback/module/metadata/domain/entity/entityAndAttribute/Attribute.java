package geodatahubback.module.metadata.domain.entity.entityAndAttribute;

import geodatahubback.module.metadata.domain.entity.spatialDataOragnization.RasterObjectInfo;
import geodatahubback.module.metadata.enums.AttributeDomainType;
import lombok.Data;

import javax.security.auth.message.callback.PrivateKeyCallback;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/5
 **/
@Data
public class Attribute {
    private String attributeLabel;
    private String attributeDefinition;

    private AttributeDomainType domainType;
    //以下为四选一
    private EnumeratedDomain enumeratedDomain;
    private RangeDomain rangeDomain;
    private CodeSetDomain codeSetDomain;
    private UnrepresentableDomain unrepresentableDomain;
}
