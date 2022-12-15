package geodatahubback.module.metadata.dao;

import geodatahubback.module.metadata.domain.entity.GeoMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/12/6
 **/
public interface GeoMetaDataDao extends MongoRepository<GeoMetadata,String> {

}
