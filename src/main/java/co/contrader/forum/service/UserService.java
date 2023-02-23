package co.contrader.forum.service;

import co.contrader.forum.dto.*;
import co.contrader.forum.exception.*;
import co.contrader.forum.mapper.ProfileMapper;
import co.contrader.forum.mapper.UserMapper;
import co.contrader.forum.model.User;
import co.contrader.forum.repository.ProfileRepository;
import co.contrader.forum.repository.UserRepository;
import co.contrader.forum.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {

    UserMapper userMapper = UserMapper.INSTANCE;

    ProfileMapper profileMapper = ProfileMapper.INSTANCE;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProfileRepository profileRepository;

    public UserDTO updateUser(UpdateUserDTO updateUserDTO) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!loggedUser.getUsername().equals(updateUserDTO.getUsername()) ||
                userRepository.findByUsername(updateUserDTO.getUsername()).isEmpty()) {
            loggedUser.setUsername(updateUserDTO.getUsername());
            if (!updateUserDTO.getPassword().equals(loggedUser.getPassword()))
                loggedUser.setPassword(passwordEncoder.encode(updateUserDTO.getPassword()));

            userRepository.save(loggedUser);
            return userMapper.toDto(loggedUser);
        } else {
            throw new UserNameAlreadyExistException(updateUserDTO.getUsername());
        }
    }

    public List<UserDTO> getAll(){
        return userMapper.toDtoList(userRepository.findAll());
    }

    public UserDTO getByUsername(String username){
        return userMapper.toDto(userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new));
    }

    public void deleteByUsername(String username) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedUser.getUsername().equals(username)
                || Objects.equals(loggedUser.getUserRole(), Role.ADMIN)
                || Objects.equals(loggedUser.getUserRole(), Role.FOUNDER)){
            userRepository.deleteByUsername(username);
        }
    }


}
