package pl.put.poznan.pdrive.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

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

}