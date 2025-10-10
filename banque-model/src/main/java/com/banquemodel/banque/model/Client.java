package main.java.com.banquemodel.banque.model;

import java.io.Serializable;

public class Client implements Serializable {
    private Long id;
    private String nom;

    public Client() {}
    public Client(Long id) { this.id = id; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
}