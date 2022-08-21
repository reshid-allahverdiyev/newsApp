package newsApp.request;

import lombok.Data;

@Data
public class CreateNewsRequest {
    private String title;
    private String body;
    private String typeId;
}
