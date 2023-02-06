package co.contrader.forum.mapper;

import co.contrader.forum.dto.ForumCategoryDTO;
import co.contrader.forum.model.ForumCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FCatMapper {

    FCatMapper INSTANCE = Mappers.getMapper(FCatMapper.class);

    ForumCategory toEntity(ForumCategoryDTO forumCategoryDTO);
    ForumCategoryDTO toDto(ForumCategory forumCategory);
    List<ForumCategory> toEntityList(Iterable<ForumCategoryDTO>forumCategoryDTOS);
    List<ForumCategoryDTO>toDtoList(Iterable<ForumCategory>forumCategories);
}
