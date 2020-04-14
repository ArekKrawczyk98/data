package com.example.data.user;

public interface UserRepository {
    User save(User user);
    User load(Integer id);
}
