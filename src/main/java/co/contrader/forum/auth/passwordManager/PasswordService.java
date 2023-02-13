package co.contrader.forum.auth.passwordManager;

import co.contrader.forum.exception.UserNotFoundException;
import co.contrader.forum.mapper.ProfileMapper;
import co.contrader.forum.mapper.UserMapper;
import co.contrader.forum.model.Profile;
import co.contrader.forum.model.User;
import co.contrader.forum.repository.ProfileRepository;
import co.contrader.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String requestNewPassword(RequestPasswordDTO requestPasswordDTO) {
        Optional<Profile> userprofile = profileRepository.findByeMail(requestPasswordDTO.getEMail());
        if (userprofile.isPresent()) {
            Optional<User> userFromDB = userRepository.findUserByeMail(requestPasswordDTO.getEMail());
            userFromDB.get().setResetPasswordCode(UUID.randomUUID().toString());
            userFromDB.get().setPassword(null);
            userRepository.save(userFromDB.get());
            return "check your mail " + userFromDB.get().getResetPasswordCode();
            //eliminare "+ userFromDB.get().getResetPasswordCode()" in prod
        } else {
            throw new UserNotFoundException();
        }
    }

    public String setNewPassword(ResetPasswordDTO resetPasswordDTO) {
        Optional<User> userFromDB = userRepository.findByResetPasswordCode(resetPasswordDTO.getResetPasswordCode());
        if (userFromDB.isPresent()) {
            userFromDB.get().setPassword(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));
            userFromDB.get().setResetPasswordCode(null);
            userRepository.save(userFromDB.get());
            return "Password change success " + userFromDB.get();
            //eliminare "+ userFromDB.get()" in prod
        } else {
            throw new UserNotFoundException();
        }
    }


}
