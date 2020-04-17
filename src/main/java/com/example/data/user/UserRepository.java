package com.example.data.user;

public interface UserRepository {
    User save(User user);
    User load(String username);
    User changeUserName(String oldUsername, String username);

    boolean exists(User user);
}
