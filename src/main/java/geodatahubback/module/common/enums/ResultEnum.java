package geodatahubback.module.common.enums;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/6
 **/
public enum ResultEnum {
    SUCCESS(0,"success"),
    Error(-1,"error"),
    UNAUTHORIZED(-2,"Unauthorized");

    private Integer code;
    private String msg;

    ResultEnum(int code,String msg){
       this.code=code;
       this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
