package co.contrader.forum.controller;

import co.contrader.forum.dto.UpdateUserDTO;
import co.contrader.forum.dto.UserDTO;
import co.contrader.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/updateUser")
    public UserDTO updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
        return userService.updateUser(updateUserDTO);
    }

    @GetMapping("/getAll")
    public List<UserDTO> getAll(){
        return userService.getAll();
    }

    @GetMapping("/getUser")
    public UserDTO getUser(@RequestParam String username) {
        return userService.getByUsername(username);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestParam String username){
        userService.deleteByUsername(username);
    }


}
