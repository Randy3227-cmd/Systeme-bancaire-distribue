package com.banky.pret.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banky.pret.model.Echeance;


@Repository
public interface EcheanceRepository extends JpaRepository<Echeance, Long> {
    
}
