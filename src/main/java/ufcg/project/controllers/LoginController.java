package ufcg.project.controllers;

import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ufcg.project.DTOs.UserDTO;
import ufcg.project.entities.User;
import ufcg.project.services.JWTService;
import ufcg.project.services.UserService;
@CrossOrigin
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
    public ResponseEntity<LoginResponse> authenticate(@RequestBody UserDTO user) throws ServletException {

        // future user token
        String token = "";

        // recover the user
        Optional<User> authUser = userService.getUser(user.getEmail());

        // checks
        if (authUser.isEmpty()) {
        	return new ResponseEntity<LoginResponse>(new LoginResponse(token, false), HttpStatus.BAD_REQUEST);
        }

        if (!authUser.get().getPassword().equals(user.getPassword())) {
        	return new ResponseEntity<LoginResponse>(new LoginResponse(token, false), HttpStatus.UNAUTHORIZED);
        }

        if(user.getSavePassword()){
            token = Jwts.builder().setSubject(authUser.get().getEmail()).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 50 * 60 * 100000)).compact();
        }else{
            token = Jwts.builder().setSubject(authUser.get().getEmail()).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 50 * 60 * 10000)).compact();
        }
        
        return new ResponseEntity<LoginResponse>(new LoginResponse(token, true), HttpStatus.OK);

    }

    private class LoginResponse {
        public String token;
        public boolean ok;
        public LoginResponse(String token, boolean ok) {
            this.token = token;
            this.ok = ok;
        }
        public String getToken() {
        	return this.token;
        }
        
        public void setToken(String t) {
        	this.token = t;
        }
        
        public boolean getOk() {
        	return this.ok;
        }
        
        public void setOk(boolean nOK) {
        	this.ok = nOK;
        }
    }
}
