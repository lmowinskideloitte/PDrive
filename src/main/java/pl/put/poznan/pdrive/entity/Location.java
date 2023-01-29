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
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "district")
    private String district;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Station> stations = new LinkedHashSet<>();


    @Override
    public String toString(){
        return "%s %s".formatted(district, city);
    }
}