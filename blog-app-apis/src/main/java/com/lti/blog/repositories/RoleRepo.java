package com.lti.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.blog.entities.Role;

public interface RoleRepo extends JpaRepository <Role, Integer>{

}
