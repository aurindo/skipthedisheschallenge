package com.skipthediches.challenge.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("user").password("{noop}password").roles("USER")
                .and()
            .withUser("admin").password("{noop}admin").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin()
            .and()
        .authorizeRequests()
            .antMatchers("/withoutAuthentication.html").permitAll()
//            .antMatchers("/challenge-service/products").permitAll()
            .antMatchers("/challenge-service/**").hasAnyRole("USER", "ADMIN")
            .antMatchers("/order-service/**").hasAnyRole("USER", "ADMIN")
            .antMatchers("/home.html").hasAnyRole("USER", "ADMIN")
            .anyRequest().authenticated()
            .and()
        .logout()
            .logoutSuccessUrl("/out_application.html")
            .and()
        .csrf().disable();

    }
}
