package com.donatus.activityTracker.repository;

import com.donatus.activityTracker.entity.Status;
import com.donatus.activityTracker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findTasksByUsersUserId(Integer userId);
    Task findTasksByUsersUserIdAndTaskId(Integer userId, Integer taskId);
    List<Task> findTasksByUsersUserIdAndStatus(Integer userId, Status status);
    void removeByUsersUserIdAndTaskId(Integer userId, Integer taskId);
    List<Task> findTasksByUsersUserIdOrderByDueDate(Integer userId);
    List<Task> findTasksByUsersUserIdOrderByCreatedDate(Integer userId);
}
