package geodatahubback.controller;

import geodatahubback.config.concurrent.RateLimit;
import geodatahubback.entity.JsonResult;
import geodatahubback.entity.script.ScriptExecDTO;
import geodatahubback.entity.script.ScriptItem;
import geodatahubback.service.ScriptService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/6
 **/

@CrossOrigin
@RestController
public class ScriptController {
    @Autowired
    ScriptService scriptService;

    @ApiOperation(value = "添加脚本")
    @PostMapping(value = "/addScriptItem")
    public JsonResult addScriptItem(@RequestBody ScriptItem scriptItem){
        return scriptService.addScriptItem(scriptItem);
    }

    @ApiOperation(value = "上传脚本文件")
    @PostMapping(value = "/uploadScriptFile")
    public JsonResult uploadScriptFile(@RequestPart("upScriptFile") MultipartFile upScriptFile){
        return scriptService.uploadScriptFile(upScriptFile);
    }

    @ApiOperation(value = "获取脚本列表")
    @GetMapping("/getScriptList")
    public JsonResult getScriptList(){
        return scriptService.getScriptList();
    }

    @ApiOperation(value = "删除脚本")
    @GetMapping("/deleteScript/{id}")
    public JsonResult deleteScript(@PathVariable("id") String id){
        return scriptService.deleteScript(id);
    }

    @ApiOperation(value = "执行脚本")
    @PostMapping(value = "/execScript/{uuid}")
    public JsonResult execScript(@RequestBody ScriptExecDTO scriptExecDTO,
                                 @PathVariable("uuid")  String uuid){
        return scriptService.execScript(scriptExecDTO,uuid);
    }


    @RateLimit
    @ApiOperation(value = "创建通信")
    @GetMapping(value = "/createSeeConnect/{uuid}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter createSeeConnect(@PathVariable("uuid") String uuid) {
        return scriptService.createSeeConnect(uuid);
    }

    @ApiOperation(value = "关闭通信")
    @GetMapping(value = "/closeSeeConnect/{uuid}")
    public JsonResult closeSeeConnect(@PathVariable("uuid") String uuid) {
        return scriptService.closeSeeConnect(uuid);
    }



}
