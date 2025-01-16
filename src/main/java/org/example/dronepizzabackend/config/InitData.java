package org.example.dronepizzabackend.config;

import org.example.dronepizzabackend.model.*;
import org.example.dronepizzabackend.repositories.DeliveryRepository;
import org.example.dronepizzabackend.repositories.DroneRepository;
import org.example.dronepizzabackend.repositories.PizzaRepository;
import org.example.dronepizzabackend.repositories.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    DroneRepository droneRepository;
    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    StationRepository stationRepository;

    @Override
    public void run(String... args) throws Exception {
        // Generate and persist mock station data
        List<Station> stations = generateMockStations();
        stationRepository.saveAll(stations);

        // Generate and persist mock drone data
        List<Drone> drones = generateMockDrones(stations);
        droneRepository.saveAll(drones);

        // Generate and persist mock pizza data
        List<Pizza> pizzas = generateMockPizzas();
        pizzaRepository.saveAll(pizzas);

        // Generate and persist mock delivery data
        List<Delivery> deliveries = generateMockDeliveries(drones, pizzas);
        deliveryRepository.saveAll(deliveries);
    }

    public List<Station> generateMockStations() {
        List<Station> stations = new ArrayList<>();

        // Station 1: Central Copenhagen
        Station centralCopenhagen = new Station();
        centralCopenhagen.setLongitude("12.34"); // East
        centralCopenhagen.setLatitude("55.41"); // North
        stations.add(centralCopenhagen);

        // Station 2: Nørrebro
        Station nørrebro = new Station();
        nørrebro.setLongitude("12.55"); // Approximate longitude for Nørrebro
        nørrebro.setLatitude("55.70"); // Approximate latitude for Nørrebro
        stations.add(nørrebro);

        // Station 3: Amager
        Station amager = new Station();
        amager.setLongitude("12.60"); // Approximate longitude for Amager
        amager.setLatitude("55.63"); // Approximate latitude for Amager
        stations.add(amager);

        // Station 4: Valby
        Station valby = new Station();
        valby.setLongitude("12.50"); // Approximate longitude for Valby
        valby.setLatitude("55.65"); // Approximate latitude for Valby
        stations.add(valby);

        // Station 5: Østerbro
        Station østerbro = new Station();
        østerbro.setLongitude("12.57"); // Approximate longitude for Østerbro
        østerbro.setLatitude("55.70"); // Approximate latitude for Østerbro
        stations.add(østerbro);

        return stations;
    }


    public List<Drone> generateMockDrones(List<Station> stations) {
        List<Drone> drones = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Drone drone = new Drone();

            // Cycle through the three statuses: RETIRED, ACTIVE, and INACTIVE
            OperationalStatus status;
            if (i % 3 == 0) {
                status = OperationalStatus.RETIRED;
            } else if (i % 3 == 1) {
                status = OperationalStatus.ACTIVE;
            } else {
                status = OperationalStatus.INACTIVE;
            }
            drone.setOperationalStatus(status);

            // Assign the drone to a station
            drone.setStation(stations.get(i % stations.size()));
            drones.add(drone);
        }

        return drones;
    }

    public List<Pizza> generateMockPizzas() {
        List<Pizza> pizzas = new ArrayList<>();
        String[] pizzaNames = {
                "Margherita", "Pepperoni", "BBQ Chicken", "Hawaiian", "Vegetarian", "Four Cheese",
                "Meat Lovers", "Seafood", "Buffalo Chicken", "Chicken Alfredo"
        };

        for (int i = 0; i < pizzaNames.length; i++) {
            Pizza pizza = new Pizza();
            pizza.setPizzaName(pizzaNames[i]);
            pizza.setPizzaPrice(65 + (i * 10));
            pizzas.add(pizza);
        }

        return pizzas;
    }

    public List<Delivery> generateMockDeliveries(List<Drone> drones, List<Pizza> pizzas) {
        List<Delivery> deliveries = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            Drone drone = drones.get(i % drones.size());
            Pizza pizza = pizzas.get(i % pizzas.size());

            // Skip retired drones
            if (drone.getOperationalStatus() == OperationalStatus.RETIRED) {
                continue;
            }

            Delivery delivery = new Delivery();
            delivery.setDrone(drone);
            delivery.setPizza(pizza);
            delivery.setDeliveryAddress("Delivery Address #" + (i + 1));
            delivery.setExpectedDeliveryTime(LocalTime.of(14, i % 60));

            // Only assign actual delivery time if the drone is not "ACTIVE"
            if (drone.getOperationalStatus() != OperationalStatus.ACTIVE) {
                delivery.setActualDeliveryTime(LocalTime.of(15, i % 60));
            }

            deliveries.add(delivery);
        }

        return deliveries;
    }
}
