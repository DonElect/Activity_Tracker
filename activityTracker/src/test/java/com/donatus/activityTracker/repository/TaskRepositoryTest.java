package com.donatus.activityTracker.repository;

import com.donatus.activityTracker.entity.Status;
import com.donatus.activityTracker.entity.Task;
import com.donatus.activityTracker.entity.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class TaskRepositoryTest {

    private Users user1, user2;

    private Users savedUser1, savedUser2;
    private Task task1, task2, task3;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    private UsersRepository usersRepository;

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
    public void save_task(){
       Users savedUser = usersRepository.save(user1);

       task1.setUsers(savedUser);

       Task savedTask = taskRepository.save(task1);

        assertNotNull(savedTask);

        assertTrue(savedTask.getTaskId() > 0);

    }

    @Test
    public void delete_task(){
        Users savedUser = usersRepository.save(user1);

        task1.setUsers(savedUser);

        Task savedTask = taskRepository.save(task1);

        Integer taskId = savedTask.getTaskId();
        System.out.println(savedTask);

        taskRepository.delete(savedTask);

        Assertions.assertEquals(Optional.empty(), taskRepository.findById(taskId));
    }

    @Test
    void findTasksByUsersUserId() {

        // Save the user
        savedUser1 = usersRepository.save(user1);

        // Add tasks to the user
        savedUser1.addTask(task1);
        savedUser1.addTask(task2);

        // Update the database with the user and tasks
        savedUser1 = usersRepository.save(savedUser1);

        // Get user id
        Integer userId = savedUser1.getUserId();

        Assertions.assertTrue(taskRepository.findTasksByUsersUserId(userId).size() > 1);
    }

    @Test
    void findTasksByUsersUserIdAndTaskId() {

        // Save the user
        savedUser1 = usersRepository.save(user1);

        // Add tasks to the user
        savedUser1.addTask(task1);
        savedUser1.addTask(task2);

        // Update the database with the user and tasks
        savedUser1 = usersRepository.save(savedUser1);

        // Get user id
        Integer userId = savedUser1.getUserId();

        // Get a task Id
        List<Task> userTasks = taskRepository.findTasksByUsersUserId(userId);
        Integer taskId = userTasks.get(0).getTaskId();

        Assertions.assertNotNull(taskRepository.findTasksByUsersUserIdAndTaskId(userId, taskId));
        Assertions.assertTrue(taskRepository.findTasksByUsersUserIdAndTaskId(userId, taskId).getTaskId() > 0);
    }

    @Test
    void findTasksByUsersUserIdAndStatus() {

        // Save the user
        savedUser1 = usersRepository.save(user1);

        // Add tasks to the user
        savedUser1.addTask(task1);
        savedUser1.addTask(task2);

        // Update the database with the user and tasks
        savedUser1 = usersRepository.save(savedUser1);

        // Get user id
        Integer userId = savedUser1.getUserId();

        Assertions.assertNotNull(taskRepository.findTasksByUsersUserIdAndStatus(userId, Status.IN_PROGRESS));
        assertFalse(taskRepository.findTasksByUsersUserIdAndStatus(userId, Status.IN_PROGRESS).isEmpty());
    }

    @Test
    void removeByUsersUserIdAndTaskId() {
        // Save the user
        savedUser1 = usersRepository.save(user1);

        // Add tasks to the user
        savedUser1.addTask(task1);
        savedUser1.addTask(task2);

        // Update the database with the user and tasks
        savedUser1 = usersRepository.save(savedUser1);

        // Get user id
        Integer userId = savedUser1.getUserId();

        // Get a task Id
        Integer taskId = taskRepository.findTasksByUsersUserId(userId).get(0).getTaskId();

        usersRepository.delete(savedUser1);

        taskRepository.removeByUsersUserIdAndTaskId(userId, taskId);

        Assertions.assertNull(taskRepository.findTasksByUsersUserIdAndTaskId(userId, taskId));
    }

    @Test
    void findTasksByUsersUserIdOrderByDueDate() {
        // Save the user
        savedUser1 = usersRepository.save(user1);

        // Add tasks to the user
        savedUser1.addTask(task1);
        savedUser1.addTask(task2);

        // Update the database with the user and tasks
        savedUser1 = usersRepository.save(savedUser1);

        // Get user id
        Integer userId = savedUser1.getUserId();

        Assertions.assertNotNull(taskRepository.findTasksByUsersUserIdOrderByDueDate(userId));
        assertFalse(taskRepository.findTasksByUsersUserIdOrderByDueDate(userId).isEmpty());
    }

    @Test
    void findTasksByUsersUserIdOrderByCreatedDate() {
        // Save the user
        savedUser1 = usersRepository.save(user1);

        // Add tasks to the user
        savedUser1.addTask(task1);
        savedUser1.addTask(task2);

        // Update the database with the user and tasks
        savedUser1 = usersRepository.save(savedUser1);

        // Get user id
        Integer userId = savedUser1.getUserId();

        Assertions.assertNotNull(taskRepository.findTasksByUsersUserIdOrderByCreatedDate(userId));
        assertFalse(taskRepository.findTasksByUsersUserIdOrderByCreatedDate(userId).isEmpty());
    }
}