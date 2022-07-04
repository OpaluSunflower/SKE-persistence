package com.example.fotowoltaika.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPARepository extends JpaRepository<User, Long> {
        User findByUsername(String username);
    }

