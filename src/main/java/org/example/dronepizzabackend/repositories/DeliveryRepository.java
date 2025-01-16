package org.example.dronepizzabackend.repositories;

import org.example.dronepizzabackend.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
}
