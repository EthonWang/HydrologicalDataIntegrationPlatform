package geodatahubback.service;

import geodatahubback.entity.UserData;
import com.baomidou.mybatisplus.extension.service.IService;
import geodatahubback.entity.JsonResult;
import geodatahubback.entity.form.UserDataAdd;

/**
* @author WYJ
* @description 针对表【user_data】的数据库操作Service
* @createDate 2023-05-05 10:48:06
*/
public interface UserDataService extends IService<UserData> {

    public JsonResult createUserData(UserDataAdd userDataAdd);

    public JsonResult getUserDataList(String id);

    public JsonResult deleteUserData(Long id);
}
