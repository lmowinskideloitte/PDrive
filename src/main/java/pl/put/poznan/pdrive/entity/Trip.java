package pl.put.poznan.pdrive.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "origin_station_id", nullable = false)
    private Station originStation;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "destination_station_id", nullable = false)
    private Station destinationStation;

    @Column(name = "distance", nullable = false)
    private Long distance;

    @OneToMany(mappedBy = "trip", orphanRemoval = true)
    private Set<Event> events = new LinkedHashSet<>();

    public Trip(Payment payment, Vehicle vehicle, Station originStation, Station destinationStation, Long distance, Set<Event> events) {
        this.payment = payment;
        this.vehicle = vehicle;
        this.originStation = originStation;
        this.destinationStation = destinationStation;
        this.distance = distance;
        this.events = events;
    }
}