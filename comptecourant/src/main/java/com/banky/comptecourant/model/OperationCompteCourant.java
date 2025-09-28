package com.banky.comptecourant.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import jakarta.validation.constraints.Positive;

@Entity
@Data
public class OperationCompteCourant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "Le montant doit être supérieur à 0")
    @Column(nullable = false)
    private Double montant;

    @Column(nullable = false)
    private LocalDateTime dateInsertion;

    @ManyToOne
    @JoinColumn(name = "type_operation_id", nullable = false)
    private TypeOperationCompteCourant typeOperation;

    @ManyToOne
    @JoinColumn(name = "compte_courant_id", nullable = false)
    private CompteCourant compteCourant;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}
