package com.example.fotowoltaika.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


public interface BillJPARepository extends JpaRepository<Bill,Long> {
}
