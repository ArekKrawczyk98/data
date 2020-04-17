package com.example.data.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserEndpoint {

    UserService userService;

    @PostMapping
    public User createUser(User user){
     return userService.registerUser(user);
    }
    @PutMapping("/changeUsername")
    public User changeName(@RequestBody ChangeUsernameDTO changeUsernameDTO){
       return userService.changeName(
               changeUsernameDTO.getOldName(),
               changeUsernameDTO.getNewName());
    }
    @GetMapping("/{username}")
    public User user(@PathVariable String username){
        return userService.getUser(username);

    }

}
