package com.jvgualdi.deliveryapi.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jvgualdi.deliveryapi.data.UserDataDetail;
import com.jvgualdi.deliveryapi.dto.AppUserDTO;
import com.jvgualdi.deliveryapi.model.AppUser;
import com.jvgualdi.deliveryapi.repository.UserRepository;
import com.jvgualdi.deliveryapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "deliveryapi")
public class UserController {

    final UserRepository userRepository;

    final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/list")
    @Operation(summary = "List All Users", security = @SecurityRequirement(name = "deliveryapi"))
    public List<AppUser> findAll(){
        return userRepository.findAll();
    }

    @PostMapping("/register")
    public void register(@RequestBody AppUser appUser){
        userService.save(appUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDataDetail> validatePassword (@RequestBody AppUserDTO appUserDTO){
        return userService.signin(appUserDTO);
    }

}
