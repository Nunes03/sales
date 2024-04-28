package io.github.nunes03.services;

import io.github.nunes03.VendasApplication;
import io.github.nunes03.entities.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken(Usuario usuario) {
        final var expiracaoLong = Long.valueOf(this.expiracao);
        final var dataExpiracao = LocalDateTime.now().plusMinutes(expiracaoLong);
        final var instant = dataExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        final var date = Date.from(instant);

        return Jwts
            .builder()
            .setSubject(usuario.getLogin())
            .setExpiration(date)
            .setClaims(Map.of("roles", List.of("admin", "user"), "completeUser", usuario))
            .signWith(SignatureAlgorithm.HS512, this.chaveAssinatura)
            .compact();
    }

    private Claims getClaims(String token) {
        return Jwts
            .parser()
            .setSigningKey(this.chaveAssinatura)
            .parseClaimsJws(token)
            .getBody();
    }

    public Boolean tokenValido(String token) {
        try {
            return getClaims(token)
                .getExpiration()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
                .isBefore(LocalDateTime.now());
        } catch (Exception exception) {
            return Boolean.FALSE;
        }
    }

    public String getLoginUsuario(String token) {
        return getClaims(token).getSubject();
    }

    public static void main(String[] args) {
        final var context = SpringApplication.run(VendasApplication.class);
        final var service = context.getBean(JwtService.class);

        final var usuario = new Usuario();
        usuario.setLogin("login");

        final var token = service.gerarToken(usuario);
        final var valido = service.tokenValido(token);
        final var login = service.getLoginUsuario(token);

        System.err.println(token + " | " + valido + " | " + login);//todo aula 79 finalizada, mas tem que corrigir esse cara
    }
}
