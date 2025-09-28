package com.banky.pret.service;

import com.banky.pret.repository.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.banky.pret.model.*;

@Service
public class PretService {
    private PretRepository pretRepository;
    private ClientRepository clientRepository;
    private EcheanceRepository echeanceRepository;

    public PretService(PretRepository pretRepository, EcheanceRepository echeanceRepository) {
        this.pretRepository = pretRepository;
        this.echeanceRepository = echeanceRepository;
    }

    public Pret addPret(Pret pret) {
        return pretRepository.save(pret);
    }

    public List<Pret> getAllPret() {
        return pretRepository.findAll();
    }

    public Pret updatePret(Pret p) {
        return pretRepository.save(p);
    }

    public void deletePretById(Long id) {
        pretRepository.deleteById(id);
    }

    private static long differenceMois(LocalDate date1, LocalDate date2) {
        return ChronoUnit.MONTHS.between(date1, date2);
    }

    public static double interetSimple(double volaInitial, double taux, int nbmois) {
        return volaInitial * taux * nbmois;
    }

    public static double interetCompose(double volaInitial, double taux, int nbmois) {
        return volaInitial * Math.pow(1 + taux, nbmois) - volaInitial;
    }

    public void preter(Pret p, TypeInteret tI) {
        pretRepository.save(p);

        long duree = differenceMois(p.getDateOuverture(), p.getDateFermeture());
        if (duree <= 0) {
            duree = 1; 
        }

        double montant = p.getMontant();
        double interetTotal = p.getInteret();

        if (tI.getDescription().equalsIgnoreCase("Simple")) {
            interetTotal = interetSimple(montant, interetTotal, (int) duree);
        } else if (tI.getDescription().equalsIgnoreCase("Compose")) {
            interetTotal = interetCompose(montant, interetTotal, (int) duree);
        }

        double capitalParEcheance = montant / duree;
        double interetParEcheance = interetTotal / duree;

        for (int i = 0; i < duree; i++) {
            Echeance e = new Echeance();
            e.setMontantCapital(capitalParEcheance);
            e.setMontantInteret(interetParEcheance);
            e.setEstPayee(false);
            e.setDatePaiement(null);
            e.setPret(p);
            echeanceRepository.save(e);
        }
    }

}
