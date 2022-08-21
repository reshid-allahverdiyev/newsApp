package newsApp.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class GetNewsResponse {
    private Long id;
    private String title;
    private String body;
    private String typeId;
}
