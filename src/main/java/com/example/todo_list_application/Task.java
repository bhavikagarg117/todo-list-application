package com.example.todo_list_application;


public class Task {
    private Integer id;
    private String taskName;
    private boolean isCompleted;

    public Task(int id, String taskName, boolean isCompleted) {
        this.id = id;
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    public Task() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
