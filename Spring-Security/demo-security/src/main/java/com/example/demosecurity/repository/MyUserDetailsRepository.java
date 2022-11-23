package com.example.demosecurity.repository;

import com.example.demosecurity.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserDetailsRepository extends JpaRepository<MyUser, Integer> {

    MyUser findByEmail(String username);
}
