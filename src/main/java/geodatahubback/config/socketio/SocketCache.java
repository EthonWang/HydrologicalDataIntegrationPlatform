package geodatahubback.config.socketio;

import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/7/11
 **/
@Component
public class SocketCache {

    private static Map<String, SocketIOClient> concurrentHashMap = new ConcurrentHashMap<>();
    public static AtomicInteger onlineCount = new AtomicInteger(0);


    public void saveClient(String userId, SocketIOClient socketIOClient) {
        onlineCount.getAndIncrement();
        concurrentHashMap.put(userId, socketIOClient);
    }
    public void deleteClient(String userId) {
        onlineCount.getAndDecrement();
        concurrentHashMap.remove(userId);
    }

    public SocketIOClient getUserClient(String userId) {
        return concurrentHashMap.get(userId);
    }

    public int getOnLineCount(){
        return onlineCount.get();
    }



}
