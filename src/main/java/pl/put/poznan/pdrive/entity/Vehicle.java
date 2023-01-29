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
    private Long tripCount = 0L;

    @Column(name = "battery_charge")
    private Long batteryCharge = 100L;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Trip> trips = new LinkedHashSet<>();

    @OneToMany(mappedBy = "vehicle", orphanRemoval = true)
    private Set<Event> events = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "renter_id")
    private User renter;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

    @Override
    public String toString(){
        return "%d Type: %s, Battery: %d".formatted(id, vehicleType.getName().value, batteryCharge);
    }
}