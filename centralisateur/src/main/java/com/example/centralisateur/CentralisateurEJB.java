package com.example.centralisateur;

import jakarta.ejb.Stateless;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Stateless
public class CentralisateurEJB implements CentralisateurRemote {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String combinerDonnees() {
        return "Service centralisateur actif ✅";
    }

    public BigDecimal soldeTotalClient(Long clientId) {
        try {
            HttpRequest requestCourant = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/compte-courant/" + clientId + "/soldeClient"))
                    .GET()
                    .build();

            HttpResponse<String> responseCourant = httpClient.send(
                    requestCourant, HttpResponse.BodyHandlers.ofString()
            );

            BigDecimal soldeCourant = new BigDecimal(responseCourant.body());

            HttpRequest requestDepot = HttpRequest.newBuilder()
            
                    .uri(new URI("http://localhost:5066/api/compteDepot/client/solde/" + clientId))
                    .GET()
                    .build();

            HttpResponse<String> responseDepot = httpClient.send(
                    requestDepot, HttpResponse.BodyHandlers.ofString()
            );

            JsonNode jsonDepot = mapper.readTree(responseDepot.body());
            BigDecimal soldeDepot = jsonDepot.get("soldeActuel").decimalValue();

            return soldeCourant.add(soldeDepot);

        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }
}
