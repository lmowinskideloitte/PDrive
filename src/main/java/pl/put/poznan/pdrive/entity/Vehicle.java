package pl.put.poznan.pdrive.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_type", nullable = false)
    private VehicleType vehicleType;

    @Column(name = "trip_count")
    private Long tripCount;

    @Column(name = "battery_charge")
    private Long batteryCharge;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Trip> trips = new LinkedHashSet<>();

    @OneToMany(mappedBy = "vehicle", orphanRemoval = true)
    private Set<Event> events = new LinkedHashSet<>();

}