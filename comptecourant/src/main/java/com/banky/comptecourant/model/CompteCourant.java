package com.banky.comptecourant.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CompteCourant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String numero;

    @Column(nullable = false, columnDefinition = "DOUBLE DEFAULT 0.0")
    private Double solde;

    @Column(nullable = false, columnDefinition = "DOUBLE DEFAULT 0.0")
    private Double taxe;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}
