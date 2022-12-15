package geodatahubback.config.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/11/30
 **/

@Configuration
public class RabbitConfig {
    //默认情况下，系统自动提供了该工具RabbitTemplate，
    // 但是这里我们需要对该工具重新进行定制，
    // 主要是添加消息发送的返回队列，最后我们还需要给返回队列设置一个监听器。

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        //1消息推送到server，但是在server里找不到交换机
        //2消息推送到server，找到交换机了，但是没找到队列
        //3消息推送到sever，交换机和队列啥都没找到
        //4消息推送成功
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("ConfirmCallback: "+"相关数据："+correlationData+"  确认情况："+ack+"  原因："+cause);
        });

        //2消息推送到server，找到交换机了，但是没找到队列
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            System.out.println("ReturnCallback: "+"消息："+returnedMessage.getMessage()
                    +"  回应码："+returnedMessage.getReplyCode()
                    +"  回应信息："+returnedMessage.getReplyText()
                    +"  交换机："+returnedMessage.getExchange()
                    +"  路由键："+returnedMessage.getRoutingKey());
        });

        return rabbitTemplate;
    }

}
