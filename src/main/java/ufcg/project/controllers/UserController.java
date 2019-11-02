package ufcg.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ufcg.project.DTOs.UserDTOpass;
import ufcg.project.entities.User;
import ufcg.project.services.UserService;

import java.util.Optional;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/user/register")
    public ResponseEntity<User> addUser(@RequestBody User user) {

        User u = service.addUser(user);
        if(u != null) {
            return new ResponseEntity<User>(u, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/user/changepassword")
    public ResponseEntity<Boolean> changePassword(@RequestBody UserDTOpass user){
        Optional<User> authUser = service.getUser(user.getEmail());

        if(authUser.isEmpty()){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

        if(!authUser.get().getPassword().equals(user.getPassword())){
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }

        service.updatePassword(user.getEmail(), user.getNewPassword());
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}


