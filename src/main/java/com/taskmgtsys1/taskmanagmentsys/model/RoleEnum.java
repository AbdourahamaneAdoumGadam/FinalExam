package com.taskmgtsys1.taskmanagmentsys.model;

public enum RoleEnum {
    ADMIN, // Full access to manage users and tasks
    MANAGER, // Access to manage tasks and monitor users
    USER; // Basic access to assigned tasks
}
