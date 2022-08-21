package newsApp.api;

import newsApp.entity.NewsEntity;
import newsApp.request.CreateNewsRequest;
import newsApp.response.GetNewsResponse;
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

    @GetMapping("/{id}")
    public GetNewsResponse getNewsById(
            @PathVariable("id") Long id
    ) {
        return newsService.getNewsById(id);
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
