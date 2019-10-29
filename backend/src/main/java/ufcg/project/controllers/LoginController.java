package ufcg.project.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ufcg.project.entities.User;
import ufcg.project.services.JWTService;
import ufcg.project.services.UserService;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.Optional;

@RestController
public class LoginController {

    private final String TOKEN_KEY = "L112haSj78r4944i8khsSi6hhA";

    private UserService userService;
    private JWTService jwtService;

    public LoginController(UserService usuariosService, JWTService jwtService) {
        super();
        this.userService = usuariosService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody User user) throws ServletException {

        // Recupera o usuario
        Optional<User> authUsuario = userService.getUser(user.getEmail());

        // verificacoes
        if (authUsuario.isEmpty()) {
            throw new ServletException("User not find!");
        }

        if (!authUsuario.get().getPassword().equals(user.getPassword())) {
            throw new ServletException("Invalid password!");
        }


        String token = Jwts.builder().setSubject(authUsuario.get().getEmail()).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 50 * 60 * 10000)).compact();

        return new LoginResponse(token);

    }

    private class LoginResponse {
        public String token;

        public LoginResponse(String token) {
            this.token = token;
        }
    }
}
