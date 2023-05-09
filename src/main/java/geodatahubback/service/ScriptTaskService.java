package geodatahubback.service;

import geodatahubback.dao.ScriptTaskDao;
import geodatahubback.entity.JsonResult;
import geodatahubback.entity.ScriptTask;
import geodatahubback.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/5/8
 **/

@Service
public class ScriptTaskService {
    @Autowired
    ScriptTaskDao scriptTaskDao;


    public JsonResult getScriptTaskList(String userId) {
        List<ScriptTask> scriptTaskList= scriptTaskDao.findAllByUserId(userId);
        return ResponseResult.success(scriptTaskList);
    }

    public JsonResult createScriptTask(ScriptTask scriptTask) {
        scriptTaskDao.save(scriptTask);
        return ResponseResult.success();
    }

    public void createScriptTask(String seeId, String name, String userId) {
        ScriptTask scriptTask=new ScriptTask();
        scriptTask.setId(seeId);
        scriptTask.setName(name);
        scriptTask.setUserId(userId);
        scriptTask.setStatus("running");
        scriptTaskDao.save(scriptTask);
    }

    public void updateScriptStatus(String seeId, String status) {
        ScriptTask scriptTask=scriptTaskDao.findById(seeId).get();
        scriptTask.setStatus(status);
        scriptTaskDao.save(scriptTask);
        scriptTaskDao.save(scriptTask);
    }

    public void updateScriptStatusAndFile(String seeId, String status,String fileWebAddress) {
        ScriptTask scriptTask=scriptTaskDao.findById(seeId).get();
        scriptTask.setStatus(status);
        scriptTask.setFileWebAddress(fileWebAddress);
        scriptTaskDao.save(scriptTask);
        scriptTaskDao.save(scriptTask);
    }

}
