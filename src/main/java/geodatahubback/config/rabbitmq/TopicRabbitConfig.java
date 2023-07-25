package geodatahubback.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/11/29
 **/

@Configuration
public class TopicRabbitConfig {

    @Bean
    public Queue createTestTopicQueue1() {
        return new Queue("TestTopicQueue1");
    }

    @Bean
    public Queue createTestTopicQueue2() {
        return new Queue("TestTopicQueue2");
    }

    @Bean
    TopicExchange createTestTopicExchange() {
        return new TopicExchange("TestTopicExchange");
    }

    @Bean
    Binding bindingTopic1() {
        return BindingBuilder.bind(createTestTopicQueue1()).to(createTestTopicExchange()).with("TestTopicQueue1");
    }

    @Bean
    Binding bindingTopic2() {
        return BindingBuilder.bind(createTestTopicQueue2()).to(createTestTopicExchange()).with("#");
    }
}
