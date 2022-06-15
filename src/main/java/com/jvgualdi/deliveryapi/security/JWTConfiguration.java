package com.jvgualdi.deliveryapi.security;

import com.jvgualdi.deliveryapi.repository.UserRepository;
import com.jvgualdi.deliveryapi.service.UserDetailsServiceImplemented;
import com.jvgualdi.deliveryapi.service.UserService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@SecurityScheme(name="deliveryapi", scheme= "bearer", bearerFormat = "JWT", type= SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER, description = "Enter the Bearer Token")
public class JWTConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImplemented userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public JWTConfiguration(UserDetailsServiceImplemented userDetailService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailService;
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
        http.authorizeRequests().antMatchers("/user/register").permitAll();
        http.authorizeRequests().antMatchers("/user/login").permitAll();
        http.authorizeRequests().antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll();

        //enables everyone to make requests de application if they are authenticated
        http.authorizeRequests().anyRequest().authenticated();

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

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
