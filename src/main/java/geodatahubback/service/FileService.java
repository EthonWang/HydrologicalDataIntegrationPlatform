package geodatahubback.service;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import geodatahubback.entity.JsonResult;
import geodatahubback.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/4/19
 **/
@Service
public class FileService {

    @Value("${upFileStoreDir}")
    String folder;


    public JsonResult uploadFile(MultipartFile upFile) {
        try {
            //可视化文件
            if (upFile.isEmpty()) {
                return ResponseResult.error("上传文件为空");
            }

            //将文件保存到指定位置
            String fileName = upFile.getOriginalFilename(); //eg: XXX.js
            String fileMainName= FileNameUtil.mainName(fileName); // XXX
            String fileExtName = FileNameUtil.extName(fileName); //js
            String fileNewName= IdUtil.objectId()+"_"+fileName; //xxx_XXX.js
            File file = new File(folder, fileNewName);//eg: E:\\TEMP\\1231231.js
            upFile.transferTo(file);


            JSONObject o=new JSONObject();
            o.put("realName",fileName);
            o.put("storeName",fileNewName);
            o.put("fileWebAddress","/store/UpFile/"+fileNewName);
            o.put("fileSize",upFile.getSize()/1000.0); //KB

            return ResponseResult.success(o);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.error("文件上传失败");
        }

    }
}
