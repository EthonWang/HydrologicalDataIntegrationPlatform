package geodatahubback.dao;

import geodatahubback.entity.ScriptTask;
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
public interface ScriptTaskDao extends MongoRepository<ScriptTask,String> {

    List<ScriptTask> findAllByUserId(String userId);
}
