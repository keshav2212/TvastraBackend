package com.shopex.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserPrincipleService userPrincipleService;

    @Autowired
    UserService service;

    @GetMapping(path = "/login")
    public String login(Principal principal){
        return principal.getName();
    }

    @PostMapping(path = "/register")
    public void register(@RequestBody UserDTO newUser){
        service.saveUser(newUser);
    }


}
