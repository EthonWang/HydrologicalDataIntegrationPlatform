package geodatahubback.module.metadata.domain.entity.entityAndAttribute;

import lombok.Data;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/5
 **/
@Data
public class CodeSetDomain extends AttributeDomainValueParent {
    private String codeSetName;
    private String codeSetSource;
}
