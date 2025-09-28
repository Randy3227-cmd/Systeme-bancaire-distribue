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

    // PostgreSQL n'accepte pas DOUBLE → utiliser double precision
    @Column(nullable = false, columnDefinition = "double precision default 0.0")
    private Double solde = 0.0;

    @Column(nullable = false, columnDefinition = "double precision default 0.0")
    private Double taxe = 0.0;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}
