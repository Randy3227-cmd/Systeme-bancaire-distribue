package com.example.centralisateur;

import jakarta.ejb.Remote;

@Remote
public interface CentralisateurRemote {
    String combinerDonnees();
}
