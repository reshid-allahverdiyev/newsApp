package newsApp.Specification;

import newsApp.entity.NewsEntity;
import newsApp.entity.NewsEntity_;
import newsApp.request.SearchNewsRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class NewsSpecification implements Specification<NewsEntity> {
    private final SearchNewsRequest request;

    public NewsSpecification(SearchNewsRequest request) {
        this.request = request;
    }

    @Override
    public Predicate toPredicate(Root<NewsEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(request)) {
            if (Objects.nonNull(request.getId())){
                predicates.add(
                        cb.like(root.get(NewsEntity_.title),"%"+request.getTitle()+"%"));
            }
        }

        if (Objects.nonNull(request)) {
            if (Objects.nonNull(request.getId())){
                predicates.add(
                        cb.equal(root.join("type").get("id"),request.getId()));
            }
        }

        return cb.and(predicates.toArray(new Predicate[]{}));
    }
}
