package com.jvgualdi.deliveryapi.controller;

import com.jvgualdi.deliveryapi.model.User;
import com.jvgualdi.deliveryapi.repository.UserRepository;
import com.jvgualdi.deliveryapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserService userService;

    @GetMapping("/list")
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @PostMapping
    public void register(@RequestBody User user){
        userService.save(user);
    }

    @GetMapping("/enter")
    public ResponseEntity<Boolean> validatePassword (@RequestParam String login, @RequestParam String password){
        return userService.login(login, password);
    }

}
