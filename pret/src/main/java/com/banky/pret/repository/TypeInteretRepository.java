package com.banky.pret.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banky.pret.model.TypeInteret;

@Repository
public interface TypeInteretRepository extends JpaRepository<TypeInteret, Long> {
    
}
