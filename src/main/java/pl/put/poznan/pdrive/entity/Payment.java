package pl.put.poznan.pdrive.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cost", nullable = false)
    private Long cost;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    private Trip trip;

}