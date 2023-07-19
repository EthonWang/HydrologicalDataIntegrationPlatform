package geodatahubback.config.exception;

import geodatahubback.entity.JsonResult;
import geodatahubback.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/6/2
 **/
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    /**
     * 系统繁忙，请稍候再试"
     */
    @ExceptionHandler(Exception.class)
    public JsonResult handleException(Exception e) {
        log.error("Exception,exception:{}", e, e);
        return ResponseResult.error("error");
    }

    /**
     * 自定义全局异常处理
     */
//    @ExceptionHandler(value = BusinessException.class)
//    DataResult businessExceptionHandler(BusinessException e) {
//        log.error("Exception,exception:{}", e, e);
//        return new DataResult(e.getMessageCode(), e.getDetailMessage());
//    }

    /**
     * 没有权限 返回403视图
     */
//    @ExceptionHandler(value = AuthorizationException.class)
//    public DataResult errorPermission(AuthorizationException e) {
//        log.error("Exception,exception:{}", e, e);
//        return new DataResult(BaseResponseCode.UNAUTHORIZED_ERROR);
//
//    }

    /**
     * 处理validation 框架异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    JsonResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("methodArgumentNotValidExceptionHandler bindingResult.allErrors():{},exception:{}", e.getBindingResult().getAllErrors(), e);
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        return ResponseResult.error(errors.get(0).getDefaultMessage());
    }

    /**
     * 校验List<entity>类型， 需要controller添加@Validated注解
     * 处理Validated List<entity> 异常
     */
//    @ExceptionHandler
//    public DataResult handle(ConstraintViolationException exception) {
//        log.error("methodArgumentNotValidExceptionHandler bindingResult.allErrors():{},exception:{}", exception, exception);
//        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
//        StringBuilder builder = new StringBuilder();
//        for (ConstraintViolation violation : violations) {
//            builder.append(violation.getMessage());
//            break;
//        }
//        return DataResult.getResult(BaseResponseCode.METHODARGUMENTNOTVALIDEXCEPTION.getCode(), builder.toString());
//    }

}
