package com.saravjot.journalBackend.service;

import com.saravjot.journalBackend.entity.User;
import com.saravjot.journalBackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User update(ObjectId id, User myUser){
        User userFromDb = userRepository.getUserById(id);
        if(userFromDb != null){
            userFromDb.setMobile(myUser.getMobile());
            userFromDb.setRoles(myUser.getRoles());
            return userRepository.save(userFromDb);
        }
        throw new RuntimeException("User not found");
    }

    public void delete(ObjectId id){
        userRepository.deleteById(id);
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public User findByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

}
