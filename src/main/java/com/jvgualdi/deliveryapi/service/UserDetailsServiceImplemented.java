package com.jvgualdi.deliveryapi.service;

import com.jvgualdi.deliveryapi.data.UserDataDetail;
import com.jvgualdi.deliveryapi.model.AppUser;
import com.jvgualdi.deliveryapi.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImplemented implements UserDetailsService {

    public final UserRepository userRepository;

    public UserDetailsServiceImplemented(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = userRepository.findByUsername(username);

        if (!user.isPresent()){
            throw new UsernameNotFoundException("User: " + username + " was not found");
        }
        //we may return a User from the class, not our user
        //do we implement an array of simpleGrantedAuthority
        return new UserDataDetail(user);
    }
}
