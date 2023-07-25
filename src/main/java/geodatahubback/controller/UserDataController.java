package geodatahubback.controller;


import geodatahubback.entity.JsonResult;
import geodatahubback.entity.form.UserDataAdd;
import geodatahubback.service.UserDataService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserDataController {

    @Autowired
    UserDataService userDataService;


    @ApiOperation(value = "创建用户数据")
    @PostMapping(value = "/createUserData")
    public JsonResult createUserData(@RequestBody UserDataAdd userDataAdd){
        return userDataService.createUserData(userDataAdd);
    }

    @ApiOperation(value = "获取用户数据")
    @GetMapping(value = "/getUserDataList/{userId}")
    public JsonResult getUserDataList(@PathVariable("userId") String userId){
        return userDataService.getUserDataList(userId);
    }

    @ApiOperation(value = "删除用户数据")
    @GetMapping(value = "/deleteUserData/{dataId}")
    public JsonResult deleteUserData(@PathVariable("dataId") Long dataId){
        return userDataService.deleteUserData(dataId);
    }

}

