package com.donatus.activityTracker.servies;

import com.donatus.activityTracker.entity.Status;
import com.donatus.activityTracker.entity.Task;
import com.donatus.activityTracker.entity.Users;

import java.util.List;

public interface UserServices {
    List<Task> viewAllUserTask(Integer userId);
    Task viewSingleTask(Integer userId, Integer taskId);
    List<Task> viewTasksByStatus(Integer userId, Status status);
    void deleteTask(Integer userId, Integer taskId);
    Task saveTask(Task task);
    Users findUser(Integer userId);
    Users saveUser(Users user);
    void deleteUser(Integer userId);
}
