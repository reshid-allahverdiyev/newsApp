package newsApp.api;

import newsApp.entity.NewsEntity;
import newsApp.request.CreateNewsRequest;
import newsApp.request.SearchNewsRequest;
import newsApp.response.GeneralResponse;
import newsApp.response.GetNewsPagebleResponse;
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
    public GeneralResponse getAllNews() {
        return  newsService.getAllNews();
    }






    @GetMapping("/allcriteria")
    public GeneralResponse getAllNewsWithCriteria(
            @RequestBody SearchNewsRequest request
    ) {
        return newsService.getAllNewsWithCriteria(request);
    }

    @GetMapping("/allcriteriapage")
    public GeneralResponse getAllNewsWithCriteriaAndPage(
            @RequestBody SearchNewsRequest request,
            @RequestParam(value = "page",required = false,defaultValue = "0") int page,
            @RequestParam(value = "size",required = false,defaultValue = "3") int size

    ) {
        return newsService.getAllNewsWithCriteriaAndPage(request,page,size);
    }



    @GetMapping("/{id}")
    public GeneralResponse getNewsById(
            @PathVariable("id") Long id
    ) {
        return newsService.getNewsById(id);
    }

    @GetMapping("/review/{id}")
    public GeneralResponse getNewsByIdReview(
            @PathVariable("id") Long id
    ) {
        return newsService.getNewsByIdReview(id);
    }



    @GetMapping("/one/{id}")
    public GeneralResponse getOneNewsById(
            @PathVariable("id") Long id
    ) {
        return newsService.getOneNewsById(id);
    }

    @PostMapping("/create")
    public GeneralResponse createCourse(
            @RequestBody CreateNewsRequest request
    ) {
        return newsService.createNews(request);
    }



    @PutMapping("/update/{id}")
    public GeneralResponse updateNews(
            @RequestBody CreateNewsRequest request,
            @PathVariable Long id
    ) {
        return newsService.updateNews(request, id);
    }

}
