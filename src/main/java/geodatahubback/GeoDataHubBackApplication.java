package geodatahubback;

import geodatahubback.config.rabbitmq.DirectRabbitConfig;
import geodatahubback.config.rabbitmq.TopicRabbitConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class GeoDataHubBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeoDataHubBackApplication.class, args);

//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TopicRabbitConfig.class);
//        Queue bean = (Queue) applicationContext.getBean("createTestTopicQueue2");
//        System.out.println(bean);
//
//        //根据类型获取bean的名字
//        String[] beanNames = applicationContext.getBeanNamesForType(Queue.class);
//        for(String name : beanNames) {
//            System.out.println(name);
//        }
    }

}
