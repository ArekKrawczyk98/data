package com.example.data.user;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserEmailService userEmailService;

    public User addUserToDb(User user) {
        return userRepository.save(user);
    }

    public boolean verifyUser(User user) {
        boolean userExists = userRepository.exists(user);
        String strRegEx = "^(?=.*[0-9]).{8,15}$";
        boolean passwordMatch = user.getPassword().matches(strRegEx);
        return !userExists && passwordMatch;
    }
    public User registerUser(User user){

        if (verifyUser(user)){
            user = User.of(
                    user.getId(),user.getUsername(),
                    user.getPassword(),user.getEmail(),
                    user.getRole(), user.getCountriesTracked());

            User userAdded =  this.addUserToDb(user);
            userEmailService.sendEmail(userAdded);

            return userAdded;
        }
        else throw new IllegalStateException("Cannot register user");
    }
    public User changeName(String oldName,String newName){
      return userRepository.changeUserName(oldName, newName);
    }

    public User getUser(String username) {
        return userRepository.load(username);
    }

    public List<User> getAllUsers() {
        return userRepository.loadAllUsers();
    }
    public User addCountriesTrackedToUser(User user, List<String> countriesTracked){
       return userRepository.addToCountriesTracked(user,countriesTracked);

    }
    public Long deleteUserByUsername(String username){
        return userRepository.deleteUserByUsername(username);
    }

}
