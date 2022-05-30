package com.jvgualdi.deliveryapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jvgualdi.deliveryapi.data.UserDataDetail;
import com.jvgualdi.deliveryapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthentitactionFilter extends UsernamePasswordAuthenticationFilter {

    public static final int EXPIRE_TOKEN = 600_000;

    public static final String PASSWORD_TOKEN = "d6adefb7-359b-4bd5-80b8-4b9be27b04f6";

    private final AuthenticationManager authenticationManager;


    public JWTAuthentitactionFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword(), new ArrayList<>()));

        } catch (IOException e) {
            throw new RuntimeException("User not authenticated ", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDataDetail userDataDetail = (UserDataDetail) authResult.getPrincipal();

        String token = JWT.create().withSubject(userDataDetail.getUsername()).
                withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TOKEN)).sign(Algorithm.HMAC512(PASSWORD_TOKEN));

        response.getWriter().write(token);
        response.getWriter().flush();

    }
}
