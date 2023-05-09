package geodatahubback.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/5/8
 **/
@Component
public class SeeContainer {
    private Map<String, SseEmitter> seeMap = new ConcurrentHashMap<>();

    public void addSeeEmitter(String uuid,SseEmitter sseEmitter){
        seeMap.put(uuid,sseEmitter);
    }
    public SseEmitter getSeeEmitter(String uuid){
        return seeMap.get(uuid);
    }

    public void removeSeeEmitter(String uuid){
        seeMap.remove(uuid);
    }

    public boolean ifExist(String uuid){
        return seeMap.containsKey(uuid);
    }
    public int getCount(){
        return seeMap.size();
    }


}
