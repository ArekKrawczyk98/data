package com.example.data;

import com.example.data.user.*;
import org.junit.Test;

import java.util.Collections;
import java.util.UUID;

import static org.junit.Assert.*;

public class UserTests {

    UserRepository userRepository = new UserRepositoryInMemory();
    UserEmailService userEmailService = UserEmailService.createService();
    private final UserService userService = new UserService(userRepository,userEmailService);
    @Test
    public void shouldVerifyUserSuccessfully(){
        final User user = new User(UUID.randomUUID().toString(),
                "Arkadiusz",
                "password123",
                "email",
                UserRole.ADMIN,
                Collections.emptyList());

        assertTrue(userService.verifyUser(user));

    }

    @Test
    public void shouldAddUser(){
        final User user = new User(UUID.randomUUID().toString(),
                "Arkadiusz",
                "password123",
                "email",
                UserRole.ADMIN,
                Collections.emptyList());
        assertNotNull(userService.addUserToDb(user));
    }
    @Test
    public void shouldRegisterUser(){
        final User user = new User(UUID.randomUUID().toString(),
                "Arkadiusz",
                "password123",
                "email",
                UserRole.ADMIN,
                Collections.emptyList());

        User userFromDb = userService.registerUser(user);
        assertEquals(user.getUsername(),userFromDb.getUsername());
    }

}
