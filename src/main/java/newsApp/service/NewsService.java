package newsApp.service;

import newsApp.aspect.EventNotAcceptableException;
import newsApp.config.statemachine.NewsEvent;
import newsApp.config.statemachine.NewsState;
import newsApp.config.statemachine.NewsStateChangeInterceptor;
import newsApp.entity.NewsEntity;
import newsApp.mapper.ObjectMapper;
import newsApp.repository.mysql.AuthorRepository;
import newsApp.repository.mysql.NewsRepository;
import newsApp.repository.mysql.TypeRepository;
import newsApp.repository.view.NewsView;
import newsApp.request.CreateNewsRequest;
import newsApp.request.SearchNewsRequest;
import newsApp.request.searchQuery.SearchQueries;
import newsApp.response.GeneralResponse;
import newsApp.response.GetNewsPagebleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

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

    @Autowired
    private StateMachineFactory<NewsState, NewsEvent> newsStateMachineFactory;

    @Autowired
    private NewsStateChangeInterceptor newsStateChangeInterceptor;

    public GeneralResponse getAllNews() {
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setData(
                newsRepository.findAll().stream()
                .map(objectMapper::entityToDto)
                .collect(Collectors.toList()));
        return generalResponse;
    }

    public GeneralResponse getAllNewsWithCriteria(SearchNewsRequest request) {
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setData(
                newsRepository.findAll(SearchQueries.createStudentSpecification(request)).stream()
                        .map(objectMapper::entityToDto)
                        .collect(Collectors.toList()));
        return generalResponse;
    }

    public GeneralResponse getAllNewsWithCriteriaAndPage(SearchNewsRequest request, int page, int size) {
        GetNewsPagebleResponse response = new GetNewsPagebleResponse();
        Page<NewsEntity> newsEntityPage = newsRepository
                .findAll(
                        SearchQueries.createStudentSpecification(request),
                        PageRequest.of(page, size));
        response.setNewsList(newsEntityPage.getContent().stream()
                .map(objectMapper::entityToDto)
                .collect(Collectors.toList()));
        response.setTotalPages(newsEntityPage.getTotalPages());
        response.setTotalElements(newsEntityPage.getTotalElements());

        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setData(response);
        return generalResponse;
    }


    public GeneralResponse getNewsById(Long id) {
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setData(objectMapper.entityToDto(newsRepository.getById(id)));
        return generalResponse;
    }

    public GeneralResponse getNewsByIdReview(Long id) {


        StateMachine<NewsState, NewsEvent> sm = build(id);
        sendEvent(id,sm,NewsEvent.REJECT);

        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setData(objectMapper.entityToDto(newsRepository.findById(id).get()));
        return generalResponse;
    }

    private void sendEvent(Long blogId, StateMachine<NewsState, NewsEvent> sm, NewsEvent event) {
        Message<NewsEvent> msg = MessageBuilder.withPayload(event)
                .setHeader("blog_id", blogId)
                .setHeader("a","abc")
                .build();
        if (!sm.sendEvent(msg)) {
            throw new EventNotAcceptableException();
        }
    }

    private StateMachine<NewsState, NewsEvent> build(Long blogId) {
        NewsEntity news = newsRepository.findById(blogId).get();

        StateMachine<NewsState, NewsEvent> sm = newsStateMachineFactory.getStateMachine(Long.toString(blogId));

        sm.stop();

        sm.getStateMachineAccessor()
                .doWithAllRegions(sma -> {
                    sma.addStateMachineInterceptor(newsStateChangeInterceptor);
                    sma.resetStateMachine(new DefaultStateMachineContext<>(news.getState(), null, null, null));
                });

        sm.start();

        return sm;
    }






    public GeneralResponse getOneNewsById(Long id) {
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setData(objectMapper.entityToDto(newsRepository.findById(id, NewsView.class)));
        return generalResponse;
    }




    public GeneralResponse createNews(CreateNewsRequest request) {
        NewsEntity entity = new NewsEntity();
        entity.setTitle(request.getTitle());
        entity.setBody(request.getBody());
        entity.setAuthorEntities(authorRepository.findAllByIdIn(request.getAuthorIds()));
        entity.setType(typeRepository.getById(request.getTypeId()));

        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setData(objectMapper.entityToDto(newsRepository.save(entity)));
        return generalResponse;

    }


    public GeneralResponse updateNews(CreateNewsRequest request, Long id) {
        NewsEntity entity =
                newsRepository.findById(id).orElseThrow(NullPointerException::new);

        entity.setTitle(request.getTitle());
        entity.setBody(request.getBody());
        entity.setType(typeRepository.getById(request.getTypeId()));
        entity.setAuthorEntities(authorRepository.findAllByIdIn(request.getAuthorIds()));

        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setData(objectMapper.entityToDto(newsRepository.save(entity)));
        return generalResponse;
    }

}
