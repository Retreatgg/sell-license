package com.axelor.apps.selllicenseplates2.mapper;

import com.axelor.apps.selllicenseplates2.dto.PostDto;
import com.axelor.apps.selllicenseplates2.model.Post;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-14T16:49:24+0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostDto toDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostDto postDto = new PostDto();

        postDto.setId( post.getId() );
        postDto.setTitle( post.getTitle() );
        postDto.setDescription( post.getDescription() );
        postDto.setCreatedDate( post.getCreatedDate() );
        postDto.setUpdatedDate( post.getUpdatedDate() );
        postDto.setImageUrl( post.getImageUrl() );

        return postDto;
    }

    @Override
    public List<PostDto> toDtoList(List<Post> postList) {
        if ( postList == null ) {
            return null;
        }

        List<PostDto> list = new ArrayList<PostDto>( postList.size() );
        for ( Post post : postList ) {
            list.add( toDto( post ) );
        }

        return list;
    }
}
