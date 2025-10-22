package com.axelor.apps.selllicenseplates2.service;

import com.axelor.apps.selllicenseplates2.dto.PostCreateRequest;
import com.axelor.apps.selllicenseplates2.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    List<PostDto> getAllPosts();
    PostDto getPostById(Long postId);
    PostDto createPost(PostCreateRequest request);
    void deletePost(Long postId);
    PostDto updatePost(Long postId, PostCreateRequest request);

}
