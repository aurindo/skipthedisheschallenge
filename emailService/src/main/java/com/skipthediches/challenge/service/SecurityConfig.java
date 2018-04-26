package com.skipthediches.challenge.service;

import com.skipthediches.challenge.service.security.LoginDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginDetailService loginDetailService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginDetailService).passwordEncoder(getPasswordEnconded());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable().authorizeRequests()
                .antMatchers("/senderEmail/**").permitAll().anyRequest()
//                .antMatchers("/customerOrders/**").hasAnyRole("USER", "ADMIN").anyRequest()
                .authenticated().and().csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEnconded() {
        return new BCryptPasswordEncoder();
    }
}
