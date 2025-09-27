package com.banky.pret.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banky.pret.repository.EtatPretRepository;
import com.banky.pret.model.EtatPret;

@Service
public class EtatPretService {
    private EtatPretRepository etatPretRepository;

    public EtatPretService(EtatPretRepository etatPretRepository) {
        this.etatPretRepository = etatPretRepository;
    }

    public EtatPret addEtatPret(EtatPret etatPret) {
        return etatPretRepository.save(etatPret);
    }
    public List<EtatPret> getAllEtatPret() {
        return etatPretRepository.findAll();
    }

    public EtatPret updateEtatPret(EtatPret eP) {
        return etatPretRepository.save(eP);
    }

    public void deleteEtatPretById(Long id){
        etatPretRepository.deleteById(id);
    }
}
