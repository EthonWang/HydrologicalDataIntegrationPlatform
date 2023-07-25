package geodatahubback.entity;

import lombok.Data;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/4/21
 **/
@Data
public class ChatMessage {
    private String userId;
    private String userName;
    private String message;
}
