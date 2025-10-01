package com.banky.pret.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banky.pret.model.EtatPret;

@Repository
public interface EtatPretRepository extends JpaRepository<EtatPret, Long> {
    
}
