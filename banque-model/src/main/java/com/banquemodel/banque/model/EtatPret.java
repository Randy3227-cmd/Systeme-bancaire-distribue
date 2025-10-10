package main.java.com.banquemodel.banque.model;
import java.io.Serializable;

public class EtatPret implements Serializable {
    private Long id;
    private String description;

    public EtatPret() {}
    public EtatPret(Long id) { this.id = id; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
