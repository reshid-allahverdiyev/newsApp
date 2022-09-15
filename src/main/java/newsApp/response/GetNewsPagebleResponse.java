package newsApp.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GetNewsPagebleResponse  implements Serializable {
    private Integer totalPages;
    private Long totalElements;
    private List<GetNewsResponse>  newsList;
}
