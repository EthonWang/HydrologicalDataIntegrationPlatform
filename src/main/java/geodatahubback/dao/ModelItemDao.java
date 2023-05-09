package geodatahubback.dao;

import geodatahubback.entity.model.ModelItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/6
 **/
@Component
public interface ModelItemDao extends MongoRepository<ModelItem,String> {

}
