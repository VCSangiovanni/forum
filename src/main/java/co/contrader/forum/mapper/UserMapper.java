package co.contrader.forum.mapper;

import co.contrader.forum.dto.UserDTO;
import co.contrader.forum.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDTO user);
    UserDTO toDto(User user);
    List<User> toEntityList(Iterable<UserDTO>userDTOS);
    List<UserDTO>toDtoList(Iterable<User>users);


}
