package com.donatus.activityTracker.repository;

import com.donatus.activityTracker.entity.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UsersRepositoryTest {

    private  Users users;

    @Autowired
    private UsersRepository repository;

    @BeforeEach
    void setUp() {
        users = Users.builder()
                .firstName("Jane")
                .lastName("Mary")
                .email("jane@gmail.com")
                .password("password")
                .userName("MarryAnn")
                .occupation("Chief")
                .address("Decagon")
                .tasks(new ArrayList<>()).build();
    }

    @Test
    void save_new_user() {
        Users savedUser = repository.save(users);

        Assertions.assertNotNull(savedUser);

        Assertions.assertTrue(savedUser.getUserId() > 0);
    }

    @Test
    public void find_user_email_and_username(){

        Users savedUser = repository.save(users);

        Assertions.assertNotNull(repository.findByUserNameAndPassword(savedUser.getUserName(), savedUser.getPassword()));
    }

    @Test
    public void delete_user(){

        Users savedUser = repository.save(users);

        Integer userId = savedUser.getUserId();

        repository.delete(savedUser);

        Assertions.assertEquals(Optional.empty(), repository.findById(userId));
    }

}