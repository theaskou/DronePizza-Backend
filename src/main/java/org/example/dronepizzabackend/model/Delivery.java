package org.example.dronepizzabackend.model;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deliveryID;
    private String deliveryAddress;
    private LocalTime expectedDeliveryTime;
    private LocalTime actualDeliveryTime;


    @ManyToOne()
    @JoinColumn(name = "pizzaIDfk", referencedColumnName = "pizzaID")
    private Pizza pizza;

    @ManyToOne(optional = true)
    @JoinColumn(name = "droneIDfk", referencedColumnName = "droneID")
    private Drone drone;

    public Delivery(Pizza pizza) {
        this.pizza = pizza;
    }

    public Delivery() {
    }

    public int getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(int deliveryID) {
        this.deliveryID = deliveryID;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public LocalTime getExpectedDeliveryTime() {
        return expectedDeliveryTime;
    }

    public void setExpectedDeliveryTime(LocalTime expectedDeliveryTime) {
        this.expectedDeliveryTime = expectedDeliveryTime;
    }

    public LocalTime getActualDeliveryTime() {
        return actualDeliveryTime;
    }

    public void setActualDeliveryTime(LocalTime actualDeliveryTime) {
        this.actualDeliveryTime = actualDeliveryTime;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }
}
