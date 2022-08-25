package newsApp.response;

import lombok.Data;

import java.util.List;

@Data
public class GetNewsPagebleResponse {
    private Integer totalPages;
    private Long totalElements;
    private List<GetNewsResponse>  newsList;
}
