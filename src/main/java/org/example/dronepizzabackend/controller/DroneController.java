package org.example.dronepizzabackend.controller;

import org.example.dronepizzabackend.model.Drone;
import org.example.dronepizzabackend.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DroneController {

    @Autowired
    DroneService droneService;

    @GetMapping("/drones")
    public List<Drone> allDrones() {
        return droneService.getAllDrones();
    }

    @PostMapping("/drones/add")
    public Drone addDrone(@RequestBody Drone drone){
        return droneService.addDrone(drone);
    }

    @PostMapping("/drones/enable")
    public ResponseEntity<Drone> enableDrone(@RequestBody Drone drone){
        return droneService.enableDrone(drone);
    }

    @PostMapping("/drones/disable")
    public ResponseEntity<Drone> disableDrone(@RequestBody Drone drone){
        return droneService.disableDrone(drone);
    }

    @PostMapping("/drones/retire")
    public ResponseEntity<Drone> retireDrone(@RequestBody Drone drone){
        return droneService.retireDrone(drone);
    }



}
