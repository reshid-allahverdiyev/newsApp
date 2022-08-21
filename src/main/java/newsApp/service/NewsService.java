package newsApp.service;

import newsApp.entity.NewsEntity;
import newsApp.mapper.ObjectMapper;
import newsApp.repository.NewsRepository;
import newsApp.request.CreateNewsRequest;
import newsApp.response.GetNewsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public List<GetNewsResponse> getAllNews(){
        return newsRepository.findAll().stream()
                .map(objectMapper::entityToDto)
                .collect(Collectors.toList());
    }
    public GetNewsResponse getNewsById(Long id) {
        return objectMapper.entityToDto(newsRepository.getById(id));
    }


    public GetNewsResponse createNews(CreateNewsRequest request) {
        NewsEntity entity = objectMapper.dtoToEntity(request);
        return objectMapper.entityToDto(newsRepository.save(entity));
    }


    public GetNewsResponse updateNews(CreateNewsRequest request, Long id) {
        NewsEntity entity =
                newsRepository.findById(id).orElseThrow(NullPointerException::new);

        entity.setTitle(request.getTitle());
        entity.setBody(request.getBody());
        //entity.setTypeId(request.getTypeId());
        return objectMapper.entityToDto(newsRepository.save(entity));
    }

}
