package main.java.com.banquemodel.banque.model;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Echeance implements Serializable {
    private Long id;
    private LocalDateTime datePaiement;
    private Double montantCapital;
    private Double montantInteret;
    private Boolean estPayee;

    public Echeance() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDatePaiement() { return datePaiement; }
    public void setDatePaiement(LocalDateTime datePaiement) { this.datePaiement = datePaiement; }

    public Double getMontantCapital() { return montantCapital; }
    public void setMontantCapital(Double montantCapital) { this.montantCapital = montantCapital; }

    public Double getMontantInteret() { return montantInteret; }
    public void setMontantInteret(Double montantInteret) { this.montantInteret = montantInteret; }

    public Boolean getEstPayee() { return estPayee; }
    public void setEstPayee(Boolean estPayee) { this.estPayee = estPayee; }
}