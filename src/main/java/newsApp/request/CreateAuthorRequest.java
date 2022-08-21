package newsApp.request;

import lombok.Data;


@Data
public class CreateAuthorRequest {
    private String name;
    private String surname;
}
