package com.donatus.activityTracker.dao.daoImpl;

import com.donatus.activityTracker.dao.TaskDAO;
import com.donatus.activityTracker.entity.Status;
import com.donatus.activityTracker.entity.Task;
import com.donatus.activityTracker.repository.TaskRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDAOImpl implements TaskDAO {
    private final EntityManager entityManager;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskDAOImpl(EntityManager entityManager, TaskRepository taskRepository){
        this.entityManager = entityManager;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findTaskByUserId(Integer userId) {
        return entityManager
                .createQuery("FROM Task WHERE users.userId = :input_id", Task.class)
                .setParameter("input_id", userId)
                .getResultList();
    }

    @Override
    public Task findTaskByUserIdAndTaskId(Integer userId, Integer taskId) {
        List<Task> tasks = entityManager
                .createQuery("FROM Task WHERE users.userId= :user_id AND taskId = :task_id", Task.class)
                .setParameter("user_id", userId)
                .setParameter("task_id", taskId)
                .getResultList();

        return tasks.get(0);
    }

    @Override
    public List<Task> findTaskByUserIdAndStatus(Integer userId, Status status) {
        return entityManager
                .createQuery("FROM Task WHERE users.userId= :user_id AND status = :input_status", Task.class)
                .setParameter("user_id", userId)
                .setParameter("input_status", status)
                .getResultList();
    }

    @Override
    public void deleteTaskByUserIdAndTaskId(Integer userId, Integer taskId) {
        Task task = findTaskByUserIdAndTaskId(userId, taskId);
        taskRepository.delete(task);
    }

    @Override
    public Task editTask(Integer userId, Integer taskId) {
        return findTaskByUserIdAndTaskId(userId, taskId);
    }

    @Override
    public List<Task> sortTaskByUserIdAndDueDate(Integer userId) {
        return entityManager.createQuery("FROM Task WHERE users.userId = : user_id ORDER BY dueDate", Task.class)
                            .setParameter("user_id", userId)
                            .getResultList();
    }

    @Override
    public List<Task> sortTaskByUserIdAndAddedDate(Integer userId) {
        return entityManager.createQuery("FROM Task WHERE users.userId = : user_id ORDER BY createdDate", Task.class)
                .setParameter("user_id", userId)
                .getResultList();
    }
}
