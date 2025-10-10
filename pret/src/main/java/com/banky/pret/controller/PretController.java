package com.banky.pret.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.banky.pret.model.Pret;
import com.banky.pret.service.PretService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/compte-pret")
public class PretController {
    private final PretService pretService;

    @Autowired
    public PretController(PretService pretService) {
        this.pretService = pretService;
    }

    @PostMapping("/preter")
    public Map<String, String> preter(@RequestBody Pret pret) {
        pretService.preter(pret);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Prêt effectué avec succès pour le client " + pret.getClient().getId());
        return response;
    }

    @GetMapping("/client/{id}")
    public List<Pret> getPretsClient(@PathVariable Long id) {
        return pretService.getPretsClient(id);
    }

}
