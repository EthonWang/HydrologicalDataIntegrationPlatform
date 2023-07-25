package geodatahubback.service.impl;

import com.alibaba.druid.wall.WallProvider;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import geodatahubback.entity.JsonResult;
import geodatahubback.entity.User;
import geodatahubback.mapper.UserMapper;
import geodatahubback.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import geodatahubback.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.QueryMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyjq
 * @since 2023-04-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    //极简实现
    @Override
    public JsonResult register(String name, String email, String password) {
        try {
            QueryWrapper<User> wrapper=new QueryWrapper<>();
            wrapper.eq("email",email);
            User user1 = userMapper.selectOne(wrapper);
            if (user1!=null){
                return ResponseResult.error("用户已存在");
            }

            User user=new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            userMapper.insert(user);
            return ResponseResult.success("注册成功");
        }catch (Exception e){
            return ResponseResult.error("注册失败");
        }
    }

    //极简实现
    @Override
    public JsonResult login(String email, String password) {
        try{
            User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getEmail, email));

            if (user==null){
                return ResponseResult.error("用户不存在");
            }
            if(!password.equals(user.getPassword())){
                return ResponseResult.error("密码不正确");
            }
            user.setPassword("");
            return ResponseResult.success(user);
        }catch (Exception e){
            return ResponseResult.error("登录失败");
        }

    }
}
