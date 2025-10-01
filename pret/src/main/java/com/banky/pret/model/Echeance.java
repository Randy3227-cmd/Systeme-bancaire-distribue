package com.banky.pret.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Echeance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime datePaiement;
    private Double montantCapital;
    private Double montantInteret;
    private Boolean estPayee;

    @ManyToOne
    private Pret pret;
}
