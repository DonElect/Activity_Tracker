package com.donatus.activityTracker.servies.servicesImpl;

import com.donatus.activityTracker.entity.Users;
import com.donatus.activityTracker.repository.UsersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRegistrationAndLoginServicesImplTest {

    private Users user1, user2;

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UserRegistrationAndLoginServicesImpl services;

    @BeforeEach
    void setUp(){

        user1 = Users.builder()
                .firstName("Jane")
                .lastName("Mary")
                .email("jane@gmail.com")
                .password("password")
                .userName("MarryAnn")
                .occupation("Chief")
                .address("Decagon")
                .tasks(new ArrayList<>()).build();

        user2 = Users.builder()
                .firstName("Mike")
                .lastName("John")
                .email("johnMary@gmail.com")
                .password("password")
                .userName("JohnMary")
                .occupation("Software Engineer")
                .address("Lagos")
                .tasks(new ArrayList<>()).build();


    }

    @Test
    void registerUser() {
        when(usersRepository.save(Mockito.any(Users.class))).thenReturn(user1);

        Users savedUser1 = services.registerUser(user1);

        Assertions.assertThat(savedUser1).isNotNull();
    }

    @Test
    void verifyUser() {
        when(usersRepository.findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(user2);

        Users savedUser2 = services.verifyUser(user2.getUserName(), user2.getPassword());

        Assertions.assertThat(savedUser2).isNotNull();
    }
}