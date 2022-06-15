package com.jvgualdi.deliveryapi.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jvgualdi.deliveryapi.data.UserDataDetail;
import com.jvgualdi.deliveryapi.dto.AppUserDTO;
import com.jvgualdi.deliveryapi.model.AppUser;
import com.jvgualdi.deliveryapi.repository.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    public final UserRepository userRepository;

    public final PasswordEncoder passwordEncoder;

    final AuthenticationManager authenticationManager;

    public static final int EXPIRE_TOKEN = 600_0000;

    public static final String PASSWORD_TOKEN = "d6adefb7-359b-4bd5-80b8-4b9be27b04f6";

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public void save (AppUser appUser){
        Optional<AppUser> user = userRepository.findByUsername(appUser.getUsername());

        if (user.isPresent()){
            throw new Error("User already exists!");
        }
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        userRepository.save(appUser);
    }

    public ResponseEntity<UserDataDetail> signin(AppUserDTO appUserDTO){
        Authentication authenticate =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUserDTO.getUsername(), appUserDTO.getPassword(), new ArrayList<>()));

        UserDataDetail userData = (UserDataDetail) authenticate.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC512(PASSWORD_TOKEN);
        String access_token = JWT.create().withSubject(userData.getUsername()).
                withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TOKEN)).sign(algorithm);

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, access_token)
                .body(userData);
    }

}
