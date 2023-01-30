package co.contrader.forum.service;

import co.contrader.forum.dto.LoginDTO;
import co.contrader.forum.dto.UserDTO;
import co.contrader.forum.mapper.UserMapper;
import co.contrader.forum.model.User;
import co.contrader.forum.repository.ProfileRepository;
import co.contrader.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserMapper userMapper = UserMapper.INSTANCE;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;


    public UserDTO login(LoginDTO loginDTO) {
        UserDTO userDTO = userMapper.toDto(userRepository.findByUserNameAndPassword(loginDTO.getUsername(), loginDTO.getPassword()));


        return userDTO;
    }
}
