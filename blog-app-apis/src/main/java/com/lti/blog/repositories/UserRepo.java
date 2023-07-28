package com.lti.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	

}
