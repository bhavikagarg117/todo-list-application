package com.example.todo_list_application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="task")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "status")
    private boolean status;

    @Column(name = "link")
    private String link;

    public boolean getStatus() {
        return status;
    }
}