package org.example.dronepizzabackend.controller;

import org.example.dronepizzabackend.model.Delivery;
import org.example.dronepizzabackend.model.Drone;
import org.example.dronepizzabackend.model.Pizza;
import org.example.dronepizzabackend.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/deliveries")
    public List<Delivery> getUndeliveredDeliveries(){
        return deliveryService.getUndeliveredOrders();
    }

    // TODO: Add address
    @PostMapping("/deliveries/add")
    public Delivery addDelivery(@RequestBody Pizza pizza){
        return deliveryService.addDelivery(pizza);
    }

    @GetMapping("/deliveries/queue")
    public List<Delivery> getDeliveriesQueue(){
        return deliveryService.getQueuedDeliveries();
    }

    // TODO: Bedre fejlhåndtering
    @PostMapping("/deliveries/schedule/{id}")
    public Delivery updateDelivery(@PathVariable String id) throws Exception {
        return deliveryService.updateDelivery(id);
    }

    // TODO: deliveries/finish skal tage en givet levering, der er i gang, og markere den som færdig i
    //  det øjeblik som endpoint'et kontaktes. Hvis leveringen ikke har en Drone, skal den fejle.
    @PostMapping("/deliveries/finish/{id}")
    public ResponseEntity<Delivery> finishDelivery(@PathVariable String id) throws Exception {
        return deliveryService.finishDelivery(id);
    }



}
