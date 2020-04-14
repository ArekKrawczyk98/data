package com.example.data.user;

import lombok.Value;

@Value
public class UserDTO implements UserRepository {

    UserMongoData userMongoData;
    @Override
    public User save(User user) {
      return  userMongoData.save(user);
    }

    @Override
    public User load(Integer id) {
        return userMongoData.findById(id).orElse(null);
    }
}
