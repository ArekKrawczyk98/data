package com.example.data.user;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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
            return this.addUserToDb(user);
        }
        else throw new IllegalStateException("Cannot register user");
    }
    public User changeName(String oldName,String newName){
      return userRepository.changeUserName(oldName, newName);
    }

    public User getUser(String username) {
        return userRepository.load(username);
    }
}
