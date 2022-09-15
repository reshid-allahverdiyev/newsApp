package newsApp.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GetAuthorResponse  implements Serializable {
    private String id;
    private String name;
    private String surname;
    private List<GetNewsResponse> news;
}
