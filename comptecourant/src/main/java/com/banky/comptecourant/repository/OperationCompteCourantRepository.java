package com.banky.comptecourant.repository;

import com.banky.comptecourant.model.OperationCompteCourant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OperationCompteCourantRepository extends JpaRepository<OperationCompteCourant, Long> {
    List<OperationCompteCourant> findByCompteCourantId(Long compteId);
}