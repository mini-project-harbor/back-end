package com.enigma.pascal.api.controllers;

import com.enigma.pascal.api.models.UserModel;
import com.enigma.pascal.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserModel> login(@RequestBody UserModel userModel){
        try {
            return new ResponseEntity<>(userService.getUser(userModel),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
