package com.banky.comptecourant.repository;

import com.banky.comptecourant.model.CompteCourant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteCourantRepository extends JpaRepository<CompteCourant, Long> {

    List<CompteCourant> findByClientId(Long clientId);
    CompteCourant findByNumero(String numero);
}
