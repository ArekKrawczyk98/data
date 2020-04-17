package com.example.data.user;

import lombok.Value;
import org.springframework.data.domain.Example;

import java.util.Optional;

@Value
public class UserDAO implements UserRepository {

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
    public boolean exists(User user) {
       return userMongoData.exists(Example.of(user));
    }
}
