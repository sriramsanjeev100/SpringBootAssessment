package com.example.blog_management.service;

import com.example.blog_management.dto.request.PostRequest;
import com.example.blog_management.dto.response.CategoryResponse;
import com.example.blog_management.dto.response.PostResponse;
import com.example.blog_management.entity.Category;
import com.example.blog_management.entity.Post;
import com.example.blog_management.entity.User;
import com.example.blog_management.exception.CategoryNotFoundException;
import com.example.blog_management.exception.PostNotFoundException;
import com.example.blog_management.exception.UserNotFoundException;
import com.example.blog_management.repository.CategoryRepository;
import com.example.blog_management.repository.PostRepository;
import com.example.blog_management.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class PostService
{
    private static final Logger log = LoggerFactory.getLogger(PostService.class);
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository, CategoryRepository categoryRepository)
    {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public PostResponse createPost(PostRequest request)
    {
        log.info("Creating post with title: {} for user: {}", request.title(), request.userId());
        Post post = new Post();
        setPostFields(post, request);
        post.setCreatedDate(LocalDateTime.now());
        Post savedPost = postRepository.save(post);
        log.info("Post created successfully with id: {}", savedPost.getId());
        return mapToResponse(savedPost);
    }

    public List<PostResponse> getAllPosts()
    {
        log.info("Fetching all posts");
        return postRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public PostResponse getPost(UUID id)
    {
        log.info("Fetching post with id: {}", id);
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));

        return mapToResponse(post);
    }

    public PostResponse updatePost(UUID id, PostRequest request)
    {
        log.info("Updating post with id: {}", id);
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));

        setPostFields(post, request);
        return mapToResponse(post);
    }

    public void deletePost(UUID id)
    {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));

        postRepository.delete(post);
        log.info("Deleted post with id: {}", id);
    }

    private void setPostFields(Post post, PostRequest request)
    {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + request.userId()));

        Set<Category> categories = getCategories(request.categoryIds());
        post.setTitle(request.title());
        post.setContent(request.content());
        post.setUser(user);
        post.setCategories(categories);
    }

    private Set<Category> getCategories(List<UUID> categoryIds)
    {
        Set<Category> categories = new HashSet<>();
        if (categoryIds == null)
        {
            return categories;
        }

        for (UUID categoryId : categoryIds)
        {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + categoryId));
            categories.add(category);
        }
        return categories;
    }

    public Page<PostResponse> findRecentPostsByCategory(UUID categoryId, int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findRecentPostsByCategory(categoryId, pageable);
        return posts.map(this::mapToResponse);
    }

    private PostResponse mapToResponse(Post post)
    {
        List<CategoryResponse> categories = post.getCategories()
                        .stream()
                        .map(category -> new CategoryResponse(category.getId(), category.getName()))
                        .toList();

        return new PostResponse(post.getId(), post.getTitle(), post.getContent(), post.getCreatedDate(), post.getUser().getId(), categories);
    }
}