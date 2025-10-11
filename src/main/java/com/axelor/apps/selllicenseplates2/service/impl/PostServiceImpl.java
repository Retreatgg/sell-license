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
}
