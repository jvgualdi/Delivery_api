package com.jvgualdi.deliveryapi.security;

import com.jvgualdi.deliveryapi.service.UserDetailServiceImplemented;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class JWTConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailServiceImplemented userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public JWTConfiguration(UserDetailServiceImplemented userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //tells Spring how to look for the users
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Cross-Site Request Forgery, even though it prevents some unwanted calls by the browser without intervention from the end user
        //We disable it, because our application is token-based authorization, so we don't need CSRF enabled
        http.csrf().disable();

        //allow everyone to access the user mapping (register user)
        http.authorizeHttpRequests().antMatchers(HttpMethod.POST, "/user").permitAll();
        http.authorizeHttpRequests().antMatchers(HttpMethod.POST, "/login").permitAll();

        //enables everyone to make requests de application if they are authenticated
        http.authorizeHttpRequests().anyRequest().authenticated();

        //an authentication filter to check the user when they try to log in
        http.addFilter(new JWTAuthentitactionFilter(authenticationManager()));

        //validates the token passed at the requests
        http.addFilter(new JWTValidationFilter(authenticationManager()));

        //Doesn't save the user session on the database
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    //We can maintain blocked to external domains
//    @Bean
//    CorsConfigurationSource corsConfigurationSource () {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//
//        return source;
//    }
}
