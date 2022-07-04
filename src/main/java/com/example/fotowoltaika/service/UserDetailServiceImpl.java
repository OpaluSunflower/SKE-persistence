/*
package com.example.fotowoltaika.service;

import com.example.fotowoltaika.domain.User;
import com.example.fotowoltaika.domain.UserJPARepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserJPARepository userRepository;

    public UserDetailServiceImpl(UserJPARepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User currentUser = userRepository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(
                username,
                currentUser.getPassword(),
                true,
                true,
                true,
                true,
                AuthorityUtils.createAuthorityList(currentUser.getRole()));
        return user;
    }
}

*/
