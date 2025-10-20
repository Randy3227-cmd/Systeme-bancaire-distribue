package com.banky.comptecourant.controller;

import com.banky.comptecourant.model.OperationCompteCourant;
import com.banky.comptecourant.service.CompteCourantService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/compte-courant")
public class CompteCourantController {

    private final CompteCourantService service;

    public CompteCourantController(CompteCourantService service) {
        this.service = service;
    }

    /** ✅ Dépôt d’argent */
    @GetMapping("/{id}/entrer")
    public String entrerArgent(
            @PathVariable Long id,
            @RequestParam Double montant,
            @RequestParam LocalDateTime dateInsertion
    ) {
        service.entrerArgent(id, montant, dateInsertion);
        return "Dépôt de " + montant + " effectué avec succès sur le compte " + id;
    }

    /** ✅ Retrait d’argent */
    @PostMapping("/{id}/sortir")
    public String sortirArgent(
            @PathVariable Long id,
            @RequestParam Double montant,
            @RequestParam LocalDateTime dateInsertion) {
        service.sortirArgent(id, montant, dateInsertion);
        return "Retrait de " + montant + " effectué avec succès sur le compte " + id;
    }

    /** ✅ Consulter solde */
    @GetMapping("/{id}/soldeCompte")
    public Double getSolde(@PathVariable Long id) {
        return service.getSoldeCompteCourant(id);
    }

    /** ✅ Consulter historique des opérations */
    @GetMapping("/{id}/operations")
    public List<OperationCompteCourant> getOperations(@PathVariable Long id) {
        return service.getOperationsCompteCourant(id);
    }

    @GetMapping("/{id}/soldeClient")
    public Double getSoldeClient(@PathVariable Long id) {
        return service.getSoldeClient(id);
    }

    @GetMapping("/{numero}/soldeCompteByNumero")
    public Double getSoldeByNumero(@PathVariable String numero){
        return service.getSoldeByNumero(numero);
    }
}
