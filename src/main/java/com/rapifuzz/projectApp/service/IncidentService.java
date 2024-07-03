package com.rapifuzz.projectApp.service;

import java.util.List;
import java.util.Optional;

import com.rapifuzz.projectApp.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapifuzz.projectApp.models.Incident;

@Service
public class IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    public Incident createIncident(Incident incident) {
        return incidentRepository.save(incident);
    }

    public List<Incident> getIncidentsByUserName(String userName) {
        return incidentRepository.findByUserName(userName);
    }

    public List<Incident> getIncidentsByUserId(Long userId) {
        return incidentRepository.findByUserId(userId);
    }

    public Optional<Incident> getIncidentByIdAndUserName(Long id, String userName) {
        return incidentRepository.findByIdAndUserName(id, userName);
    }

    public Incident updateIncident(Long id, Incident incident, String userName) {
        Optional<Incident> existingIncident = incidentRepository.findByIdAndUserName(id, userName);
        if (existingIncident.isPresent()) {
            Incident updatedIncident = existingIncident.get();
            updatedIncident.setIncidentDetails(incident.getIncidentDetails());
            updatedIncident.setPriority(incident.getPriority());
            updatedIncident.setStatus(incident.getStatus());
            return incidentRepository.save(updatedIncident);
        }
        return null;
    }
}
