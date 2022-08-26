package newsApp.repository.mysql;

import newsApp.entity.NewsEntity;
import newsApp.repository.view.NewsView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  NewsRepository extends JpaRepository<NewsEntity, Long>{
    NewsView  findById(Long id, Class<NewsView> type);
    List<NewsEntity> findAll(Specification<NewsEntity> newsSpecification);
    Page<NewsEntity> findAll(Specification<NewsEntity> newsSpecification, Pageable of);
}
