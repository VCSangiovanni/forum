package co.contrader.forum.mapper;

import co.contrader.forum.dto.ForumTopicDTO;
import co.contrader.forum.model.ForumTopic;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FTMapper {

    FTMapper INSTANCE = Mappers.getMapper(FTMapper.class);

    ForumTopic toEntity(ForumTopicDTO forumTopicDTO);

    ForumTopicDTO toDto(ForumTopic forumTopic);

    List<ForumTopic> toEntityList(Iterable<ForumTopicDTO> forumTopicDTOS);

    List<ForumTopicDTO> toDtoList(Iterable<ForumTopic> forumTopics);
}
