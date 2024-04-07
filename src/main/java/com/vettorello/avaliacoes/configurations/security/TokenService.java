package com.vettorello.avaliacoes.configurations.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.vettorello.avaliacoes.entities.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${avaliacoes.security.token.secret}")
    private String segredo;
    public String gerarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(segredo);

            String token = JWT.create()
                    .withIssuer("avaliacoes")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(this.gerarDataExpiracao())
                    .sign(algorithm);

            return token;

        } catch (JWTCreationException e){
            throw new RuntimeException("Erro de autenticação");
        }
    }

    public String validarToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(segredo);
            return JWT.require(algorithm)
                    .withIssuer("avaliacoes")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e){
            return null;
        }
    }

    private Instant gerarDataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
