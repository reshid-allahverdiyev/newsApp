package newsApp.response;

import lombok.Data;

@Data
public class GeneralResponse<T> {
    T data;
    public   T getData(){
        return data;
    }
}
