package newsApp.response;

import lombok.Data;

import java.util.List;

@Data
public class GetAuthorResponse {
    private String id;
    private String name;
    private String surname;
    private List<GetNewsResponse> news;
}
