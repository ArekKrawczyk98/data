package com.example.data;

import com.example.data.user.User;
import com.example.data.user.UserMongoData;
import com.example.data.user.UserRole;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;



@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDBTests {
    @Autowired
    UserMongoData userMongoData;
    List<String> countriesForUser2 = List.of("Poland","Germany","United-States");
    final String UUIDForUser2  = UUID.randomUUID().toString();
    final String UUIDForUser1  = UUID.randomUUID().toString();

    @Before
    public void setup(){
        User user1 = new User(UUIDForUser1,"Arek","password", UserRole.ADMIN, Collections.emptyList());
        User user2 = new User(UUIDForUser2,"Piotr","pass2",UserRole.ADMIN,countriesForUser2);
        this.userMongoData.save(user1);
        this.userMongoData.save(user2);
    }

    @Test
    public void shouldGetAllUsers(){
        User user11 = this.userMongoData.findByUsername("Arek").orElseThrow();
        User user21 = this.userMongoData.findByUsername("Piotr").orElseThrow();
        assertEquals("password",user11.getPassword());
        assertEquals(countriesForUser2,user21.getCountriesTracked());
    }


    @Test
    public void shouldGetPiotrsList(){
        Optional<User> optionalUser = this.userMongoData.findByUsername("Piotr");
        List <String> countries = optionalUser.map(User::getCountriesTracked).orElse(Collections.emptyList());
        assertEquals(countries,countriesForUser2);
    }

    @Test
    public void shouldUpdateUser(){
        String name = "Hubert";
        Optional<User> optionalUser = this.userMongoData.findByUsername("Piotr");
        User userUpdated = optionalUser.map(user -> user.changeName(name))
                .orElseThrow();
        assertEquals(userUpdated.getUsername(),"Hubert");
        this.userMongoData.delete(optionalUser.get());
        this.userMongoData.save(userUpdated);
        List<String> listOfCountries  = this.userMongoData.findByUsername("Hubert")
                .map(User::getCountriesTracked)
                .orElseThrow();
        assertEquals(listOfCountries,countriesForUser2);

    }

    @Test
    public void shouldAddCountriesToUser(){
        final List<String> countriesForUser1 = List.of("Poland","Germany");
        final User user = userMongoData.findByUsername("Arek")
                .map(u -> u.addToCountriesTracked(countriesForUser1))
                .orElseThrow();

        assertEquals(user.getCountriesTracked(),countriesForUser1);


    }

    @After
    public void deleteAll(){

        this.userMongoData.deleteById(UUIDForUser1);
        this.userMongoData.deleteById(UUIDForUser2);
    }

}
