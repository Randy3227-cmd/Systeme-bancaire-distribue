package com.banky.comptecourant.repository;

import com.banky.comptecourant.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {}
