package com.donatus.activityTracker.controller;

import com.donatus.activityTracker.entity.Status;
import com.donatus.activityTracker.entity.Task;
import com.donatus.activityTracker.entity.Users;
import com.donatus.activityTracker.servies.UserRegistrationAndLoginServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class UserLoginAndRegistrationControllerTest {

    @Autowired
    private MockMvc  mockMvc;

    @MockBean
    private UserRegistrationAndLoginServices loginServices;

    @Autowired
    private ObjectMapper objectMapper;

    private Users user1, user2;

    private Users savedUser1, savedUser2;
    private Task task1, task2, task3;

    @BeforeEach
    void setUp() {

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


        task1 = Task.builder()
                .activity("Test Your Application")
                .activityDetail("I will be testing my application using Mockito and JUnit5")
                .dueDate(Timestamp.valueOf("2024-04-26 03:00:00.000000000"))
                .status(Status.IN_PROGRESS)
                .build();

        task2 = Task.builder()
                .activity("Watch a movie")
                .activityDetail("Watch the equalizer again")
                .dueDate(Timestamp.valueOf(LocalDateTime.now()))
                .status(Status.IN_PROGRESS)
                .build();

        task3 = Task.builder()
                .activity("Study up on Spring Security")
                .activityDetail("What is Spring security, how to create one, etc")
                .dueDate(Timestamp.valueOf(LocalDateTime.now()))
                .status(Status.IN_PROGRESS)
                .build();


    }


    @Test
    void registration() {
    }

    @Test
    void saveUser() {
    }

    @Test
    void login() {
    }

    @Test
    void loginVerification() {
    }
}