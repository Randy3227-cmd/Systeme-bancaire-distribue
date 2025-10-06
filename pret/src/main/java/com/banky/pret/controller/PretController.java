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
import java.util.List;


@RestController
@RequestMapping("/compte-pret")
public class PretController {
    private final PretService pretService;

    @Autowired
    public PretController(PretService pretService) {
        this.pretService = pretService;
    }

    
    @PostMapping("/preter")
    public String preter(@RequestBody Pret pret) {
        if (pret.getClient() == null || pret.getClient().getId() == null) {
            throw new RuntimeException("Client requis pour le prêt");
        }
        if (pret.getTypeInteret() == null || pret.getTypeInteret().getId() == null) {
            throw new RuntimeException("Type d'intérêt requis pour le prêt");
        }

        pretService.preter(pret);

        return "Prêt effectué avec succès pour le client " + pret.getClient().getId();
    }

    @GetMapping("/client/{id}")
    public List<Pret> getPretsClient(@PathVariable Long id) {
        return pretService.getPretsClient(id);
    }

}
