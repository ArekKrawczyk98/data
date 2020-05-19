package com.example.data.user;

import com.example.data.user.dto.ChangeUsernameDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserEndpoint {

    UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.registerUser(user);
    }
    @PutMapping("/changeUsername")
    public User changeUsername(@RequestBody ChangeUsernameDTO changeUsernameDTO){
       return userService.changeName(
               changeUsernameDTO.getOldName(),
               changeUsernameDTO.getNewName());
    }
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
        return userService.getUser(username);

    }
    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/{username}/addCountries")
    public User addCountriesToUser(@PathVariable("username") String username,@RequestBody List<String> countries){
        User user = userService.getUser(username);
        return userService.addCountriesTrackedToUser(user,countries);

    }
    @DeleteMapping("/{username}")
    public String deleteUser(@PathVariable("username") String username){
        return userService.deleteUserByUsername(username)+" --- deleted documents";
    }


}
