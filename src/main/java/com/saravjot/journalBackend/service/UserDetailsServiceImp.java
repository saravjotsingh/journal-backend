package com.saravjot.journalBackend.service;

import com.saravjot.journalBackend.entity.User;
import com.saravjot.journalBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try{
            return userRepository.getUserByEmail(email);
        }catch (Exception e){
            System.out.println(e);
            throw e;
        }
    }
}
