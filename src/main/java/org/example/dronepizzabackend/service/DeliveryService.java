package org.example.dronepizzabackend.service;

import org.example.dronepizzabackend.model.Delivery;
import org.example.dronepizzabackend.model.Drone;
import org.example.dronepizzabackend.model.OperationalStatus;
import org.example.dronepizzabackend.model.Pizza;
import org.example.dronepizzabackend.repositories.DeliveryRepository;
import org.example.dronepizzabackend.repositories.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private DroneRepository droneRepository;

    public List<Delivery> getUndeliveredOrders() {
        List<Delivery> undeliveredOrders = new ArrayList<>();
        List<Delivery> allDeliveries = deliveryRepository.findAll();
        for (Delivery delivery : allDeliveries) {
            if (delivery.getActualDeliveryTime() == null) {
                undeliveredOrders.add(delivery);
            }
        }
        return undeliveredOrders;
    }

    public LocalTime setNewDeliveryTime() {
        return LocalTime.now().plusMinutes(30);
    }

    public Delivery addDelivery(Pizza pizza) {
        Delivery delivery = new Delivery();
        delivery.setPizza(pizza);
        delivery.setExpectedDeliveryTime(setNewDeliveryTime());
        deliveryRepository.save(delivery);
        System.out.println(delivery);
        return delivery;
    }

    public List<Delivery> getQueuedDeliveries() {
        List<Delivery> queuedDeliveries = new ArrayList<>();
        List<Delivery> allDeliveries = deliveryRepository.findAll();
        for (Delivery delivery : allDeliveries) {
            if (delivery.getDrone() == null) {
                queuedDeliveries.add(delivery);
            }
        }
        return queuedDeliveries;
    }

    // TODO: Fjern Ddeliveries med actual delivery fra listen
    public Drone droneWithFewestDeliveries() {
        List<Drone> drones = droneRepository.findAll();
        drones.removeIf(drone -> drone.getOperationalStatus() == OperationalStatus.RETIRED);
        Drone droneWithFewestDeliveries = drones.getFirst();
        for (Drone drone : drones) {
            if (drone.getDeliveries().size() < droneWithFewestDeliveries.getDeliveries().size()) {
                droneWithFewestDeliveries = drone;
            }
        }
        System.out.println(droneWithFewestDeliveries.getDroneID());
        return droneWithFewestDeliveries;
    }

    public Delivery updateDelivery(String id) throws Exception {
        Delivery delivery = deliveryRepository.findById(Integer.parseInt(id)).get();
        if (delivery.getDrone() == null) {
            delivery.setDrone(droneWithFewestDeliveries());
        } else {
            throw new Exception("Delivery is already in progress");
        }
        return deliveryRepository.save(delivery);
    }

    public ResponseEntity<Delivery> finishDelivery(String deliveryId) {
        Delivery delivery = deliveryRepository.findById(Integer.parseInt(deliveryId)).get();
        if (delivery.getActualDeliveryTime() == null && delivery.getDrone() != null) {
            delivery.setActualDeliveryTime(LocalTime.now());
            deliveryRepository.save(delivery);
            Drone drone = delivery.getDrone();
            drone.getDeliveries().remove(delivery);
            droneRepository.save(drone);
            return new ResponseEntity<>(delivery, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(delivery, HttpStatus.BAD_REQUEST);
        }
    }
}
