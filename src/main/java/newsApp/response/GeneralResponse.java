package newsApp.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GeneralResponse<T>   implements  Serializable  {
    T data;
    public   T getData(){
        return data;
    }
}
