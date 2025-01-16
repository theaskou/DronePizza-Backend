package org.example.dronepizzabackend.repositories;

import org.example.dronepizzabackend.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
}
