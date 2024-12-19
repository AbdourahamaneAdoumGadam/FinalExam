package com.taskmgtsys1.taskmanagmentsys.repisotory;

import com.taskmgtsys1.taskmanagmentsys.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // You can add custom queries here if needed
    
}


