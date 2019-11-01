package ufcg.project.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ufcg.project.DTOs.UserDTO;
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

    public LoginController(UserService userService, JWTService jwtService) {
        super();
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody UserDTO user) throws ServletException {

        // future user token
        String token = "";

        // recover the user
        Optional<User> authUser = userService.getUser(user.getEmail());

        // checks
        if (authUser.isEmpty()) {
            throw new ServletException("User not find!");
        }

        if (!authUser.get().getPassword().equals(user.getPassword())) {
            throw new ServletException("Invalid password!");
        }

        if(user.getSavePassword()){
            token = Jwts.builder().setSubject(authUser.get().getEmail()).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 50 * 60 * 100000)).compact();
        }else{
            token = Jwts.builder().setSubject(authUser.get().getEmail()).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 50 * 60 * 10000)).compact();
        }
        

        return new LoginResponse(token);

    }

    private class LoginResponse {
        public String token;

        public LoginResponse(String token) {
            this.token = token;
        }
    }
}
