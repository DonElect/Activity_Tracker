package com.donatus.activityTracker.controller;

import com.donatus.activityTracker.entity.Status;
import com.donatus.activityTracker.entity.Task;
import com.donatus.activityTracker.entity.Users;
import com.donatus.activityTracker.servies.UserServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    private final UserServices services;

    @Autowired
    public UserController(UserServices services) {
        this.services = services;
    }

    @GetMapping("/user/home")
    public String viewAllTasks(Model model, HttpSession session){
        Integer userId = (int) session.getAttribute("userId");
        List<Task> allTask = services.viewAllUserTask(userId);

        String firstName = (String) session.getAttribute("firstName");

        model.addAttribute("firstName", firstName);

        model.addAttribute("tasks", allTask);
        model.addAttribute("newTask", new Task());

        model.addAttribute("standardDate", new Date());

        return "home";
    }

    @PostMapping("/user/add_task")
    public String addTask(HttpServletRequest req, Task task, HttpSession session){
        String activity_dueDate = req.getParameter("activity_dueDate")
                                     .replace("T", " ")
                                     .concat(":00.000000000");

        task.setDueDate(Timestamp.valueOf(activity_dueDate));
        task.setStatus(Status.IN_PROGRESS);

        Integer userId = (int) session.getAttribute("userId");

        Users currentUser = services.findUser(userId);

        currentUser.addTask(task);

        services.saveUser(currentUser);

        return "redirect:/user/home";
    }

    @GetMapping("/user/task/{taskId}")
    public String editTask(@PathVariable Integer taskId, HttpSession session, Model model){
        Integer userId = (Integer) session.getAttribute("userId");

        Task oldTask = services.viewSingleTask(userId, taskId);
        model.addAttribute("oldTask", oldTask);
        model.addAttribute("standardDate", new Date());

        return "task_edit";
    }

    @PostMapping("/user/task")
    public String saveEditTask(Task editedTask, HttpSession session, HttpServletRequest req){
        Integer userId = (Integer) session.getAttribute("userId");

        String activity_dueDate = req.getParameter("activity_dueDate")
                .replace("T", " ")
                .concat(":00.000000000");
        editedTask.setDueDate(Timestamp.valueOf(activity_dueDate));

        Users user = services.findUser(userId);
        editedTask.setUsers(user);

        services.saveTask(editedTask);


        return "redirect:/user/home";
    }

    @GetMapping("/user/task_delete/{taskId}")
    public String deleteTask(@PathVariable Integer taskId, HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");

        services.deleteTask(userId, taskId);

        return "redirect:/user/home";
    }

    @GetMapping("/user/task_status/{status}/{taskId}")
    public String updateStatus(@PathVariable String status, HttpSession session, @PathVariable Integer taskId){
        Integer userId = (Integer) session.getAttribute("userId");

        Users user = services.findUser(userId);
        Task task = services.viewSingleTask(userId, taskId);

        // Update status
        task.setStatus(Status.valueOf(status));

        if ("DONE".equals(status)){
            task.setCloseDate(Timestamp.valueOf(LocalDateTime.now()));
        }

        task.setUsers(user);

        services.saveTask(task);
        return "redirect:/user/home";
    }

    @GetMapping("/user/task_sort/{status}")
    public String sortByStatus(@PathVariable String status, HttpSession session, Model model){
        if ("ALL".equals(status))
            return "redirect:/user/home";

        Integer userId = (Integer) session.getAttribute("userId");

        List<Task> sortedTasks = services.viewTasksByStatus(userId, Status.valueOf(status));

        String firstName = (String) session.getAttribute("firstName");
        model.addAttribute("firstName", firstName);

        model.addAttribute("tasks", sortedTasks);
        model.addAttribute("newTask", new Task());

        model.addAttribute("standardDate", new Date());

        return "home";
    }

    @GetMapping("/user/task_time_sort/{time_sort}")
    public String timeSort(@PathVariable String time_sort, Model model, HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");

        List<Task> sortedByDate = services.sortDate(userId, time_sort);
        model.addAttribute("tasks", sortedByDate);

        String firstName = (String) session.getAttribute("firstName");
        model.addAttribute("firstName", firstName);

        model.addAttribute("newTask", new Task());

        model.addAttribute("standardDate", new Date());

        return "home";
    }
}
