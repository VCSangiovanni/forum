package co.contrader.forum.service;

import co.contrader.forum.dto.*;
import co.contrader.forum.exception.UserEmailAlreadyExistException;
import co.contrader.forum.exception.UserNameAlreadyExistException;
import co.contrader.forum.exception.UserNotFoundException;
import co.contrader.forum.mapper.ProfileMapper;
import co.contrader.forum.mapper.UserMapper;
import co.contrader.forum.model.Profile;
import co.contrader.forum.model.User;
import co.contrader.forum.repository.ProfileRepository;
import co.contrader.forum.repository.UserRepository;
import co.contrader.forum.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    UserMapper userMapper = UserMapper.INSTANCE;

    ProfileMapper profileMapper = ProfileMapper.INSTANCE;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;


    public UserDTO login(LoginDTO loginDTO) {
        UserDTO userDTO = userMapper.toDto(userRepository.findByUserNameAndPassword(loginDTO.getUsername(), loginDTO.getPassword()));


        return userDTO;
    }

    public UserDTO signUp(SignUpDTO signUpDTO) {
        if (userRepository.findByUserNameAndPassword(signUpDTO.getUserName(), signUpDTO.getPassword()) != null) {
            throw new UserNameAlreadyExistException(signUpDTO.getUserName());
        } else if(profileRepository.findByeMail(signUpDTO.getEMail()) != null){
           throw new UserEmailAlreadyExistException(signUpDTO.getEMail());
        }else{
            UserDTO newUser = new UserDTO();
            newUser.setUserName(signUpDTO.getUserName());
            newUser.setPassword(signUpDTO.getPassword());
            newUser.setUserCreation(System.currentTimeMillis());
            newUser.setActivationCode(UUID.randomUUID().toString());
            newUser.setActive(false);
            ProfileDTO newProfile = new ProfileDTO();
            newProfile.setFirstName(signUpDTO.getFirstName());
            newProfile.setLastName(signUpDTO.getLastName());
            newProfile.setEMail(signUpDTO.getEMail());
            newProfile.setUser(newUser);
            profileRepository.save(profileMapper.toEntity(newProfile));
            return newUser;
        }

    }


    public UserDTO activationUser(UserActivationDTO userActivationDTO) {
        UserDTO userToActive = userMapper
                .toDto(userRepository.findByActivationCode(userActivationDTO.getActivationCode()));
        if(userToActive != null){
            userToActive.setActive(true);
            userToActive.setActivationCode(null);
            userToActive.setUserRole(Role.USER);
            userRepository.save(userMapper.toEntity(userToActive));
        }else {
            throw new UserNotFoundException();
        }
        return userToActive;

    }
}
