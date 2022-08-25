package newsApp.api;

import newsApp.request.CreateAuthorRequest;
import newsApp.response.GeneralResponse;
import newsApp.response.GetAuthorResponse;
import newsApp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/author")
public class AuthorApi {
    @Autowired
    private AuthorService authorService;


    @GetMapping("/all")
    public GeneralResponse getAllAuthor() {
        return authorService.getAllAuthor();
    }

    @GetMapping("/{id}")
    public GeneralResponse getAuthorById(
            @PathVariable("id") Long id
    ) {
        return authorService.getAuthorById(id);
    }

    @PostMapping("/create")
    public GeneralResponse createAuthor(
            @RequestBody CreateAuthorRequest request
    ) {
        return authorService.createAuthor(request);
    }



    @PutMapping("/update/{id}")
    public GeneralResponse updateAuthor(
            @RequestBody CreateAuthorRequest request,
            @PathVariable Long id
    ) {
        return authorService.updateAuthor(request, id);
    }

}
