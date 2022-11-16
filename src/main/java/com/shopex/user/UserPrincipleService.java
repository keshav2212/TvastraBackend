package com.shopex.user;

import com.shopex.exceptions.UserNameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserPrincipleService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        return new UserPrinciple(user);
    }

    private User findByUsername(String username) {
        try {
            return userRepository.findByUsername(username).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void saveUser(UserDTO newUser) throws UserNameAlreadyExistsException {
        if(findByUsername(newUser.getUsername())==null){
            String password = passwordEncoder.encode(newUser.getPassword());
            userRepository.save(new User(newUser.getUsername(), password, newUser.getRole()));
        }else{
            throw new UserNameAlreadyExistsException();
        }
    }

}
