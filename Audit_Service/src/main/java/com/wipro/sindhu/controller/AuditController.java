package com.wipro.sindhu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.wipro.sindhu.entities.AuditLog;
import com.wipro.sindhu.services.AuditService;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    // Only ADMIN can view all logs
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<AuditLog>> getAllLogs() {
        return ResponseEntity.ok(auditService.getAllLogs());
    }

    // ADMIN or AUDITOR can fetch logs by entity type
    @PreAuthorize("hasAnyAuthority('ADMIN', 'AUDITOR')")
    @GetMapping("/entity")
    public ResponseEntity<List<AuditLog>> getLogsByEntityType(@RequestParam String entityType) {
        return ResponseEntity.ok(auditService.getLogsByEntityType(entityType));
    }

    // ADMIN or AUDITOR can fetch logs by event type
    @PreAuthorize("hasAnyAuthority('ADMIN', 'AUDITOR')")
    @GetMapping("/event")
    public ResponseEntity<List<AuditLog>> getLogsByEventType(@RequestParam String eventType) {
        return ResponseEntity.ok(auditService.getLogsByEventType(eventType));
    }
}

