package geodatahubback.mapper;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import geodatahubback.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wyjq
 * @since 2023-04-14
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    User findMyUser(String email);

    User findMyUser2(String password,String email);
    User findMyUser3(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

    List<User> findByEmailAndName(@Param("email") String email, @Param("name") String name);


}
