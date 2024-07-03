package com.rapifuzz.projectApp.repository;

import com.rapifuzz.projectApp.models.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {

    List<Incident> findByUserId(Long id);

    List<Incident> findByUserName(String userName);

    Optional<Incident> findByIdAndUserName(Long id, String userName);
}
