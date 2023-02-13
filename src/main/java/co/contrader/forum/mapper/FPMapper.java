package co.contrader.forum.mapper;

import co.contrader.forum.dto.ForumPostDTO;
import co.contrader.forum.dto.ForumTopicDTO;
import co.contrader.forum.model.ForumPost;
import co.contrader.forum.model.ForumTopic;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FPMapper {

    FPMapper INSTANCE = Mappers.getMapper(FPMapper.class);

    ForumPost toEntity(ForumPostDTO forumPostDTO);

    ForumPostDTO toDto(ForumPost forumPost);

    List<ForumPost> toEntityList(Iterable<ForumPostDTO> forumPostDTOS);

    List<ForumPostDTO> toDtoList(Iterable<ForumPost> forumPosts);
}
