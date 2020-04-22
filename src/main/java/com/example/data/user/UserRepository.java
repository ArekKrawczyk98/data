package com.example.data.user;

import java.util.List;

public interface UserRepository {
    User save(User user);
    User load(String username);
    User changeUserName(String oldUsername, String username);
    User addToCountriesTracked(User user, List<String> list);
    Long deleteUserByUsername(String username);

    boolean exists(User user);

    List<User> loadAllUsers();
}
