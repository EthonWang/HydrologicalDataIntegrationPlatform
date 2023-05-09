package geodatahubback;

import cn.hutool.core.io.file.FileReader;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;
//import geodatahubback.utils.ParseXMLToJSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import geodatahubback.entity.JsonResult;
import geodatahubback.entity.User;
import geodatahubback.mapper.UserMapper;
import geodatahubback.service.DataMetaService;
import geodatahubback.service.impl.UserServiceImpl;
import geodatahubback.utils.ParseXMLToJSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@SpringBootTest
class GeoDataHubBackApplicationTests {

//    @Resource
//    UserMapper userMapper;
    @Autowired
DataMetaService dataMetaService;

    @Autowired
    private DataSource dataSource;

//    @Autowired
//    private UserMapper userMapper;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads3() throws IOException {
        //查询
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.likeRight("name","n");
        wrapper.orderByDesc("email");
        wrapper.select("id","name","email");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);

        //更新
        UpdateWrapper<User> updateWrapper=new UpdateWrapper<>();
        updateWrapper.likeLeft("name","n");
        updateWrapper.set("password","123456");
        userMapper.update(null,updateWrapper);

        userMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    void test_lambda(){
        LambdaQueryWrapper<User> lam=new LambdaQueryWrapper<>();
        lam.eq(User::getEmail,"371252847@qq.com");
        userMapper.selectList(lam).forEach(System.out::println);
    }

    @Test
    void test_xml(){
//        User myUser = userMapper.findMyUser("371252847@qq.com");

//        User myUser = userMapper.findMyUser2("123456","371252847@qq.com");
        List<User> lists = userMapper.findByEmailAndName("371252847@qq.com", "wangyijie");
        lists.forEach(System.out::println);
//        System.out.println(myUser);
    }

    @Test
    void testPage(){
        IPage<User> iPage= new Page<>();
        iPage.setCurrent(1);
        iPage.setSize(2);
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.likeRight("name","n");
        wrapper.orderByDesc("email");
        IPage<User> userIPage = userMapper.selectPage(iPage,wrapper);
        System.out.println(userIPage.getRecords());
//        lists.forEach(System.out::println);
//        System.out.println(myUser);
    }

    @Test
    void contextLoads2() {
        // 参数是一个 Wrapper ，条件构造器，这里我们先不用 null
        // 查询全部用户
//        List<User> users = userMapper.selectList(null);
//        users.forEach(System.out::println);
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("email","371252847@qq.com");
        User user1 = userMapper.selectOne(wrapper);
        if (user1!=null){
            log.error("用户已存在");
        }

        QueryWrapper<User> wrapper2 = new QueryWrapper<>();
        wrapper
                .isNotNull("name")
                .isNotNull("email");
        userMapper.selectList(wrapper2).forEach(System.out::println);

        List<User> users = userMapper.selectList(null);

    }

    @Test
    void contextLoads() throws IOException {
        System.out.println("aaa");
        String xmlPath="E:\\Graduation\\DataShare\\ModelSchemaDescription\\gbhm_msd.xml";
//        String xml ="<auibinsurancecallback><policyinfo><transtype>TKTS</transtype><eticketno>xxx</eticketno><flightnumber>xxx</flightnumber><flightdate>2019-10-16</flightdate><operatetime>2019-10-16 17:20:00</operatetime><insureno>1910161720056066735</insureno><agreeno>102160199</agreeno><policyno></policyno><policyurl><!--[CDATA[]]--></policyurl></policyinfo><returninfo><serialnumber>2019103015284949545354</serialnumber><retruncode>0</retruncode><errormessage><!--[CDATA[xxx]]--></errormessage></returninfo></auibinsurancecallback>";
        String xmlPath2="E:\\Graduation\\DataShare\\DataSchemaDescription\\dsd_长三角1-10万土地利用数据(2010年).xml";

        FileReader fileReader = new FileReader(xmlPath2);
        JSONObject parse = ParseXMLToJSON.parse(fileReader.readString());
        System.out.println(parse);
    }

    @Test
    public void test() throws IOException {
        String a="6457038f840e761b88e8c1ab_nanjing_shp.zip";
        System.out.println(a.substring(25));
    }

    @Test
    void test2(){
        System.out.println("123");
    }

    @Test
    void test3(){
        JsonResult modelList = dataMetaService.getAllGeoMetaData();
        System.out.println(modelList.getData().toString());
    }

    @Test
    public void testDataSource() throws SQLException {
        //看一下默认数据源
        System.out.println(dataSource.getClass());
        //获得连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
        System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());
    }
}
