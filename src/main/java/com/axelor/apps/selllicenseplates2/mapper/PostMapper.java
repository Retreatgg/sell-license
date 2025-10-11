package com.axelor.apps.selllicenseplates2.mapper;

import com.axelor.apps.selllicenseplates2.dto.PostDto;
import com.axelor.apps.selllicenseplates2.model.Post;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostDto toDto(Post post);
    List<PostDto> toDtoList(List<Post> postList);

}
