/*
package com.example.fotowoltaika.service;

import com.example.fotowoltaika.domain.User;
import com.example.fotowoltaika.domain.UserJPARepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserJPARepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public  UserService(UserJPARepository userRepository){
        this.userRepository = userRepository;
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public void signup(String username, String password, String role, String firstName, String lastName, String email) {
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        userRepository.save(new User(username, encodedPassword, role, firstName, lastName, email));
    }
}
*/
