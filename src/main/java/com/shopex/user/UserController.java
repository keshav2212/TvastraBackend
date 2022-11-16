package com.shopex.user;

import com.shopex.Message;
import com.shopex.exceptions.UserNameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserPrincipleService userPrincipleService;



    @PostMapping(path = "/login")
    public String login(Principal principal){
        return principal.getName();
    }

    @PostMapping(path = "/register")
    public Message register(@Valid @RequestBody UserDTO newUser)  {
        try {
            userPrincipleService.saveUser(newUser);
            return new Message("Registered successfully");
        } catch (UserNameAlreadyExistsException e) {
            return new Message("Username already exists");
        }
    }


}
