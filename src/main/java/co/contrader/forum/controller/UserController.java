package co.contrader.forum.controller;

import co.contrader.forum.dto.LoginDTO;
import co.contrader.forum.dto.SignUpDTO;
import co.contrader.forum.dto.UserActivationDTO;
import co.contrader.forum.dto.UserDTO;
import co.contrader.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserDTO login(@RequestBody LoginDTO loginDTO){
        return userService.login(loginDTO);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO){
        userService.signUp(signUpDTO);
        return new ResponseEntity<>(HttpStatus.CREATED) ;
    }

    @PostMapping("/activation")
    public ResponseEntity<UserDTO> activationUser(@RequestBody UserActivationDTO userActivationDTO){
        userService.activationUser(userActivationDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
