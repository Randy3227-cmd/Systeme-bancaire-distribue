package com.example.centralisateur;

import jakarta.ejb.Remote;
import java.math.BigDecimal;

@Remote
public interface CentralisateurRemote {
    String combinerDonnees();
    BigDecimal soldeTotalClient(Long clientId);
}