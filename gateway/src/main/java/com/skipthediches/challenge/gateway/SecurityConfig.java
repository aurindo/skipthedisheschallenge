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
        auth.inMemoryAuthentication().withUser("user").password("{noop}password")
                .roles("USER").and().withUser("admin").password("{noop}admin")
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().
//                antMatchers("/challenge-service/**").permitAll().
//                antMatchers("/order-service/**").permitAll().
//                antMatchers("/discovery/**").hasRole("ADMIN").
//                    anyRequest().authenticated().and().formLogin().and().
//                logout().permitAll().
//                logoutSuccessUrl("/out_application.html")
//                    .permitAll().
//                and().csrf().disable();


//        http
//                .formLogin()
//                .defaultSuccessUrl("/home.html", true)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/challenge-service/**", "/order-service/**", "/discovery/**", "/").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .logout().logoutSuccessUrl("/out_application.html")
//                .and()
//                .csrf().disable();


            http.authorizeRequests()
                    .antMatchers("/withoutAuthentication.html").permitAll()
                    .antMatchers("/challenge-service/products").permitAll()
                    .antMatchers("/challenge-service/customers").hasRole("USER")
                    .antMatchers("/home.html").hasRole("USER")
                    .anyRequest().authenticated().and().formLogin().and()
                    .logout().permitAll().logoutSuccessUrl("/out_application.html")
                    .permitAll().and().csrf().disable();

    }
}
