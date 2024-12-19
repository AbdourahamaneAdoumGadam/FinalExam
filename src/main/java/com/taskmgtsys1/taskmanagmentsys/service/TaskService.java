package com.taskmgtsys1.taskmanagmentsys.service;

import com.taskmgtsys1.taskmanagmentsys.model.Task;
import com.taskmgtsys1.taskmanagmentsys.repisotory.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;



import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Create a new task
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get task by ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // Update task
    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setStatus(updatedTask.getStatus());
            task.setPriority(updatedTask.getPriority());
            task.setDueDate(updatedTask.getDueDate());
            task.setAssignedUser(updatedTask.getAssignedUser());
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    // Delete task by ID
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    public Page<Task> getTasksWithPagination(int page, int size) {
        return taskRepository.findAll(PageRequest.of(page, size));
    }
}
