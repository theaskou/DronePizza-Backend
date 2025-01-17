package org.example.dronepizzabackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int droneID;
    private UUID serialUUID;
    private OperationalStatus operationalStatus;

    @OneToMany(mappedBy = "drone")
    @JsonBackReference
    private Set<Delivery> deliveries = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "stationIDfk", referencedColumnName = "stationID")
    private Station station;

    public Drone() {
        this.serialUUID = UUID.randomUUID();
        this.operationalStatus = OperationalStatus.INACTIVE;
    }

    public int getDroneID() {
        return droneID;
    }

    public void setDroneID(int droneID) {
        this.droneID = droneID;
    }

    public UUID getSerialUUID() {
        return serialUUID;
    }

    public void setSerialUUID(UUID serialUUID) {
        this.serialUUID = serialUUID;
    }

    public OperationalStatus getOperationalStatus() {
        return operationalStatus;
    }

    public void setOperationalStatus(OperationalStatus operationalStatus) {
        this.operationalStatus = operationalStatus;
    }

    public Set<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(Set<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
