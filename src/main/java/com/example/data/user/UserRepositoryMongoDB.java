package com.example.data.user;

import lombok.Value;

import java.util.List;
import java.util.Optional;

@Value
public class UserRepositoryMongoDB implements UserRepository {

    UserMongoData userMongoData;
    @Override
    public User save(User user) {
      return userMongoData.save(user);
    }

    @Override
    public User load(String username) {

        return userMongoData.findByUsername(username).orElseThrow();
    }

    @Override
    public User changeUserName(String oldUsername,String username) {
        Optional<User> opt = userMongoData.findByUsername(oldUsername);
        User userUpdated = opt.map(user -> user.changeName(username)).orElseThrow();
        userMongoData.delete(opt.get());
        return userMongoData.save(userUpdated);
    }

    @Override
    public User addToCountriesTracked(User user, List<String> list) {
        User userWithAddedCountries =  user.addToCountriesTracked(list);
        return userMongoData.save(userWithAddedCountries);
    }

    @Override
    public Long deleteUserByUsername(String username) {
      return this.userMongoData.deleteByUsername(username).orElseThrow();
    }

    @Override
    public boolean exists(User user) {
       return userMongoData.existsByUsername(user.getUsername());
    }

    @Override
    public List<User> loadAllUsers() {
        return userMongoData.findAll();
    }
}
