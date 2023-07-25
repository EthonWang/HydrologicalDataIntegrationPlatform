package geodatahubback.config.socketio.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @Description
 * @Auther wyjq
 * @Date 2023/4/21
 **/
@Data
@Component
@ConfigurationProperties(prefix = "my-socket-io")
public class MySocketIOProperties {

    /**
     * socket主机
     */
    private String host;

    /**
     * socket端口
     */
    private Integer port;

    /**
     * 最大每帧处理数据的长度
     */
    private Integer maxFramePayloadLength;

    /**
     * Ping消息超时时间（毫秒），默认60秒，这个时间间隔内没有接收到心跳消息就会发送超时事件
     */
    private Integer pingTimeout;

    /**
     * Ping消息间隔（毫秒），默认25秒。客户端向服务器发送一条心跳消息间隔
     */
    private Integer pingInterval;

    /**
     * APK文件访问URL前缀
     */
    private String apkUrlPrefix;


}
