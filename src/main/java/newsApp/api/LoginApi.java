package newsApp.api;

import newsApp.request.LoginRequest;
import newsApp.response.AuthResponse;
import newsApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginApi {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public AuthResponse login(LoginRequest request){
        return userService.login(request);
    }

    @GetMapping("/ping")
    public AuthResponse test(
            @RequestBody LoginRequest request
    ){
        return userService.login(request);
    }
}
