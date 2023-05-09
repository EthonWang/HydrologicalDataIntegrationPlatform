package geodatahubback.entity;


import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @TableName user_data
 */
@TableName(value ="user_data")
@Data
public class UserData implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Long userId;

    private String realName;

    private String storeName;

    private String fileWebAddress;

    private String fileSize;

    private Integer deleted;

    @Version
    private Integer version;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}