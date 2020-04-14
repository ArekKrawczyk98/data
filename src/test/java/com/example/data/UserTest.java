package com.example.data;

import com.example.data.user.User;
import com.example.data.user.UserMongoData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    UserMongoData userMongoData;
    List<String> countriesForUser2 = List.of("Poland","Germany","United-States");

    @Before
    public void setup(){
        User user1 = new User(UUID.randomUUID().toString(),"Arek","password",null);
        User user2 = new User(UUID.randomUUID().toString(),"Piotr","pass2",countriesForUser2);
        this.userMongoData.save(user1);
        this.userMongoData.save(user2);
    }

    @Test
    public void shouldGetAllUsers(){
        int counter = this.userMongoData.findAll().size();
        assertEquals(counter,2);
    }

    @Test
    public void shouldGetPiotrsList(){
        List <String> countries = this.userMongoData.findByUsername("Piotr").getCountriesTracked();
        assertEquals(countries,countriesForUser2);
    }

    @After
    public void deleteAll(){
        this.userMongoData.deleteAll();
    }

}
