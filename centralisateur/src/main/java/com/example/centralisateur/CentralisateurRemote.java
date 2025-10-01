package com.example.centralisateur;

import jakarta.ejb.Remote;
import java.math.BigDecimal;

@Remote
public interface CentralisateurRemote {
    String combinerDonnees();
    BigDecimal soldeTotalClient(Long clientId);
    BigDecimal soldeCompteCourantClient(Long clientId);
    BigDecimal soldeCompteCourantByNumero(String numero);
    BigDecimal soldeCompteDepotClient(Long clientId);
    BigDecimal soldeCompteDepotByNumero(String numero);
}