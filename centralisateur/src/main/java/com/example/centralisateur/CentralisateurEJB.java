package com.example.centralisateur;

import jakarta.ejb.Stateless;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

@Stateless
public class CentralisateurEJB implements CentralisateurRemote {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Override
    public String combinerDonnees() {
        try {
            // Appel REST module Spring Boot
            HttpRequest requestSpring = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/api/spring"))
                    .GET()
                    .build();

            HttpResponse<String> responseSpring = httpClient.send(requestSpring,
                    HttpResponse.BodyHandlers.ofString());

            // Appel REST module .NET
            HttpRequest requestDotNet = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:5066/api/dotnet"))
                    .GET()
                    .build();

            HttpResponse<String> responseDotNet = httpClient.send(requestDotNet,
                    HttpResponse.BodyHandlers.ofString());

            // Combiner les résultats
            String result = "{ \"spring\": " + responseSpring.body()
                          + ", \"dotnet\": " + responseDotNet.body() + " }";

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\": \"Impossible de récupérer les données\"}";
        }
    }
}
