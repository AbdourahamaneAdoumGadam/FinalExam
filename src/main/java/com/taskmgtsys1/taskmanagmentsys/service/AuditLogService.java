// Service
package com.taskmgtsys1.taskmanagmentsys.service;

import com.taskmgtsys1.taskmanagmentsys.model.AuditLog;
import com.taskmgtsys1.taskmanagmentsys.repisotory.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    public List<AuditLog> getAllAuditLogs() {
        return auditLogRepository.findAll();
    }

    public Optional<AuditLog> getAuditLogById(Long id) {
        return auditLogRepository.findById(id);
    }

    public AuditLog createAuditLog(AuditLog auditLog) {
        return auditLogRepository.save(auditLog);
    }

    public AuditLog updateAuditLog(Long id, AuditLog updatedAuditLog) {
        return auditLogRepository.findById(id).map(auditLog -> {
            auditLog.setAction(updatedAuditLog.getAction());
            auditLog.setTimestamp(updatedAuditLog.getTimestamp());
            auditLog.setUser(updatedAuditLog.getUser());
            return auditLogRepository.save(auditLog);
        }).orElseThrow(() -> new RuntimeException("AuditLog not found with id: " + id));
    }

    public void deleteAuditLog(Long id) {
        auditLogRepository.deleteById(id);
    }
}
