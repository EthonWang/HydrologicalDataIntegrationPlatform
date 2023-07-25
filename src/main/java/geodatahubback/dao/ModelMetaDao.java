package geodatahubback.dao;

import geodatahubback.entity.metadata.GeoMetadata;
import geodatahubback.entity.model.ModelMeta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/6
 **/
@Component
public interface ModelMetaDao extends MongoRepository<ModelMeta,String> {

}
