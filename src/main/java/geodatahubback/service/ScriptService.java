package geodatahubback.service;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import geodatahubback.config.SeeContainer;
import geodatahubback.dao.ScriptItemDao;
import geodatahubback.entity.JsonResult;
import geodatahubback.entity.script.ArgumentComp;
import geodatahubback.entity.script.ArgumentType;
import geodatahubback.entity.script.ScriptExecDTO;
import geodatahubback.entity.script.ScriptItem;
import geodatahubback.utils.ExecCmdUtils;
import geodatahubback.utils.ResponseResult;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/3/28
 **/
@Slf4j
@Service
public class ScriptService {
    @Value("${scriptsStoreDir}")
    String scriptsStoreDir;

    @Value("${upFileStoreDir}")
    String upFileStoreDir;

    @Value("${scriptTasksDir}")
    String scriptTasksDir;

    @Value("${python2}")
    String python2Path;

    @Value("${python3}")
    String python3Path;


    @Autowired
    ScriptItemDao scriptItemDao;

    @Autowired
    SeeContainer seeContainer;

    @Autowired
    ScriptTaskService scriptTaskService;

    public JsonResult addScriptItem(ScriptItem scriptItem) {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();

        json.put("error", "json mapping error");

        scriptItemDao.save(scriptItem);
        return ResponseResult.success();
    }


    public JsonResult getScriptList() {
        return ResponseResult.success(scriptItemDao.findAll());
    }

    public JsonResult deleteScript(String id) {
        scriptItemDao.deleteById(id);
        return ResponseResult.success();
    }

    public JsonResult execScript(ScriptExecDTO scriptExecDTO, String seeId) {

        String id=scriptExecDTO.getScriptId();
        ScriptItem scriptItem = scriptItemDao.findById(id).get();
        String pyScriptAbsPath= scriptsStoreDir+"/"+scriptItem.getFileName();


        String taskId=IdUtil.objectId();
        scriptTaskService.createScriptTask(taskId,scriptItem.getName(),scriptExecDTO.getUserId());

        List<String> argumentsList=new ArrayList<>();


        new File(scriptTasksDir,taskId+"/result").mkdirs();
        String resultDir=new File(scriptTasksDir,taskId+"/result").getAbsolutePath();
        String dataDir=new File(scriptTasksDir,taskId).getAbsolutePath();
        String outZipPath=resultDir+".zip";

        argumentsList.add(dataDir);
        argumentsList.add(resultDir);
        argumentsList.add(outZipPath);

        for (ArgumentComp argumentComp : scriptExecDTO.getArguments()){
            String value=argumentComp.getValue();
            if (argumentComp.getType().equals(ArgumentType.FILE)){
                String realFileName=value.substring(25); //6457038f840e761b88e8c1ab_nanjing_shp.zip 2 nanjing_shp.zip
                File oldPathFile=new File(upFileStoreDir,value);
                File newPathFile=new File(dataDir,realFileName);
                argumentsList.add(newPathFile.getAbsolutePath());
                try {
                    FileInputStream inputStream= new  FileInputStream(oldPathFile);
                    FileOutputStream outputStream = new FileOutputStream(newPathFile);
                    IoUtil.copy(inputStream,outputStream);
                }catch (Exception e){
                    e.printStackTrace();
                    scriptTaskService.updateScriptStatus(taskId,"FinishButError");
                    return ResponseResult.error("输入文件异常");
                }
            }else{
                argumentsList.add(value);
            }
        }

        String pythonexe="";
        if(scriptItem.getPythonVersion().equals("python2")){
            pythonexe=python2Path;
        }else{
            pythonexe=python3Path;
        }

        SseEmitter seeEmitter = seeContainer.getSeeEmitter(seeId);
        try {
            seeEmitter.send("脚本开始执行(see)："+scriptItem.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        int re=ExecCmdUtils.execPython(pythonexe,pyScriptAbsPath,argumentsList);

        try {
            seeEmitter.send("脚本执行结束(see)："+scriptItem.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(re==0){
            log.info("脚本执行成功："+scriptItem.getFileName());
            String resultFileWeb="/store/ScriptTasks/"+taskId+"/result.zip";
            scriptTaskService.updateScriptStatusAndFile(taskId,"FinishAndSuccess",resultFileWeb);
            return ResponseResult.success();
        }else {
            log.error("脚本执行失败："+scriptItem.getFileName());
            scriptTaskService.updateScriptStatus(taskId,"FinishButError");
            return ResponseResult.error();
        }


    }


    public JsonResult uploadScriptFile(MultipartFile upScriptFile) {
        try {
            //可视化文件
            if (upScriptFile.isEmpty()) {
                return ResponseResult.error("上传文件为空");
            }

            //将文件保存到指定位置
            String fileName = upScriptFile.getOriginalFilename(); //eg: XXX.js

            File file = new File(scriptsStoreDir, fileName);//eg: E:\\TEMP\\1231231.js
            upScriptFile.transferTo(file);

//            JSONObject o=new JSONObject();
//            o.put("realName",fileName);
//            o.put("storeName",fileNewName);
//            o.put("fileWebAddress","/store/UpFile/"+fileNewName);
//            o.put("fileSize",upFile.getSize()/1000.0); //KB


            return ResponseResult.success("脚本文件上传成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.error("脚本文件上传失败");
        }
    }


    public SseEmitter createSeeConnect(String uuid) {
        try {
            if(seeContainer.ifExist(uuid)){
                return null;
            }
            SseEmitter sseEmitter = new SseEmitter(30*60*1000L);
            sseEmitter.onTimeout(() -> System.out.println("see连接超时"));
//            sseEmitter.onCompletion(() -> System.out.println("完成！！！"));
//            sseEmitter.send("okkkkk");
            sseEmitter.send(SseEmitter.event().name("open"));
//            sseEmitter.complete();

            seeContainer.addSeeEmitter(uuid,sseEmitter);
            log.info("count:"+seeContainer.getCount());
            log.info("创建see："+uuid);
            return sseEmitter;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public JsonResult closeSeeConnect(String uuid) {
        try{
            SseEmitter seeEmitter = seeContainer.getSeeEmitter(uuid);
            seeEmitter.complete();
            seeContainer.removeSeeEmitter(uuid);
            log.info("关闭see："+uuid);
            return ResponseResult.success("关闭see成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.error();
        }
    }

}
