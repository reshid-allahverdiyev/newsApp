package newsApp.api;

import newsApp.request.CreateTypeRequest;
import newsApp.response.GetTypeResponse;
import newsApp.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeApi {
    @Autowired
    private TypeService typeService;


    @GetMapping("/all")
    public List<GetTypeResponse> getAllType() {
        return typeService.getAllType();
    }

    @GetMapping("/{id}")
    public GetTypeResponse getTypeById(
            @PathVariable("id") Long id
    ) {
        return typeService.getTypeById(id);
    }

    @PostMapping("/create")
    public GetTypeResponse createType(
            @RequestBody CreateTypeRequest request
    ) {
        return typeService.createType(request);
    }



    @PutMapping("/update/{id}")
    public GetTypeResponse updateType(
            @RequestBody CreateTypeRequest request,
            @PathVariable Long id
    ) {
        return typeService.updateType(request, id);
    }
}
