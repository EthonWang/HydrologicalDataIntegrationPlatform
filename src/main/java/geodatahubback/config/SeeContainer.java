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

    public int maxProfit2(int[] prices) {
        int n=prices.length;

        int[] buy=new int[n];
        int[] sell=new int[n];
        int[] cold=new int[n];

        buy[0]=-prices[0];
        sell[0]=0;
        cold[0]=0;

        for (int i = 1; i < n; i++) {
            buy[i]=Math.max(buy[i-1], cold[i-1]-prices[i]);
            sell[i]=Math.max(sell[i-1], buy[i-1]+prices[i]);
            cold[i]=Math.max(cold[i-1], sell[i-1]);
        }

        return  sell[n-1];

    }
}
