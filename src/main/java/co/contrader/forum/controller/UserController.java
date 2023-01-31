package co.contrader.forum.controller;

import co.contrader.forum.dto.LoginDTO;
import co.contrader.forum.dto.SignUpDTO;
import co.contrader.forum.dto.UserDTO;
import co.contrader.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @RequestMapping("/login")
    public UserDTO login(@RequestBody LoginDTO loginDTO){
        return userService.login(loginDTO);
    }

    @PostMapping
    @RequestMapping("/signup")
    public UserDTO signUp(@RequestBody SignUpDTO signUpDTO){
        return userService.signUp(signUpDTO);
    }

}
