package com.Sigma.SigmaBackEnd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    private static final String API_URL_PATTERN = "/**";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);

        http
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.csrf(csrfConfigurer ->
                csrfConfigurer.ignoringRequestMatchers(mvcMatcherBuilder.pattern(API_URL_PATTERN),
                        PathRequest.toH2Console()));

        http.headers(headersConfigurer ->
                headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        http.cors(cors -> cors.configure(http));

        http.authorizeHttpRequests(auth ->
                auth
                        .requestMatchers(mvcMatcherBuilder.pattern(API_URL_PATTERN + "login")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern(
                                API_URL_PATTERN + "product/all")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern(
                                API_URL_PATTERN + "product/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern(
                                HttpMethod.POST,API_URL_PATTERN + "product")).hasRole("ADMIN")
                        .requestMatchers(mvcMatcherBuilder.pattern(
                                API_URL_PATTERN + "product?categoryName=**")).permitAll()
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .anyRequest().authenticated()
        );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
