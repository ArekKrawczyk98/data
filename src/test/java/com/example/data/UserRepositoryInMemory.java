package com.example.data;

import com.example.data.user.User;
import com.example.data.user.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserRepositoryInMemory implements UserRepository {


    HashMap<String,User> database = new HashMap<>();

    @Override
    public User save(User user) {
        User user1 =  database.put(user.getId(),user);
        if (user1 == null){
            return user;
        }
        else return null;
    }

    @Override
    public User load(String username) {
        return database.get(username);
    }

    @Override
    public User changeUserName(String oldUsername, String username) {
        return  database.values().stream()
                .filter(s -> s.getUsername().equals(oldUsername))
                .findFirst()
                .orElseThrow();

    }

    @Override
    public User addToCountriesTracked(User user, List<String> list) {

       return database.put(user.getId(),user.addToCountriesTracked(list));
    }

    @Override
    public Long deleteUserByUsername(String username) {
        return null;
    }

    @Override
    public boolean exists(User user) {
        return database.containsValue(user);
    }

    @Override
    public List<User> loadAllUsers() {
        return new ArrayList<>(database.values());
    }
}
