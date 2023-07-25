package geodatahubback.entity.metadata.entityAndAttribute;

import geodatahubback.enums.AttributeDomainType;
import lombok.Data;

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
