package org.example.dronepizzabackend.repositories;

import org.example.dronepizzabackend.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Integer> {
}
