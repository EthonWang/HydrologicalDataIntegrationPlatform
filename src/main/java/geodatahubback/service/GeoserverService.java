package geodatahubback.service;

import geodatahubback.entity.JsonResult;
import geodatahubback.utils.GeoServerUtils;
import geodatahubback.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/7/17
 **/
@Slf4j
@Service
public class GeoserverService {

    @Value("${mygeoserver.url}")
    String url;

    @Value("${mygeoserver.user}")
    String user;

    @Value("${mygeoserver.password}")
    String password;

    @Value("${mygeoserver.workName}")
    String workName;

    @Value("${mygeoserver.storePath}")
    String storePath;

    @Value("${upFileStoreDir}")
    String upFileStoreDir;

    public JsonResult upTif2Geoserver(String realName, String tifStoreName){

        String storeName=tifStoreName.split("\\.")[0];
        String filePath=upFileStoreDir+System.getProperty("file.separator")+tifStoreName;

        try {
            GeoServerUtils.PublishTiff(url,user,password,workName,storeName,filePath);
            return ResponseResult.success();
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseResult.error();
        }
    }

    public JsonResult upShp2Geoserver(String realName, String zipStoreName){

        String fileNameNoExt=realName.split("\\.")[0];

        String storeName=fileNameNoExt; //nanjing
        String layerName=fileNameNoExt; //nanjing layerName必须与zip中的shp名字一样,zip本身名字无关
        String styleName=null;
        String filePath=upFileStoreDir+System.getProperty("file.separator")+zipStoreName;  //代码运行机器的物理路径

        try {
            GeoServerUtils.PublishZipShape(url,user,password,workName,storeName,layerName,styleName,filePath);
            return ResponseResult.success();
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseResult.error();
        }
    }



}
