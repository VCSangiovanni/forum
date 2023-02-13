package co.contrader.forum.auth;


import co.contrader.forum.config.JwtService;
import co.contrader.forum.dto.ProfileDTO;
import co.contrader.forum.dto.UserDTO;
import co.contrader.forum.exception.*;
import co.contrader.forum.mapper.ProfileMapper;
import co.contrader.forum.mapper.UserMapper;
import co.contrader.forum.model.User;
import co.contrader.forum.repository.ProfileRepository;
import co.contrader.forum.repository.UserRepository;
import co.contrader.forum.utils.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    UserMapper userMapper = UserMapper.INSTANCE;
    ProfileMapper profileMapper = ProfileMapper.INSTANCE;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserDTO register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserNameAlreadyExistException(request.getUsername());
        } else if (profileRepository.findByeMail(request.getEMail()).isPresent()) {
            throw new UserEmailAlreadyExistException(request.getEMail());
        } else {
            var userToInsert = UserDTO.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .userCreation(System.currentTimeMillis())
                    .ActivationCode(UUID.randomUUID().toString())
                    .isActive(false)
                    .userRole(Role.GUEST)
                    .build();
            var profileToInsert = ProfileDTO.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .eMail(request.getEMail())
                    .user(userToInsert)
                    .build();
            profileRepository.save(profileMapper.toEntity(profileToInsert));

            return userToInsert;
        }
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword()));

            var user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow();
            if (user.isActive()){
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
            }else {
                throw new UserNotActiveException();
            }
        }catch (BadCredentialsException e){
            throw new WrongNameOrPass();
        }
    }


    public UserDTO activation(ActivationRequest request) {
        Optional<User> userToDb = userRepository.findByActivationCode(request.getActivationCode());
        if (userToDb.isPresent()) {
            userToDb.get().setActive(true);
            userToDb.get().setActivationCode(null);
            userToDb.get().setUserRole(Role.USER);
            userRepository.save(userToDb.get());
        } else {
            throw new UserNotFoundException();
        }
        return userMapper.toDto(userToDb.get());
    }

    public AuthenticationResponse refreshToken() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            final String username = authentication.getName();
            this.loadByUsername(username);
            String jwt = jwtService.generateToken((UserDetails) authentication.getPrincipal());
            AuthenticationResponse auth= new AuthenticationResponse();
            auth.setToken(jwt);
            return auth;
        }else {
            throw new Exception("Cannot refresh token: token not found");
        }
    }

    public UserDTO loadByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()){
            throw new UserNotFoundException();
        }else {
            return userMapper.toDto(user.get());
        }
    }

}
