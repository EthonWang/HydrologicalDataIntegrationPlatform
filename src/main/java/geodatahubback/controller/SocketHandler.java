package geodatahubback.controller;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import geodatahubback.config.socketio.SocketCache;
import geodatahubback.entity.ChatMessage;
import geodatahubback.utils.NettySocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/7/7
 **/
@Slf4j
@Component
public class SocketHandler {
    @Autowired
    private SocketIOServer socketIoServer;

    @Autowired
    private SocketCache socketCache;


//    public static Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();
//    public static AtomicInteger onlineCount = new AtomicInteger(0);

    private final static String QUERY_USER_ID = "userId";

    //Spring IoC容器创建之后，在加载SocketIOService Bean之后启动
    //服务端启动
    @PostConstruct
    private void autoStartup() throws Exception {
        try {
            socketIoServer.start();
            log.info("socketHandler start-------------------------------");
        }catch (Exception ex){
            ex.printStackTrace();
            log.error("SocketIOServer启动失败");
        }
    }

    //Spring IoC容器在销毁SocketIOService Bean之前关闭,避免重启项目服务端口占用问题
    //服务端关闭
    @PreDestroy
    private void autoStop() throws Exception {

        try {
            socketIoServer.stop();
            log.info("--socketHandler stop-------------------------------");
        }catch (Exception ex){
            ex.printStackTrace();
            log.error("--SocketIOServer关闭失败");
        }
    }

    //添加connect事件，当客户端发起连接时调用
    @OnConnect
    public void onConnect(SocketIOClient client) {
        //
        String clientId = client.getHandshakeData().getSingleUrlParam(QUERY_USER_ID);
        if(clientId!=null) {
            socketCache.saveClient(clientId,client);
            log.info("connect success: [clientId="+clientId+",onlineCount="+socketCache.getOnLineCount()+"]");
        }else{
            log.error("connect error" );
        }

    }

    //添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        String clientId = client.getHandshakeData().getSingleUrlParam(QUERY_USER_ID);
        if (clientId != null) {
            socketCache.deleteClient(clientId);
            client.disconnect();
            log.info("disconnect success: [clientId="+clientId+",onlineCount="+socketCache.getOnLineCount()+"]");
        }else{
            log.error("disconnect error" );
        }
    }

    // 消息接收入口
    @OnEvent(value = "server-socket-test")
    public void onEvent(SocketIOClient client, AckRequest ackRequest, Object data) {
        String userId = client.getHandshakeData().getSingleUrlParam(QUERY_USER_ID);
        log.info("onMessage: [clientId="+userId+",data="+data+"]");
        //request.sendAckData("message is revived");
        client.sendEvent("ack",1);
    }


    //系统介绍聊天
    //简单直接回复可以不需要cache
    @OnEvent(value = "server-socket-system-info")
    public void onEvent2(SocketIOClient client, AckRequest ackRequest, Object data) {

        String userId = client.getHandshakeData().getSingleUrlParam(QUERY_USER_ID);
        log.info("onMessage: [clientId="+userId+",data="+data+"]");
        SocketIOClient userClient = socketCache.getUserClient(userId);
        ackRequest.sendAckData("get request");
        userClient.sendEvent("recall","本系统面向流域水文模拟，提供数据、模型、数据处理方法");
    }

    //系统功能聊天
    @OnEvent(value = "server-socket-system-feature")
    public void onEvent3(SocketIOClient client, AckRequest ackRequest, Object data) {
        String userId = client.getHandshakeData().getSingleUrlParam(QUERY_USER_ID);
        log.info("onMessage: [clientId="+userId+",data="+data+"]");
        SocketIOClient userClient = socketCache.getUserClient(userId);
        ackRequest.sendAckData("get request");
        userClient.sendEvent("recall","1、上传数据；2、调用数据处理方法；3、调用模型");
    }
}
