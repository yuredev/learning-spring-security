package learning.security.secure_post.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import learning.security.secure_post.models.User;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            // withIssuer é o emissor do token, no caso a própria aplicação
            // withSubject é o usuário que está fazendo a autenticação, pra através do token
            // conseguirmos identificar o usuário
            var token = JWT.create().withIssuer("secure-post").withSubject(user.getLogin())
                    .withExpiresAt(this.generateExpirationDate()).sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token", e);
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
