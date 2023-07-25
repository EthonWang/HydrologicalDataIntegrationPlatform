package geodatahubback.entity.metadata.entityAndAttribute;

import lombok.Data;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/5
 **/
@Data
public class RangeDomain extends AttributeDomainValueParent{
    private String enumDomainValue;
    private String enumDomainValueDefinition;
}
