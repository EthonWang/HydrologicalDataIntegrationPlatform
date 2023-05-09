package geodatahubback.controller;


import geodatahubback.entity.JsonResult;
import geodatahubback.entity.metadata.identification.TimePeriod;
import geodatahubback.entity.user.UserAdd;
import geodatahubback.entity.user.UserLogin;
import geodatahubback.service.UserService;
import geodatahubback.utils.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wyjq
 * @since 2023-04-14
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @ApiOperation(value = "注册")
    @PostMapping(value = "/register")
    public JsonResult register(@RequestBody @Validated UserAdd userAdd){
        return userService.register(userAdd.getName(),userAdd.getEmail(),userAdd.getPassword());
    }

    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public JsonResult login( @RequestBody @Validated UserLogin userLogin){
        return userService.login(userLogin.getEmail(),userLogin.getPassword());
    }
}

