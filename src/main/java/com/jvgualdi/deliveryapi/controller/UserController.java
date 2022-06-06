package com.jvgualdi.deliveryapi.controller;

import com.jvgualdi.deliveryapi.model.AppUser;
import com.jvgualdi.deliveryapi.repository.UserRepository;
import com.jvgualdi.deliveryapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserService userService;

    @GetMapping("/list")
    public List<AppUser> findAll(){
        return userRepository.findAll();
    }

    @PostMapping
    public void register(@RequestBody AppUser appUser){
        userService.save(appUser);
    }

    @GetMapping("/enter")
    public ResponseEntity<Boolean> validatePassword (@RequestParam String userName, @RequestParam String password){
        return userService.login(userName, password);
    }

}
