package com.mediserve.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mediserve.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email); 
	
}
