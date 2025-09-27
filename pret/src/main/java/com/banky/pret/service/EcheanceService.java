package com.banky.pret.service;

import org.springframework.stereotype.Service;

import com.banky.pret.repository.EcheanceRepository;
import com.banky.pret.model.Echeance;

@Service
public class EcheanceService {
    private EcheanceRepository echeanceRepository;

    public EcheanceService(EcheanceRepository echeanceRepository) {
        this.echeanceRepository = echeanceRepository;
    }

    public Echeance addEcheance(Echeance echeance) {
        return echeanceRepository.save(echeance);
    }


    public List<Echeance> addEcheance() {
        return echeanceRepository.findAll();
    }

    public Echeance updateEcheance(Echeance echeance) {
        return echeanceRepository.save(echeance);
    }

    public void deleteEcheanceById(Long id){
        echeanceRepository.deleteById(id);
    }

}
