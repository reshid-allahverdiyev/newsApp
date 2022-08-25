package newsApp.request;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class SearchNewsRequest {
    private Long id;
    private  String title;
}
