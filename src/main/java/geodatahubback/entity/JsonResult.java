package geodatahubback.entity;

import lombok.Data;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/6
 **/
@Data
public class JsonResult<T> {

    private Integer code=0;
    private String msg="success";
    private T data;

}
