package com.saravjot.journalBackend.controller;

import com.saravjot.journalBackend.entity.User;
import com.saravjot.journalBackend.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<?>saveUser(@RequestBody User myUser){
        User user = userService.save(myUser);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?>getUsers(){
        List<User> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") ObjectId id, @RequestBody User myUser){
        User user = userService.update(id, myUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") ObjectId id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
