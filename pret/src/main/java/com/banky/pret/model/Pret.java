package com.banky.pret.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Pret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double montant;
    private double interet;

    private LocalDate dateOuverture;
    private LocalDate dateFermeture;


    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "pret", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Echeance> echeances;

     @ManyToOne
     private TypeInteret typeInteret;
}

