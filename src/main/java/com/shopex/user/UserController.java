package com.shopex.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path = "/user")
public class UserController {

//    @Autowired
//    UserPrincipleService userPrincipleService;

    @GetMapping(path = "/login")
    public String login(Principal principal){
        return principal.getName();
    }
}