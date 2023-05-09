package geodatahubback.utils;

import geodatahubback.entity.JsonResult;
import geodatahubback.enums.ResultEnum;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/6
 **/
public class ResponseResult {

    public static JsonResult<Object> success(){
        return success(ResultEnum.SUCCESS.getMsg(),null);
    }

    public static JsonResult<Object> success(Object obj){
        return success(ResultEnum.SUCCESS.getMsg(),obj);
    }

    public static JsonResult<Object> success(String msg,Object obj){
        JsonResult<Object> jsonResult=new JsonResult<>();
        jsonResult.setCode(ResultEnum.SUCCESS.getCode());
        jsonResult.setMsg(msg);
        jsonResult.setData(obj);
        return jsonResult;
    }

    public static JsonResult error(){
        return error(ResultEnum.Error.getCode(),ResultEnum.Error.getMsg(),null);
    }

    public static JsonResult error(String msg){
        return error(ResultEnum.Error.getCode(),msg,null);
    }

    public static JsonResult error(int code, String msg,Object obj){
        JsonResult<Object> jsonResult=new JsonResult<>();
        jsonResult.setCode(code);
        jsonResult.setMsg(msg);
        jsonResult.setData(obj);
        return jsonResult;
    }

}
