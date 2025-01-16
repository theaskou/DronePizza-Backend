package org.example.dronepizzabackend.service;

import jdk.jshell.Snippet;
import org.example.dronepizzabackend.model.Drone;
import org.example.dronepizzabackend.model.OperationalStatus;
import org.example.dronepizzabackend.model.Station;
import org.example.dronepizzabackend.repositories.DroneRepository;
import org.example.dronepizzabackend.repositories.StationRepository;
import org.hibernate.engine.spi.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DroneService {

    @Autowired
    private DroneRepository droneRepository;
    @Autowired
    private StationRepository stationRepository;

    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    public Drone addDrone(Drone drone) {
        List<Station> stations = stationRepository.findAll();
        List<Drone> drones = droneRepository.findAll();
        Map<Integer, Integer> stationCounts = new HashMap<>();
        for (Station station : stations) {
            int stationID = station.getStationID();
            int droneCount = 0;
            for (Drone d : drones) {
                if (d.getStation().getStationID() == stationID) {
                    droneCount++;
                }
            }
            stationCounts.put(stationID, droneCount);
        }
        int stationIDWithFewestDrones = -1;
        int fewestNumberOfDrones = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : stationCounts.entrySet()) {
            if (entry.getValue() < fewestNumberOfDrones) {
                fewestNumberOfDrones = entry.getValue();
                stationIDWithFewestDrones = entry.getKey();
            }
        }
        drone.setStation(stationRepository.findById(stationIDWithFewestDrones).get());
        return droneRepository.save(drone);
    }

    public ResponseEntity<Drone> enableDrone(Drone drone) {
        int id = drone.getDroneID();
        if (droneRepository.existsById(id)) {
            drone.setOperationalStatus(OperationalStatus.ACTIVE);
            System.out.println(drone.getOperationalStatus());
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Drone> disableDrone(Drone drone) {
        int id = drone.getDroneID();
        if (droneRepository.existsById(id)) {
            drone.setOperationalStatus(OperationalStatus.INACTIVE);
            System.out.println(drone.getOperationalStatus());
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Drone> retireDrone(Drone drone) {
        int id = drone.getDroneID();
        if (droneRepository.existsById(id)) {
            drone.setOperationalStatus(OperationalStatus.RETIRED);
            System.out.println(drone.getOperationalStatus());
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
