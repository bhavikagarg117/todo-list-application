package com.example.todo_list_application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable Integer id) {
        return taskService.getTask(id);
    }

    @PostMapping("/tasks")
    public void addTask(@RequestBody Task task) {
        taskService.addTask(task);
    }

    @PutMapping("/tasks/{id}/done")
    public Task changeCompletion(@PathVariable Integer id) {
        return taskService.changeCompletion(id);
    }

    @DeleteMapping("/tasks/{id}")
    public List<Task> deleteTask(@PathVariable Integer id) {
        return taskService.deleteTask(id);
    }

}
