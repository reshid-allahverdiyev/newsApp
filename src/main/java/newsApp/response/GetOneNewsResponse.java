package newsApp.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetOneNewsResponse implements Serializable {
    private Long id;
    private String title;
}
