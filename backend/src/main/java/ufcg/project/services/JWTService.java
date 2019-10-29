package ufcg.project.services;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import ufcg.project.entities.User;

import javax.servlet.ServletException;
import java.util.Optional;

@Service
public class JWTService {

    private UserService userService;
    private final int TOKEN_INDEX = 7;
    private final String TOKEN_KEY = "L112haSj78r4944i8khsSi6hhA";

    public JWTService(UserService userService) {
        super();
        this.userService = userService;
    }

    public boolean usuarioExiste(String authorizationHeader) throws ServletException {
        String subject = getSujeitoDoToken(authorizationHeader);

        return userService.getUser(subject).isPresent();
    }

    public String getSujeitoDoToken(String authorizationHeader) throws ServletException {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new ServletException("Token inexistente ou mal formatado!");
        }

        String token = authorizationHeader.substring(TOKEN_INDEX);

        String subject = null;
        subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
        return subject;
    }

    public boolean usuarioTemPermissao(String authorizationHeader, String email) throws ServletException {
        String subject = getSujeitoDoToken(authorizationHeader);

        Optional<User> optUsuario = userService.getUser(subject);
        return optUsuario.isPresent() && optUsuario.get().getEmail().equals(email);
    }
}