package com.skipthediches.challenge.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class LoginDetailService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (!"user".equals(username)) {
            throw new UsernameNotFoundException("User not found!");
        }

        Set<GrantedAuthority> auths = new HashSet<>();
        auths.add(new SimpleGrantedAuthority("ROLE_".concat("USER")));

        return new User(username, bCryptPasswordEncoder.encode("password"), auths);
    }
}
