package com.rapifuzz.projectApp.controllers;

import java.util.Date;
import java.util.List;

import com.rapifuzz.projectApp.service.IncidentService;
import com.rapifuzz.projectApp.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.rapifuzz.projectApp.models.Incident;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    @GetMapping
    public ResponseEntity<List<Incident>> getIncidentsByUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<Incident> incidents = incidentService.getIncidentsByUserName(userDetails.getUsername());
        return ResponseEntity.ok(incidents);
    }
    @PostMapping
    public ResponseEntity<?> createIncident(@RequestBody Incident incident) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            incident.setUserName(userDetails.getUsername());
            incident.setCreatedAt(new Date());
            incidentService.createIncident(incident);
            return new ResponseEntity<>("Incident created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable Long id) {
        String username = getCurrentUsername();
        return incidentService.getIncidentByIdAndUserName(id, username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Incident> updateIncident(@PathVariable Long id, @RequestBody Incident incident) {
        String username = getCurrentUsername();
        Incident updatedIncident = incidentService.updateIncident(id, incident, username);
        if (updatedIncident != null) {
            return ResponseEntity.ok(updatedIncident);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private String getCurrentUsername() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }
}
