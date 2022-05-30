package com.jvgualdi.deliveryapi.service;

import com.jvgualdi.deliveryapi.data.UserDataDetail;
import com.jvgualdi.deliveryapi.model.User;
import com.jvgualdi.deliveryapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImplemented implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(username);

        if (!user.isPresent()){
            throw new UsernameNotFoundException("User: " + username + " was not found");
        }
        return new UserDataDetail(user);
    }
}
