package newsApp.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@TypeAlias("DBNEWS")
@RedisHash("DBNEWS_HASH")
public class TokenEntityRedis implements Serializable {

    @Id
    @Column(name = "token")
    private String token;

    @Column(name = "username")
    private String username;

}
