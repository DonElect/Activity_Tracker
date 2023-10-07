package com.donatus.activityTracker.dao;

import com.donatus.activityTracker.entity.Status;
import com.donatus.activityTracker.entity.Task;

import java.util.List;

public interface TaskDAO {
    // This will vie all task
    List<Task> findTaskByUserId(Integer userId);

    Task findTaskByUserIdAndTaskId(Integer userId, Integer taskId);

    // List task by Status(IN_PROGRESS, PENDING and DONE
    List<Task> findTaskByUserIdAndStatus(Integer userId, Status status);

    void deleteTaskByUserIdAndTaskId(Integer userId, Integer taskId);

    Task editTask(Integer userId, Integer taskId);
}
