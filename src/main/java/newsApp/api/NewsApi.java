package newsApp.api;

import newsApp.entity.NewsEntity;
import newsApp.request.CreateNewsRequest;
import newsApp.request.SearchNewsRequest;
import newsApp.response.GetNewsResponse;
import newsApp.response.GetOneNewsResponse;
import newsApp.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsApi {
    @Autowired
    private NewsService  newsService;


    @GetMapping("/all")
    public List<GetNewsResponse> getAllNews() {
        return newsService.getAllNews();
    }

    @GetMapping("/allcriteria")
    public List<GetNewsResponse> getAllNewsWithCriteria(
            @RequestBody SearchNewsRequest request
    ) {
        return newsService.getAllNewsWithCriteria(request);
    }



    @GetMapping("/{id}")
    public GetNewsResponse getNewsById(
            @PathVariable("id") Long id
    ) {
        return newsService.getNewsById(id);
    }

    @GetMapping("/one/{id}")
    public GetOneNewsResponse getOneNewsById(
            @PathVariable("id") Long id
    ) {
        return newsService.getOneNewsById(id);
    }

    @PostMapping("/create")
    public GetNewsResponse createCourse(
            @RequestBody CreateNewsRequest request
    ) {
        return newsService.createNews(request);
    }



    @PutMapping("/update/{id}")
    public GetNewsResponse updateNews(
            @RequestBody CreateNewsRequest request,
            @PathVariable Long id
    ) {
        return newsService.updateNews(request, id);
    }

}
