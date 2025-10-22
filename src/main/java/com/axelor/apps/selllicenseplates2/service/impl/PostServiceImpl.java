package com.axelor.apps.selllicenseplates2.service.impl;

import com.axelor.apps.selllicenseplates2.dto.PostCreateRequest;
import com.axelor.apps.selllicenseplates2.dto.PostDto;
import com.axelor.apps.selllicenseplates2.mapper.PostMapper;
import com.axelor.apps.selllicenseplates2.model.Post;
import com.axelor.apps.selllicenseplates2.repository.PostRepository;
import com.axelor.apps.selllicenseplates2.service.PostService;
import com.axelor.apps.selllicenseplates2.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final FileUtils fileUtils;
    private final PostMapper postMapper;

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return postMapper.toDtoList(posts);
    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        return postMapper.toDto(post);
    }

    @Override
    public PostDto createPost(PostCreateRequest request) {
        Post post = Post.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .createdDate(Instant.now())
                .updatedDate(Instant.now())
                .build();

        String filePath = fileUtils.saveUploadedFile(request.getImage(), "/images");
        post.setImageUrl(filePath);

        Post savedPost = postRepository.save(post);
        return postMapper.toDto(savedPost);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public PostDto updatePost(Long postId, PostCreateRequest request) {
        Post post = postRepository.findById(postId).orElseThrow();

        post.setTitle(request.getTitle());
        post.setDescription(request.getDescription());
        post.setUpdatedDate(Instant.now());

        if (request.getImage() != null && !request.getImage().isEmpty()) {
            String filePath = fileUtils.saveUploadedFile(request.getImage(), "/images");
            post.setImageUrl(filePath);
        }

        Post updatedPost = postRepository.save(post);
        return postMapper.toDto(updatedPost);
    }
}
