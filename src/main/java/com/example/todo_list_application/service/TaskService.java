package com.example.todo_list_application.service;

import com.example.todo_list_application.repository.TaskRepository;
import com.example.todo_list_application.entity.Task;
import com.example.todo_list_application.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found with id: "+taskId));
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public ResponseEntity<String> changeStatus(Long taskId, boolean status) {
        Task existing= taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException ("Task not found with id: "+taskId));
        existing.setStatus(status);
        taskRepository.save(existing);
        return ResponseEntity.ok("Status changed successfully");
    }

    public Task updateTask(Task task, Long taskId){
        Task existing= taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException ("Task not found with id: "+taskId));
        existing.setTaskName(task.getTaskName());
        existing.setStatus(task.getStatus());
        existing.setLink(task.getLink());
        return taskRepository.save(existing);
    }

    public ResponseEntity<String>  deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }


}
