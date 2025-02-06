package br.com.luger.dev.precad.service;


import br.com.luger.dev.precad.model.Users;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${secret.key}")
    private String keySecret;

    public String gerarToken(Users userModel) {
        System.out.println("TokenServise gerarToken line 21");
        return JWT.create()
                .withIssuer("Safe")
                .withSubject(userModel.getUsername())
                .withClaim("user_id", userModel.getId().toString())
                .withClaim("admin", userModel.getAdmin().toString())
                .withClaim("PA", userModel.getPrimary_access().toString())
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(30)
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256(keySecret));
    }

    public String gerarTokenAdmin(Users userModel) {
        return JWT.create()
                .withIssuer("Safe")
                .withSubject(userModel.getUsername())
                .withClaim("user_id", userModel.getId().toString())
                .withClaim("admin", userModel.getAdmin().toString())
                .withClaim("PA", userModel.getAdmin().toString())
                .withExpiresAt(LocalDateTime.now()
                        .plusSeconds(30)
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256(keySecret));
    }


    public DecodedJWT getDecodedJWT(String token) {
        System.out.println("TokenServise getDecodedJWT line 49");
        DecodedJWT out = JWT.require(Algorithm.HMAC256(keySecret))
                .withIssuer("Safe")
                .build().verify(token);
        return out;
    }

    public String getSubject(String token) {

        System.out.println("TokenServise getSubject line 57");

        String out = JWT.require(Algorithm.HMAC256(keySecret))
                .withIssuer("Safe")
                .build().verify(token).getSubject();
        System.out.println(out);
        return out;
    }
}
