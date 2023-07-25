package geodatahubback.service;

import geodatahubback.entity.JsonResult;
import geodatahubback.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyjq
 * @since 2023-04-14
 */
public interface UserService extends IService<User> {

    JsonResult register(String name, String email, String password);


    JsonResult login(String email, String password);




}
