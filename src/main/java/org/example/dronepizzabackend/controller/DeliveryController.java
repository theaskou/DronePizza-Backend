package org.example.dronepizzabackend.controller;

import org.example.dronepizzabackend.model.Delivery;
import org.example.dronepizzabackend.model.Drone;
import org.example.dronepizzabackend.model.Pizza;
import org.example.dronepizzabackend.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/deliveries")
    public List<Delivery> getUndeliveredDeliveries(){
        return deliveryService.getUndeliveredOrders();
    }

    // TODO: Add address
    @PostMapping("/deliveries/add")
    public Delivery addDelivery(@RequestBody Delivery delivery){
        return deliveryService.addDelivery(delivery);
    }

    @GetMapping("/deliveries/queue")
    public List<Delivery> getDeliveriesQueue(){
        return deliveryService.getQueuedDeliveries();
    }

    // TODO: Bedre fejlhåndtering
    @PostMapping("/deliveries/schedule/{id}")
    public ResponseEntity<Delivery> updateDelivery(@PathVariable String id) {
        return deliveryService.updateDelivery(id);
    }

    // TODO: Bedre fejlhåndtering
    @PostMapping("/deliveries/finish/{id}")
    public ResponseEntity<Delivery> finishDelivery(@PathVariable String id) {
        return deliveryService.finishDelivery(id);
    }



}
