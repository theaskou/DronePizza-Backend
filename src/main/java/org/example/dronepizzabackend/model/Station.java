package org.example.dronepizzabackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stationID;
    private String longitude;
    private String latitude;

    public int getStationID() {
        return stationID;
    }

    @OneToMany(mappedBy = "station")
    @JsonBackReference
    private Set<Drone> drones = new HashSet<>();


    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Set<Drone> getDrones() {
        return drones;
    }

    public void setDrones(Set<Drone> drones) {
        this.drones = drones;
    }
}
