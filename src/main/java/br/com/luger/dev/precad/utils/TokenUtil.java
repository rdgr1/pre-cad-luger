package br.com.luger.dev.precad.utils;

import br.com.luger.dev.precad.service.TokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class TokenUtil {
    private final TokenService tokenService;
    @Value("${secret.key}")
    private String keySecret;

    @Autowired
    public TokenUtil(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    private String token(HttpHeaders headers) {
        return Objects.requireNonNull(headers
                        .get("Authorization"))
                .get(0)
                .replace("Bearer ", "");
    }

    public UUID getUserIdByTokeni(HttpHeaders headers) {
        return UUID.fromString(tokenService.getDecodedJWT(token(headers))
                .getClaim("user_id")
                .asString());
    }

    public Boolean isFirstAccess(HttpHeaders headers) {
        return Boolean.parseBoolean(tokenService.getDecodedJWT(token(headers))
                .getClaim("PA")
                .asString());
    }

    public Boolean isAdmin(HttpHeaders headers) {
        return Boolean.parseBoolean(tokenService.getDecodedJWT(token(headers))
                .getClaim("admin")
                .asString());
    }

    public String getSubjectByHeader(HttpHeaders headers) {
        String out = tokenService.getDecodedJWT(token(headers)).getSubject();
        System.out.println(out);
        return out;
    }


    public UUID getUserIdByToken(HttpHeaders headers) {
        String token = Objects.requireNonNull(headers.get("Authorization")).get(0);
        String jwt = token.replace("Bearer ", "");
        return UUID.fromString(JWT.require(Algorithm.HMAC256(keySecret))
                .withIssuer("Safe")
                .build().verify(jwt).getClaim("user_id").asString());
    }

}