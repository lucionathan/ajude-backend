package ufcg.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ufcg.project.DTOs.UserDTOpass;
import ufcg.project.entities.User;
import ufcg.project.services.JWTService;
import ufcg.project.services.UserService;

import javax.servlet.ServletException;
import java.util.Optional;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/user/register")
    public ResponseEntity<User> addUser(@RequestBody User user) {

        User u = service.addUser(user);
        if(u != null) {
            return new ResponseEntity<User>(u, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/user/changepassword")
    public ResponseEntity<Boolean> changePassword(@RequestBody UserDTOpass user, @RequestHeader("Authorization") String header) throws ServletException {

        Optional<User> authUser = service.getUser(user.getEmail());
        if(!authUser.get().getPassword().equals(user.getPassword()) || !jwtService.userExists(header)){
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }
        if(authUser.isEmpty()){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

        service.updatePassword(user.getEmail(), user.getNewPassword());
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}


