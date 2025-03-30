package com.saravjot.journalBackend.controller;

import com.saravjot.journalBackend.entity.User;
import com.saravjot.journalBackend.service.AuthService;
import com.saravjot.journalBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User myUser){
        User user = authService.registerUser(myUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User myUser){
        System.out.println("mysuer" + myUser);
        String jwtToken = authService.loginUser(myUser.getEmail(), myUser.getPassword());
        System.out.println(jwtToken);
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }
}
