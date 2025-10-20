package main.java.com.banquemodel.banque.model;

import java.time.LocalDateTime;

public class TransactionRequest {
    private Long idCompte;
    private double montant;
    private LocalDateTime dateInsertion;

    // Getters et Setters
    public Long getIdCompte() { return idCompte; }
    public void setIdCompte(Long idCompte) { this.idCompte = idCompte; }

    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }

    public LocalDateTime getDateInsertion() { return dateInsertion; }
    public void setDateInsertion(LocalDateTime dateInsertion) { this.dateInsertion = dateInsertion; }
}
