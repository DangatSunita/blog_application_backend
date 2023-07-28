package com.lti.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
