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

    @Override
    public BigDecimal soldeCompteCourantClient(Long clientId) {
        try {
            HttpRequest requestCourant = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/compte-courant/" + clientId + "/soldeClient"))
                    .GET()
                    .build();

            HttpResponse<String> responseCourant = httpClient.send(
                    requestCourant, HttpResponse.BodyHandlers.ofString());

            BigDecimal soldeCourant = new BigDecimal(responseCourant.body());
            return soldeCourant;
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal soldeCompteDepotClient(Long clientId) {
        try {
            HttpRequest requestCourant = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:5066/api/compteDepot/client/solde/" + clientId))
                    .GET()
                    .build();

            HttpResponse<String> responseDepot = httpClient.send(
                    requestCourant, HttpResponse.BodyHandlers.ofString());

            JsonNode jsonDepot = mapper.readTree(responseDepot.body());
            BigDecimal soldeDepot = jsonDepot.get("soldeActuel").decimalValue();
            return soldeDepot;
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal soldeTotalClient(Long clientId) {
        return soldeCompteCourantClient(clientId).add(soldeCompteDepotClient(clientId));
    }

    @Override
    public BigDecimal soldeCompteCourantByNumero(String numero) {
        try {
            HttpRequest requestCourant = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/compte-courant/" + numero + "/soldeCompteByNumero"))
                    .GET()
                    .build();

            HttpResponse<String> responseCourant = httpClient.send(
                    requestCourant, HttpResponse.BodyHandlers.ofString());

            BigDecimal soldeCourant = new BigDecimal(responseCourant.body());
            return soldeCourant;
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal soldeCompteDepotByNumero(String numero) {
        try {
            HttpRequest requestCourant = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:5066/api/compteDepot/numero/solde/" + numero))
                    .GET()
                    .build();

            HttpResponse<String> responseDepot = httpClient.send(
                    requestCourant, HttpResponse.BodyHandlers.ofString());

            JsonNode jsonDepot = mapper.readTree(responseDepot.body());
            BigDecimal soldeDepot = jsonDepot.get("soldeActuel").decimalValue();
            return soldeDepot;
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }


}
