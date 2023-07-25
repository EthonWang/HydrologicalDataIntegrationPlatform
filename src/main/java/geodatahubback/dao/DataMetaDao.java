package geodatahubback.dao;

import geodatahubback.entity.data.DataMeta;
import geodatahubback.entity.metadata.GeoMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/6
 **/
@Component
public interface DataMetaDao extends MongoRepository<DataMeta,String> {

}
