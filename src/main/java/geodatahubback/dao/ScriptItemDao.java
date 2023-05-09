package geodatahubback.dao;

import geodatahubback.entity.script.ScriptItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/6
 **/
@Component
public interface ScriptItemDao extends MongoRepository<ScriptItem,String> {

}
