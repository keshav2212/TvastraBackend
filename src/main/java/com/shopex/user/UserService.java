package com.shopex.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String findByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(User::getUsername).orElse(null);

    }

    public void saveUser(UserDTO newUser){
       userRepository.save(new User(newUser.getUsername(), newUser.getPassword(), newUser.getRole()));
    }
}

