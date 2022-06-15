package com.jvgualdi.deliveryapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jvgualdi.deliveryapi.controller.UserController;
import com.jvgualdi.deliveryapi.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTValidationFilter extends BasicAuthenticationFilter {

    public static final String HEADER_ATTRIBUTE = "Authorization";
    public static final String ATTRIBUTE_PREFIX = "Bearer ";



    public JWTValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    //receives the every request and determines if the user has access
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String attribute = request.getHeader(HEADER_ATTRIBUTE);

        if (attribute == null || !attribute.startsWith(ATTRIBUTE_PREFIX)){
            chain.doFilter(request, response);
        }else {
            String token = attribute.replace(ATTRIBUTE_PREFIX, "");
            UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token){
        String username = JWT.require(Algorithm.HMAC512(UserService.PASSWORD_TOKEN)).build().verify(token).getSubject();

        if (username == null){
            return null;
        }
        //We don't need the password, because the user has already been authenticated and the token is valid
        return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
    }
}
