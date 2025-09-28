package com.banky.comptecourant.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TypeOperationCompteCourant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String description;
}
