package com.tvastra.user;

import com.tvastra.exceptions.UserNameAlreadyExistsException;
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

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User saveUser(UserDTO newUser) throws UserNameAlreadyExistsException {
        if (findByUsername(newUser.getUsername()) == null) {
            String password = passwordEncoder.encode(newUser.getPassword());
            return userRepository.save(new User(newUser.getUsername(), password, newUser.getRole()));
        } else {
            throw new UserNameAlreadyExistsException();
        }
    }

}
