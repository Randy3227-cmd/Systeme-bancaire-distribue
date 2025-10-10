package com.banky.pret.service;

import com.banky.pret.repository.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.stereotype.Service;
import com.banky.pret.model.*;

@Service
public class PretService {
    private final EcheanceRepository echeanceRepository;
    private final PretRepository pretRepository;
    private final ClientRepository clientRepository;
    private final TypeInteretRepository typeInteretRepository;

    public PretService(PretRepository pretRepository, ClientRepository clientRepository,
                       EcheanceRepository echeanceRepository, TypeInteretRepository typeInteretRepository) {
        this.pretRepository = pretRepository;
        this.clientRepository = clientRepository;
        this.echeanceRepository = echeanceRepository;
        this.typeInteretRepository = typeInteretRepository;
    }

    private static int differenceMois(LocalDate date1, LocalDate date2) {
        return (int) Math.max(1, ChronoUnit.MONTHS.between(date1, date2));
    }

    // Intérêt simple
    public static double interetSimple(double montant, double tauxMensuel, int nbMois) {
        return montant * tauxMensuel * nbMois;
    }

    // Intérêt composé (taux déjà mensuel)
    public static double interetCompose(double montant, double tauxMensuel, int nbMois) {
        return montant * (Math.pow(1 + tauxMensuel, nbMois) - 1);
    }

    // Annuité constante (taux déjà mensuel)
    public static double[][] calculAnnuiteConstante(double montant, double tauxMensuel, int nbMois) {
        double annuite = montant * (tauxMensuel * Math.pow(1 + tauxMensuel, nbMois)) /
                         (Math.pow(1 + tauxMensuel, nbMois) - 1);

        double capitalRestant = montant;
        double[][] result = new double[nbMois][2];

        for (int i = 0; i < nbMois; i++) {
            double interet = capitalRestant * tauxMensuel;
            double capital = annuite - interet;
            result[i][0] = Math.round(capital * 100.0) / 100.0;
            result[i][1] = Math.round(interet * 100.0) / 100.0;
            capitalRestant -= capital;
        }
        return result;
    }

    public void preter(Pret p) {
        Client client = clientRepository.findById(p.getClient().getId())
                .orElseThrow(() -> new RuntimeException("Client introuvable"));
        p.setClient(client);

        TypeInteret typeInteret = typeInteretRepository.findById(p.getTypeInteret().getId())
                .orElseThrow(() -> new RuntimeException("Type d'intérêt introuvable"));
        p.setTypeInteret(typeInteret);

        Pret pret = pretRepository.save(p);

        int duree = differenceMois(pret.getDateOuverture(), pret.getDateFermeture());
        double montant = pret.getMontant();
        double tauxMensuel = pret.getInteret(); // déjà mensuel

        switch (typeInteret.getDescription().toLowerCase()) {
            case "simple" -> {
                double interetTotal = interetSimple(montant, tauxMensuel, duree);
                double capitalParEcheance = montant / duree;
                double interetParEcheance = interetTotal / duree;

                for (int i = 0; i < duree; i++) {
                    Echeance e = new Echeance();
                    e.setMontantCapital(Math.round(capitalParEcheance * 100.0) / 100.0);
                    e.setMontantInteret(Math.round(interetParEcheance * 100.0) / 100.0);
                    e.setEstPayee(false);
                    e.setDatePaiement(null);
                    e.setPret(pret);
                    echeanceRepository.save(e);
                }
            }

            case "compose" -> {
                double interetTotal = interetCompose(montant, tauxMensuel, duree);
                double capitalParEcheance = montant / duree;
                double interetParEcheance = interetTotal / duree;

                for (int i = 0; i < duree; i++) {
                    Echeance e = new Echeance();
                    e.setMontantCapital(Math.round(capitalParEcheance * 100.0) / 100.0);
                    e.setMontantInteret(Math.round(interetParEcheance * 100.0) / 100.0);
                    e.setEstPayee(false);
                    e.setDatePaiement(null);
                    e.setPret(pret);
                    echeanceRepository.save(e);
                }
            }

            case "annuiteconstante" -> {
                double[][] result = calculAnnuiteConstante(montant, tauxMensuel, duree);
                for (int i = 0; i < duree; i++) {
                    Echeance e = new Echeance();
                    e.setMontantCapital(result[i][0]);
                    e.setMontantInteret(result[i][1]);
                    e.setEstPayee(false);
                    e.setDatePaiement(null);
                    e.setPret(pret);
                    echeanceRepository.save(e);
                }
            }

            default -> throw new RuntimeException("Type d'intérêt inconnu : " + typeInteret.getDescription());
        }
    }

    public List<Pret> getPretsClient(Long clientId) {
        return pretRepository.findByClientIdWithEcheances(clientId);
    }
}
