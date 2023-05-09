package geodatahubback.entity.form;

import lombok.Data;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/5/5
 **/
@Data
public class UserDataAdd {
    long userId;
    String realName;
    String storeName;
    String filePath;
    String fileWebAddress;
    String fileSize;
}
