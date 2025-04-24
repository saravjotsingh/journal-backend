package com.saravjot.journalBackend.service;

import com.saravjot.journalBackend.entity.User;
import com.saravjot.journalBackend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    private PasswordEncoder passwordEncoder;

    public User registerUser(User user){
        return userService.save(user);
    }


    public String loginUser(String email, String password) {
            User user = userService.findByEmail(email);
            System.out.println(user);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            System.out.println("hello" + userDetails);
            return jwtUtil.generateToken(user.getEmail(), user.getId());
    }

}
