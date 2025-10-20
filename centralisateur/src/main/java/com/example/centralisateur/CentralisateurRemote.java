package com.example.centralisateur;

import jakarta.ejb.Remote;
import main.java.com.banquemodel.banque.model.Pret;
import main.java.com.banquemodel.banque.model.CompteCourant;
import main.java.com.banquemodel.banque.model.CompteDepot;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Remote
public interface CentralisateurRemote {
    String combinerDonnees();
    BigDecimal soldeTotalClient(Long clientId);
    BigDecimal soldeCompteCourantClient(Long clientId);
    BigDecimal soldeCompteCourantByNumero(String numero);
    BigDecimal soldeCompteDepotClient(Long clientId);
    BigDecimal soldeCompteDepotByNumero(String numero);
    String pretsClient(Long clientId);
    String preter(Pret pret);
    String ouvrirCompteDepot(CompteDepot compte);
    String transaction(String transactiontype,Long idcompte, double montant, LocalDateTime dateInsertion);

}