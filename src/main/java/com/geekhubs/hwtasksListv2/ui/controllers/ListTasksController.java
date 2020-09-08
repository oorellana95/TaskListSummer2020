package com.geekhubs.hwtasksListv2.ui.controllers;

import com.geekhubs.hwtasksListv2.application.domain.models.StatusModel;
import com.geekhubs.hwtasksListv2.application.domain.models.TaskModel;
import com.geekhubs.hwtasksListv2.application.domain.models.UserModel;
import com.geekhubs.hwtasksListv2.application.domain.services.StatusService;
import com.geekhubs.hwtasksListv2.application.domain.services.TaskService;
import com.geekhubs.hwtasksListv2.application.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Controller
public class ListTasksController {
    private static final String VIEWS_TASK_CREATE_OR_UPDATE_FORM = "createOrUpdateTaskForm";
    private final TaskService taskService;
    private final StatusService statusService;
    private final UserService userService;

    @Autowired
    public ListTasksController(TaskService taskService, StatusService statusService, UserService userService) {
        this.taskService = taskService;
        this.statusService = statusService;
        this.userService = userService;
    }

    @GetMapping("/tasks")
    public String getTasks(Model model, String keyword) {
        model.addAttribute("title","Tareas");
        model.addAttribute("action", "allTasks");

        if (keyword != null){
            model.addAttribute("tasks", this.taskService.findByKeyword(keyword));
        }
        else{
            model.addAttribute("tasks", this.taskService.findAll());
        }
        return "listTasks";
    }
    @GetMapping("/tasks/user/{id}")
    public String getTasksByUser(@PathVariable("id") int id, Model model, String keyword) {
        UserModel user = this.userService.findById(id);
        model.addAttribute("title","Tareas de "+ user.getName() + " " + user.getLastname());
        model.addAttribute("action", "userTasks");
        model.addAttribute("idUser", id);

        if (keyword != null){
            model.addAttribute("tasks", this.taskService.findByUserIdAndKeyword(user.getId(),keyword));
        }
        else{
            model.addAttribute("tasks", this.taskService.findByUserId(user.getId()));
        }
        return "listTasks";
    }


    @GetMapping("/tasks/add")
    public String initUpdateTaskForm(Model model) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy

        TaskModel task = new TaskModel();
        task.setCreatedAt(sdfDate.format(new Date()));
        task.setStatus(new StatusModel());
        task.getStatus().setId(Long.valueOf(4));

        model.addAttribute("task",task);

        List<StatusModel> listStatus = this.statusService.findAll();
        model.addAttribute("status",listStatus);

        List<UserModel> listUsers = this.userService.findAll();
        model.addAttribute("users",listUsers);

        model.addAttribute("title","Añadir tarea");
        return VIEWS_TASK_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/tasks/add")
    public String processAddTaskForm(@Valid TaskModel task, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_TASK_CREATE_OR_UPDATE_FORM;
        }
        else {
            task.setEnabled(true);
            this.taskService.addTask(task);
            return "redirect:/tasks";
        }
    }

    @GetMapping("/tasks/edit/{id}")
    public String initUpdateTaskForm(@PathVariable("id") int id, Model model) {
        TaskModel task = this.taskService.findById(id);
        model.addAttribute("task",task);

        List<StatusModel> listStatus = this.statusService.findAll();
        model.addAttribute("status",listStatus);

        List<UserModel> listUsers = this.userService.findAll();
        model.addAttribute("users",listUsers);

        model.addAttribute("title","Actualizar tarea");
        return VIEWS_TASK_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/tasks/edit/{id}")
    public String processUpdateTaskForm(@Valid TaskModel task, BindingResult result,
                                         @PathVariable("id") long id) {
        if (result.hasErrors()) {
            return VIEWS_TASK_CREATE_OR_UPDATE_FORM;
        }
        else {
            task.setId(id);
            task.setEnabled(true);
            this.taskService.changeTaskData(task);
            return "redirect:/tasks";
        }
    }

    @GetMapping("/deleteTask")
    public String deleteStudent(@RequestParam(name = "id") long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}