package com.example.centralisateur;

import jakarta.ejb.Stateless;
import main.java.com.banquemodel.banque.model.Pret;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Stateless
public class CentralisateurEJB implements CentralisateurRemote {

    private static final String COMPTE_COURANT = "http://localhost:8080/compte-courant";
    private static final String COMPTE_DEPOT = "http://localhost:5066/api/compteDepot";
    private static final String PRET = "http://localhost:8081/compte-pret"; // <-- mise à jour

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper mapper;

    public CentralisateurEJB() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.findAndRegisterModules();
    }

    @Override
    public String combinerDonnees() {
        return "Service centralisateur actif ✅";
    }

    @Override
    public BigDecimal soldeCompteCourantClient(Long clientId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(COMPTE_COURANT + "/" + clientId + "/soldeClient"))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return new BigDecimal(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal soldeCompteDepotClient(Long clientId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(COMPTE_DEPOT + "/client/solde/" + clientId))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            JsonNode jsonDepot = mapper.readTree(response.body());
            return jsonDepot.get("soldeActuel").decimalValue();
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal soldeTotalClient(Long clientId) {
        return soldeCompteCourantClient(clientId).add(soldeCompteDepotClient(clientId));
    }

    @Override
    public String pretsClient(Long clientId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(PRET + "/client/" + clientId))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\":\"Impossible de récupérer les prêts\"}";
        }
    }

    @Override
    public String preter(Pret pret) {
        try {
            String json = mapper.writeValueAsString(pret);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(PRET + "/preter"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de l'appel du microservice Pret : " + e.getMessage();
        }
    }

    @Override
    public BigDecimal soldeCompteCourantByNumero(String numero) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(COMPTE_COURANT + "/" + numero + "/soldeCompteByNumero"))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return new BigDecimal(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal soldeCompteDepotByNumero(String numero) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(COMPTE_DEPOT + "/numero/solde/" + numero))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            JsonNode jsonDepot = mapper.readTree(response.body());
            return jsonDepot.get("soldeActuel").decimalValue();
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

}
