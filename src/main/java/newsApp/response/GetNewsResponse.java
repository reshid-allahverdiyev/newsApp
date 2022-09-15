package newsApp.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
public class GetNewsResponse   implements  Serializable {
    private Long id;
    private String title;
    private String body;
    private GetTypeResponse type;
    private List<GetAuthorResponse>  authors;
}
