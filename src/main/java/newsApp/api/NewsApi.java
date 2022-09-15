package newsApp.api;

import newsApp.repository.mysql.NewsRepository;
import newsApp.request.CreateNewsRequest;
import newsApp.request.SearchNewsRequest;
import newsApp.response.GeneralResponse;
import newsApp.response.GetNewsResponse;
import newsApp.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
@EnableCaching
public class NewsApi {
    @Autowired
    private NewsService  newsService;


    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/all")
    public GeneralResponse  getAllNews() {
        return  newsService.getAllNews();
    }

    @GetMapping("/allc")
    @Cacheable("test")
    public GeneralResponse  getAllNewsCache()  throws  Exception{
        Thread.sleep(4000);
        return  newsService.getAllNews();
    }

    @GetMapping("/allcl")
    @CacheEvict(value = "test", allEntries = true)
    public void   getAllNewsCleanCache() {
    }

    @GetMapping("/allu")
    @CachePut(value = "test")
    public GeneralResponse  getAllNewsUpdateCache() {
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
