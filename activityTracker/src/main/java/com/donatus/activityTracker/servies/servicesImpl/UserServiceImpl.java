package com.donatus.activityTracker.servies.servicesImpl;

import com.donatus.activityTracker.dao.TaskDAO;
import com.donatus.activityTracker.entity.Status;
import com.donatus.activityTracker.entity.Task;
import com.donatus.activityTracker.entity.Users;
import com.donatus.activityTracker.repository.TaskRepository;
import com.donatus.activityTracker.repository.UsersRepository;
import com.donatus.activityTracker.servies.UserServices;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServices {
    private final UsersRepository usersRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, TaskRepository taskRepository) {
        this.usersRepository = usersRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> viewAllUserTask(Integer userId) {
        return taskRepository.findTasksByUsersUserId(userId);
    }

    @Override
    public Task viewSingleTask(Integer userId, Integer taskId) {
        return taskRepository.findTasksByUsersUserIdAndTaskId(userId, taskId);
    }

    @Override
    public List<Task> viewTasksByStatus(Integer userId, Status status) {
        return taskRepository.findTasksByUsersUserIdAndStatus(userId, status);
    }

    @Transactional
    @Override
    public void deleteTask(Integer userId, Integer taskId) {
        taskRepository.removeByUsersUserIdAndTaskId(userId, taskId);
    }

    @Transactional
    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Users findUser(Integer userId) {
        Optional<Users> result = usersRepository.findById(userId);

        if (result.isEmpty())
            throw new NullPointerException("User not found!");

        return result.get();
    }

    @Transactional
    @Override
    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(Integer userId) {
        Users users = findUser(userId);
        usersRepository.delete(users);
    }

    @Override
    public List<Task> sortDate(Integer userId, String sortType) {
        return "ADDED_DATE".equals(sortType) ? taskRepository.findTasksByUsersUserIdOrderByCreatedDate(userId) :
                "DUE_DATE".equals(sortType) ? taskRepository.findTasksByUsersUserIdOrderByDueDate(userId) : new ArrayList<>();
    }

}
