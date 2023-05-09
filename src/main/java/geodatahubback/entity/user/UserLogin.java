package geodatahubback.entity.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/4/14
 **/
@Data
public class UserLogin {

    @NotBlank(message = "email不能为空")
    private String email;
    @NotBlank(message = "password不能为空")
    private String password;
}
