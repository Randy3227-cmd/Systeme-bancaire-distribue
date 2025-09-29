package com.banky.pret.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banky.pret.model.Client;
import com.banky.pret.model.Pret;
import com.banky.pret.model.TypeInteret;
import com.banky.pret.service.PretService;

@RestController
@RequestMapping("/compte-pret")
public class PretController {
    private final PretService pretService;

    @Autowired
    public PretController(PretService pretService) {
        this.pretService = pretService;
    }

    @GetMapping("/preter")
    public String preter(
            @RequestParam double montant,
            @RequestParam double interet,
            @RequestParam String dateOuverture,
            @RequestParam String dateFermeture,
            @RequestParam Long clientId,
            @RequestParam String typeInteret) {

        Client client = new Client();
        client.setId(clientId);

        Pret pret = new Pret();
        pret.setMontant(montant);
        pret.setInteret(interet);
        pret.setDateOuverture(LocalDate.parse(dateOuverture));
        pret.setDateFermeture(LocalDate.parse(dateFermeture));
        pret.setClient(client);

        TypeInteret tI = new TypeInteret();
        tI.setDescription(typeInteret);

        pretService.preter(pret, tI);

        return "Prêt effectué avec succès";
    }

}
