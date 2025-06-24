package com.example.todo_list_application;

import com.example.todo_list_application.entity.Task;
import com.example.todo_list_application.entity.UserInfo;
import com.example.todo_list_application.jwtauth.AuthRequest;
import com.example.todo_list_application.jwtauth.JwtService;
import com.example.todo_list_application.service.TaskService;
import com.example.todo_list_application.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todolist")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @PostMapping("/adduser")
    public String addUser(@RequestBody UserInfo userInfo) {
        return userInfoService.addUser(userInfo);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/task/{id}")
    public Task getTaskById(@PathVariable (value="id") Long taskId) {
        return taskService.getTaskById(taskId);
    }

    @PostMapping("/task")
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @PutMapping("/task/status/{id}")
    public ResponseEntity<String> changeStatus(@PathVariable(value="id") Long taskId, @RequestBody boolean status) {
        return taskService.changeStatus(taskId,status);
    }

    @PutMapping("task/update/{id}")
    public Task updateTask(@RequestBody Task task, @PathVariable(value="id") Long taskId) {
        return taskService.updateTask(task,taskId);
    }

    @DeleteMapping("/task/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable (value="id")Long taskId) {
        return taskService.deleteTask(taskId);
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequest authRequest) {
        Authentication authentication= authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUsername());
        }
        else
            throw new UsernameNotFoundException("username is not in db");

    }

}
