package newsApp.repository.mysql;

import newsApp.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository  extends JpaRepository<AuthorEntity,Long> {
    List<AuthorEntity> findAllByIdIn(List<Long> ids);
}
