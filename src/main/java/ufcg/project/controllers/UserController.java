package ufcg.project.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import ufcg.project.DTOs.Email;
import ufcg.project.DTOs.Password;
import ufcg.project.DTOs.UserDTOpass;
import ufcg.project.entities.User;
import ufcg.project.services.JWTService;
import ufcg.project.services.UserService;
import ufcg.project.services.EmailService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private EmailService emailService;

    private final String TOKEN_KEY = "L112haSj78r4944i8khsSi6hhA";

    @PostMapping("/user/register")
    public ResponseEntity<User> addUser(@RequestBody User user) {

        User u = service.addUser(user);
        if(u != null) {
            return new ResponseEntity<User>(u, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/user/forgot")
    public ResponseEntity<Boolean> forgotPassword(@RequestBody Email email, HttpServletRequest request){
        Optional<User> u = service.getUser(email.getEmail());

        if(!u.isPresent()){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }else{
            User user = u.get();
            String token = Jwts.builder().setSubject(u.get().getEmail()).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                    .setExpiration(new Date(System.currentTimeMillis() + 50 * 60 * 10000)).compact();
            user.setToken(token);
            service.updateUser(user);
            System.out.println(user.toString());
            String appURL = request.getScheme() + "://" + request.getServerName() + ":8080/user/reset?token=" + user.getToken();
            emailService.recoverMail(user.getFirstName(), appURL, user.getEmail());
            return new ResponseEntity<>(true, HttpStatus.OK);
        }

    }

    @PostMapping(value = "/user/reset")
    public ResponseEntity<Boolean> resetPassword(@RequestParam("token") String token, @RequestBody Password new_password) throws ServletException {

        Optional<User> optional = service.getUserByToken(token);
        System.out.println("DEBUG TOKEN " + token);

        if(optional.isPresent()){
            User resetUser = optional.get();
            if(jwtService.userHasPermission("Bearer " + token, resetUser.getEmail())){
                resetUser.setPassword(new_password.getPassword());
                resetUser.setToken("");
                service.addUser(resetUser);
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
            }

        }else{
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/user/changepassword")
    public ResponseEntity<Boolean> changePassword(@RequestBody UserDTOpass user, @RequestHeader("Authorization") String header) throws ServletException {

        Optional<User> authUser = service.getUser(user.getEmail());
        if(!authUser.get().getPassword().equals(user.getPassword()) || !jwtService.userHasPermission(header, user.getEmail())){
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }
        if(authUser.isEmpty()){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

        service.updatePassword(user.getEmail(), user.getNewPassword());
        return new ResponseEntity<>(true, HttpStatus.OK);
    }


}


