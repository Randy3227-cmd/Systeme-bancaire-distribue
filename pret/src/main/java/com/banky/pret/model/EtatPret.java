package com.banky.pret.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EtatPret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
}
