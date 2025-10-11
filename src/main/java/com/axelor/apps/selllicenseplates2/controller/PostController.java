package com.axelor.apps.selllicenseplates2.controller;

import com.axelor.apps.selllicenseplates2.dto.PostCreateRequest;
import com.axelor.apps.selllicenseplates2.dto.PostDto;
import com.axelor.apps.selllicenseplates2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId) {
        PostDto post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<PostDto> createPost(@ModelAttribute PostCreateRequest request) {
        PostDto createdPost = postService.createPost(request);
        return ResponseEntity.ok(createdPost);
    }
}
