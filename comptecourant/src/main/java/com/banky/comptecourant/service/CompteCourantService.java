package com.banky.comptecourant.service;

import com.banky.comptecourant.model.CompteCourant;
import com.banky.comptecourant.model.OperationCompteCourant;
import com.banky.comptecourant.model.TypeOperationCompteCourant;
import com.banky.comptecourant.model.Client;
import com.banky.comptecourant.repository.CompteCourantRepository;
import com.banky.comptecourant.repository.OperationCompteCourantRepository;
import com.banky.comptecourant.repository.TypeOperationCompteCourantRepository;
import com.banky.comptecourant.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompteCourantService {

    private final CompteCourantRepository compteRepo;
    private final OperationCompteCourantRepository operationRepo;
    private final TypeOperationCompteCourantRepository typeOpRepo;
    private final ClientRepository clientRepo;

    public CompteCourantService(
            CompteCourantRepository compteRepo,
            OperationCompteCourantRepository operationRepo,
            TypeOperationCompteCourantRepository typeOpRepo,
            ClientRepository clientRepo
    ) {
        this.compteRepo = compteRepo;
        this.operationRepo = operationRepo;
        this.typeOpRepo = typeOpRepo;
        this.clientRepo = clientRepo;
    }

    @Transactional
    public void entrerArgent(Long compteId, Double montant) {
        CompteCourant compte = compteRepo.findById(compteId)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));

        Client client = compte.getClient();

        compte.setSolde(compte.getSolde() + montant);

        TypeOperationCompteCourant type = typeOpRepo.findByDescription("ENTREE")
                .orElseThrow(() -> new RuntimeException("Type opération ENTREE non défini"));

        OperationCompteCourant op = new OperationCompteCourant();
        op.setMontant(montant);
        op.setDateInsertion(LocalDateTime.now());
        op.setTypeOperation(type);
        op.setCompteCourant(compte);
        op.setClient(client);

        operationRepo.save(op);
        compteRepo.save(compte);
    }

    @Transactional
    public void sortirArgent(Long compteId, Double montant, Long clientId) {
        CompteCourant compte = compteRepo.findById(compteId)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));

        Client client = clientRepo.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        if (compte.getSolde() < montant) {
            throw new RuntimeException("Solde insuffisant !");
        }

        compte.setSolde(compte.getSolde() - montant);

        TypeOperationCompteCourant type = typeOpRepo.findByDescription("SORTIE")
                .orElseThrow(() -> new RuntimeException("Type opération SORTIE non défini"));

        OperationCompteCourant op = new OperationCompteCourant();
        op.setMontant(montant);
        op.setDateInsertion(LocalDateTime.now());
        op.setTypeOperation(type);
        op.setCompteCourant(compte);
        op.setClient(client);

        operationRepo.save(op);
        compteRepo.save(compte);
    }

    public Double getSoldeCompteCourant(Long compteId) {
        return compteRepo.findById(compteId)
                .map(CompteCourant::getSolde)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));
    }

    public List<OperationCompteCourant> getOperationsCompteCourant(Long compteId) {
        return operationRepo.findByCompteCourantId(compteId);
    }

    public Double getSoldeClient(Long clientId) {
        Double totalSolde = 0.0;
        List<CompteCourant> compteCourant = compteRepo.findByClientId(clientId);
        if (compteCourant.isEmpty()) {
            throw new RuntimeException("Aucun compte courant trouvé pour ce client");
        }
        for (CompteCourant compteCourant2 : compteCourant) {
            totalSolde += compteCourant2.getSolde();
        }
        return totalSolde;
    }

    public CompteCourant findByNumero(String numero){
        return compteRepo.findByNumero(numero);
    }

    public Double getSoldeByNumero(String numero){
        return findByNumero(numero).getSolde();
    }
}
