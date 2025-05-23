package com.example.todo_list_application;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    List<Task> tasks= new ArrayList<>(Arrays.asList(new Task(1,"Watch springboot lectures", false),new Task(2,"Make a simple to-do list springboot project for practice", false),new Task(3,"Watch microservices lectures", false)));

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTask(Integer id) {
        return tasks.stream().filter(t->t.getId().equals(id)).findFirst().get();

    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task changeCompletion(Integer id) {
        Optional<Task> optionalTask= tasks.stream().filter(t->t.getId().equals(id)).findFirst();
        Task task=optionalTask.get();
        task.setCompleted(true);
        return task;
    }

    public List<Task> deleteTask(Integer id) {
        Optional<Task> optionalTask= tasks.stream().filter(t->t.getId().equals(id)).findFirst();
        Task task=optionalTask.get();
        tasks.remove(task);
        return tasks;
    }


}
