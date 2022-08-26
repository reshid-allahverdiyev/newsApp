package newsApp.config;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import newsApp.entity.TokenEntityRedis;
import newsApp.mapper.ObjectMapper;
import newsApp.repository.redis.TokenRepositoryRedis;
import newsApp.response.AuthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class JwtUtil {
    @Value("${secret.jwt}")
    private String jwtSecret;
    private final TokenRepositoryRedis tokenRepositoryRedis;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public byte[] getJwtSecret() {
        return jwtSecret.getBytes();
    }

    public AuthResponse createTokenAndSession(UserDetails appUser) {
        AuthResponse authResponse = AuthResponse.builder()
                .username(appUser.getUsername())
                .token(accessToken(appUser))
                .build();
        tokenRepositoryRedis.save(objectMapper.authResponseToCache2(authResponse));
        return authResponse;
    }

    public TokenEntityRedis verifyToken(String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length());
        if (findAuthToken(token).isPresent()) {
            return findAuthToken(token).get();
        } else {
            throw new RuntimeException();
        }
    }

    public void invalidateToken(String authorizationHeader) {
        if (Objects.nonNull(authorizationHeader)) {
            String token = authorizationHeader.substring("Bearer ".length());
            if (findAuthToken(token).isPresent()) {
                tokenRepositoryRedis.deleteById(token);
            } else {
                throw new RuntimeException();
            }
        } else {
            throw new RuntimeException();
        }
    }

    private Optional<TokenEntityRedis> findAuthToken(String token) {
        return tokenRepositoryRedis.findById(token);
    }

    private String accessToken(UserDetails user) {
        return Jwts.builder()
                .claim("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000)).signWith(SignatureAlgorithm.HS256, getJwtSecret()).compact();
    }

    private String refreshToken(UserDetails user) {
        return Jwts.builder()
                .claim("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000)).signWith(SignatureAlgorithm.HS256, getJwtSecret()).compact();
    }
}
