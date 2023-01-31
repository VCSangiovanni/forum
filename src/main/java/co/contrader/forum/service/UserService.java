package co.contrader.forum.service;

import co.contrader.forum.dto.LoginDTO;
import co.contrader.forum.dto.ProfileDTO;
import co.contrader.forum.dto.SignUpDTO;
import co.contrader.forum.dto.UserDTO;
import co.contrader.forum.mapper.ProfileMapper;
import co.contrader.forum.mapper.UserMapper;
import co.contrader.forum.model.Profile;
import co.contrader.forum.model.User;
import co.contrader.forum.repository.ProfileRepository;
import co.contrader.forum.repository.UserRepository;
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
        System.err.println(newProfile);
        return newUser;

    }




}
