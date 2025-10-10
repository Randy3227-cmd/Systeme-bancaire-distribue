package main.java.com.banquemodel.banque.model;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Pret implements Serializable {
    private Long id;
    private double montant;
    private double interet;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOuverture;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateFermeture;
    private Client client;
    private TypeInteret typeInteret;
    private List<Echeance> echeances;

    public Pret() {}

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }

    public double getInteret() { return interet; }
    public void setInteret(double interet) { this.interet = interet; }

    public LocalDate getDateOuverture() { return dateOuverture; }
    public void setDateOuverture(LocalDate dateOuverture) { this.dateOuverture = dateOuverture; }

    public LocalDate getDateFermeture() { return dateFermeture; }
    public void setDateFermeture(LocalDate dateFermeture) { this.dateFermeture = dateFermeture; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public TypeInteret getTypeInteret() { return typeInteret; }
    public void setTypeInteret(TypeInteret typeInteret) { this.typeInteret = typeInteret; }

    public List<Echeance> getEcheances() { return echeances; }
    public void setEcheances(List<Echeance> echeances) { this.echeances = echeances; }
}
