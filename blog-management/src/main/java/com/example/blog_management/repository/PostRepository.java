package com.example.blog_management.repository;

import com.example.blog_management.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer>
{

}