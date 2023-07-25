package geodatahubback.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import geodatahubback.entity.UserData;
import geodatahubback.entity.JsonResult;
import geodatahubback.entity.form.UserDataAdd;
import geodatahubback.service.UserDataService;
import geodatahubback.mapper.UserDataMapper;
import geodatahubback.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author WYJ
* @description 针对表【user_data】的数据库操作Service实现
* @createDate 2023-05-05 10:48:06
*/
@Service
public class UserDataServiceImpl extends ServiceImpl<UserDataMapper, UserData>
    implements UserDataService{

    @Autowired
    UserDataMapper userDataMapper;
    public JsonResult createUserData(UserDataAdd userDataAdd) {
        try{
            UserData userData=new UserData();
            BeanUtil.copyProperties(userDataAdd,userData);
            userDataMapper.insert(userData);
            return ResponseResult.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.error();
        }
    }

    public JsonResult getUserDataList(String userId) {
        try{
            QueryWrapper<UserData> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id",userId);
            List<UserData> userDataList = userDataMapper.selectList(wrapper);
            return ResponseResult.success(userDataList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.error();
        }
    }

    public JsonResult deleteUserData(Long id) {
        try{
            userDataMapper.deleteById(id);
            return ResponseResult.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.error();
        }
    }
}




