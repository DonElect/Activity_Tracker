package com.donatus.activityTracker.servies.servicesImpl;

import com.donatus.activityTracker.entity.Status;
import com.donatus.activityTracker.entity.Task;
import com.donatus.activityTracker.entity.Users;
import com.donatus.activityTracker.repository.TaskRepository;
import com.donatus.activityTracker.repository.UsersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UsersRepository usersRepository;

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
    void viewAllUserTask() {
        List<Task> tasks = Mockito.mock(List.class);

        when(taskRepository.findTasksByUsersUserId(Mockito.anyInt())).thenReturn(tasks);

        List<Task> userTasks = userService.viewAllUserTask(1);

        Assertions.assertThat(userTasks).isNotNull();
    }

    @Test
    void viewSingleTask() {
        Task task = Mockito.mock(Task.class);
        when(taskRepository.findTasksByUsersUserIdAndTaskId(Mockito.anyInt(), Mockito.anyInt())).thenReturn(task);

        Task resultTask = userService.viewSingleTask(3, 1);

        Assertions.assertThat(resultTask).isNotNull();
    }

    @Test
    void viewTasksByStatus() {
        List<Task> tasks = Mockito.mock(List.class);

        when(taskRepository.findTasksByUsersUserIdAndStatus(Mockito.anyInt(), Mockito.any())).thenReturn(tasks);

        List<Task> userTasks = userService.viewTasksByStatus(2, Status.IN_PROGRESS);

        Assertions.assertThat(userTasks).isNotNull();

    }

    @Test
    void deleteTask() {

        when(taskRepository.findTasksByUsersUserIdAndTaskId(1, 3)).thenReturn(task1);

        assertAll(() -> userService.deleteTask(1, 3));
    }

    @Test
    void saveTask() {
        //Task task = Mockito.mock(Task.class);
        task1.setTaskId(1);
        when(taskRepository.save(Mockito.any(Task.class))).thenReturn(task1);

        Task resultTask = userService.saveTask(task1);

        Assertions.assertThat(resultTask.getTaskId()).isGreaterThan(0);

    }

    @Test
    void findUser() {
        when(usersRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(user1));

        Users res = userService.findUser(1);

        Assertions.assertThat(res).isNotNull();
    }

    @Test
    void saveUser() {
        Users newUser = Mockito.mock(Users.class);
        user2.setUserId(3);
        when(usersRepository.save(Mockito.any(Users.class))).thenReturn(user2);

        Users savedUser = userService.saveUser(newUser);

        Assertions.assertThat(savedUser.getUserId()).isGreaterThan(0);
    }

    @Test
    void deleteUser() {
        when(usersRepository.findById(1)).thenReturn(Optional.ofNullable(user1));

        assertAll(() -> userService.deleteUser(1));
    }

    @Test
    void sortDate() {
        List<Task> tasks = Mockito.mock(List.class);

        when(taskRepository.findTasksByUsersUserIdOrderByDueDate(Mockito.anyInt())).thenReturn(tasks);
        when(taskRepository.findTasksByUsersUserIdOrderByCreatedDate(Mockito.anyInt())).thenReturn(tasks);

        List<Task> addedDate = userService.sortDate(2, "ADDED_DATE");
        List<Task> dueDate = userService.sortDate(2, "DUE_DATE");

        Assertions.assertThat(addedDate).isNotNull();
        Assertions.assertThat(dueDate).isNotNull();
    }
}