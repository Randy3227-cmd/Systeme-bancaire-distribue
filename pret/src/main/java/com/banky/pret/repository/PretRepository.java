package com.banky.pret.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.banky.pret.model.Pret;

@Repository
public interface PretRepository extends JpaRepository<Pret, Long> {
    @Query("SELECT DISTINCT p FROM Pret p LEFT JOIN FETCH p.echeances WHERE p.client.id = :idClient")
    List<Pret> findByClientIdWithEcheances(@Param("idClient") Long idClient);
}
