package newsApp.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetTypeResponse  implements Serializable {
    private Long id;
    private String name;
}
