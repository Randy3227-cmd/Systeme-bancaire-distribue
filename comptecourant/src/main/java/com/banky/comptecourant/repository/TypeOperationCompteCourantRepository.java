package com.banky.comptecourant.repository;

import com.banky.comptecourant.model.TypeOperationCompteCourant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TypeOperationCompteCourantRepository extends JpaRepository<TypeOperationCompteCourant, Long> {
    Optional<TypeOperationCompteCourant> findByDescription(String description);
}
