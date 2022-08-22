package newsApp.service;

import newsApp.entity.AuthorEntity;
import newsApp.entity.NewsEntity;
import newsApp.entity.TypeEntity;
import newsApp.mapper.ObjectMapper;
import newsApp.repository.AuthorRepository;
import newsApp.repository.NewsRepository;
import newsApp.repository.TypeRepository;
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
    private TypeRepository typeRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ObjectMapper objectMapper;

    public List<GetNewsResponse> getAllNews(){
        return newsRepository.findAll().stream()
                .map(objectMapper::entityToDto)
                .collect(Collectors.toList());
    }
    public GetNewsResponse getNewsById(Long id) {
        GetNewsResponse d=objectMapper.entityToDto(newsRepository.getById(id));
        return  d;
    }


    public GetNewsResponse createNews(CreateNewsRequest request) {
        NewsEntity entity = new NewsEntity();
        entity.setTitle(request.getTitle());
        entity.setBody(request.getBody());
        entity.setAuthorEntities(authorRepository.findAllByIdIn(request.getAuthorIds()));
        entity.setType(typeRepository.getById(request.getTypeId()));
        return objectMapper.entityToDto(newsRepository.save(entity));
    }


    public GetNewsResponse updateNews(CreateNewsRequest request, Long id) {
        NewsEntity entity =
                newsRepository.findById(id).orElseThrow(NullPointerException::new);

        entity.setTitle(request.getTitle());
        entity.setBody(request.getBody());
        entity.setType(typeRepository.getById(request.getTypeId()));
        return objectMapper.entityToDto(newsRepository.save(entity));
    }

}
