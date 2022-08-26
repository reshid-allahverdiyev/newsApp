package newsApp.repository.redis;

import newsApp.entity.TokenEntityRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepositoryRedis extends CrudRepository<TokenEntityRedis,String> {

}
