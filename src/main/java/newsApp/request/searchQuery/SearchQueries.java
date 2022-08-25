package newsApp.request.searchQuery;

import newsApp.Specification.NewsSpecification;
import newsApp.entity.NewsEntity;
import newsApp.request.SearchNewsRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public interface SearchQueries {
    static Specification<NewsEntity> createStudentSpecification(SearchNewsRequest request) {
        return new NewsSpecification(request);
    }
}