package co.contrader.forum.service;

import co.contrader.forum.dto.*;
import co.contrader.forum.exception.UserEmailAlreadyExistException;
import co.contrader.forum.exception.UserNameAlreadyExistException;
import co.contrader.forum.exception.UserNotActiveException;
import co.contrader.forum.exception.UserNotFoundException;
import co.contrader.forum.mapper.ProfileMapper;
import co.contrader.forum.mapper.UserMapper;
import co.contrader.forum.repository.ProfileRepository;
import co.contrader.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    UserMapper userMapper = UserMapper.INSTANCE;

    ProfileMapper profileMapper = ProfileMapper.INSTANCE;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

}
