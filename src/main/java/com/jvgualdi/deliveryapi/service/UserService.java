package com.jvgualdi.deliveryapi.service;

import com.jvgualdi.deliveryapi.model.AppUser;
import com.jvgualdi.deliveryapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    public void save (AppUser appUser){
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        userRepository.save(appUser);
    }

    public ResponseEntity<Boolean> login (String username, String password){
        Optional<AppUser> user = userRepository.findByUsername(username);
        if (!user.isPresent()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        boolean valid = passwordEncoder.matches(password, user.get().getPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(valid);
    }

}