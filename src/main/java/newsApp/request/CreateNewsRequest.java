package newsApp.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateNewsRequest {
    private String title;
    private String body;
    private Long typeId;
    private List<Long> authorIds;
}
