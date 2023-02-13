package co.contrader.forum.auth.passwordManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping ("/requestPassword")
    public String requestNewPassword(@RequestBody RequestPasswordDTO requestPasswordDTO){
        return passwordService.requestNewPassword(requestPasswordDTO);
    }

    @PostMapping("/setNewPassword")
    public String setNewPassword(@RequestBody ResetPasswordDTO resetPasswordDTO){
        return passwordService.setNewPassword(resetPasswordDTO);
    }
}
