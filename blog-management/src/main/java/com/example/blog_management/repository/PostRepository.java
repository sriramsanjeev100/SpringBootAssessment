package com.example.blog_management.repository;

import com.example.blog_management.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID>
{
    @Query("""
            SELECT p FROM Post p
            JOIN p.categories c
            WHERE c.id = :categoryId
            ORDER BY p.createdDate DESC
            """)

    Page<Post> findRecentPostsByCategory(@Param("categoryId") UUID categoryId, Pageable pageable);
}