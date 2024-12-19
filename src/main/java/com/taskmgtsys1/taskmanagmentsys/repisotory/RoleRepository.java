// Repository
package com.taskmgtsys1.taskmanagmentsys.repisotory;

import com.taskmgtsys1.taskmanagmentsys.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}