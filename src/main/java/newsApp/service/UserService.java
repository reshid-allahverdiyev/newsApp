package newsApp.service;


import lombok.RequiredArgsConstructor;
import newsApp.config.JwtUtil;
import newsApp.entity.UserEntity;
import newsApp.repository.mysql.UserRepository;
import newsApp.request.LoginRequest;
import newsApp.response.AuthResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userFromDb = userRepository.findByUsername(username);
        User.UserBuilder userBuilder = null;
        if (Objects.nonNull(userFromDb)) {
            userBuilder = User.withUsername(username);
            userBuilder.disabled(false);
            userBuilder.password(userFromDb.getPassword());
            userBuilder.authorities("ADMIN");
        } else {
            throw new RuntimeException();
        }
        return userBuilder.build();
    }

    public AuthResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken credential = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(credential);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtil.createTokenAndSession(userDetails);
    }
}
