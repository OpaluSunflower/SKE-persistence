/*
package com.example.fotowoltaika.web;

import com.example.fotowoltaika.domain.User;
import com.example.fotowoltaika.domain.UserJPARepository;
//import com.example.fotowoltaika.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    //private final UserService userService;
    private final UserJPARepository userJPARepository;

    public UserController(UserService userService, UserJPARepository userJPARepository) {
        this.userService = userService;
        this.userJPARepository = userJPARepository;
    }

    @PostMapping("/signup")
    public HttpStatus signup(@RequestBody UserCredentialsDTO userCredentialsDTO){
        userService.signup(userCredentialsDTO.getUsername(),
                userCredentialsDTO.getPassword(),
                "USER",
                userCredentialsDTO.getFirstName(),
                userCredentialsDTO.getSecondName(),
                userCredentialsDTO.getEmail());
        return HttpStatus.OK;
    }

    @RequestMapping("/userData")
    public Iterable<User> getUserData(){
        return userJPARepository.findAll();
    }
}
*/
