package co.contrader.forum.mapper;

import co.contrader.forum.dto.ProfileDTO;
import co.contrader.forum.dto.UserDTO;
import co.contrader.forum.model.Profile;
import co.contrader.forum.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    Profile toEntity(ProfileDTO profileDTO);
    ProfileDTO toDto(Profile profile);
    List<Profile> toEntityList(Iterable<ProfileDTO>profileDTOS);
    List<ProfileDTO>toDtoList(Iterable<Profile>profiles);

}
