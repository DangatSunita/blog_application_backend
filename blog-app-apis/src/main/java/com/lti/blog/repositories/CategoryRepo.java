package com.lti.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
