package geodatahubback.entity.script;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/3/28
 **/
@Data
public class ScriptExecDTO {
    String scriptId;
    String userId;
    List<ArgumentComp> arguments;
}
