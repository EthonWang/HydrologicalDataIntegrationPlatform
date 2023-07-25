package geodatahubback.service;

import geodatahubback.entity.PageDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/4/22
 **/
@Service
public class CommonService {
    public Pageable getPageable(PageDTO pageDTO){
        return PageRequest.of(pageDTO.getPage()-1, pageDTO.getPageSize(), Sort.by(pageDTO.getAsc()? Sort.Direction.ASC: Sort.Direction.DESC,pageDTO.getSortField()));
    }
}
