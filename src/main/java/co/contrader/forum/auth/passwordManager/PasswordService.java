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
            Profile userFromDB = profileRepository.findByeMail(requestPasswordDTO.getEMail())
                    .orElseThrow(UserNotFoundException::new);;
            userFromDB.getUser().setResetPasswordCode(UUID.randomUUID().toString());
            userFromDB.getUser().setPassword(null);
            userRepository.save(userFromDB.getUser());
            return "check your mail " + userFromDB.getUser().getResetPasswordCode();
            //eliminare "+ userFromDB.get().getResetPasswordCode()" in prod

    }

    public String setNewPassword(ResetPasswordDTO resetPasswordDTO) {
        User userFromDB = userRepository.findByResetPasswordCode(resetPasswordDTO.getResetPasswordCode())
                .orElseThrow(UserNotFoundException::new);
            userFromDB.setPassword(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));
            userFromDB.setResetPasswordCode(null);
            userRepository.save(userFromDB);
            return "Password change success " + userFromDB;
            //eliminare "+ userFromDB.get()" in prod
    }

}
