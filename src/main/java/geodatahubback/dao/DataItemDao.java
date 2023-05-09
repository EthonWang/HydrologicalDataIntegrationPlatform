package geodatahubback.dao;

import geodatahubback.entity.data.DataItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/4/7
 **/
@Repository
public interface DataItemDao extends MongoRepository<DataItem,String> {
    Page<DataItem> findAllByDataClassAndNameLikeIgnoreCaseAndKeywordsLikeIgnoreCase(String dataClass, String searchText, String searchText1, Pageable pageable);

    Page<DataItem> findAllByNameLikeIgnoreCaseAndKeywordsLikeIgnoreCase(String searchText, String searchText1, Pageable pageable);

    List<DataItem> findAllByProcessable(String aTrue);
}
