package com.skipthediches.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
            .disable()
        .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/products/**").permitAll()
            .antMatchers(HttpMethod.GET, "/customers/**").permitAll()
//            .antMatchers(HttpMethod.GET, "/customers/**").hasRole("USER")
            .anyRequest().authenticated()
            .and()
        .csrf()
            .disable();
    }
}
