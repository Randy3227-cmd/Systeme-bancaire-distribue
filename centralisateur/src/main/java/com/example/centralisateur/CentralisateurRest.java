package com.example.centralisateur;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.math.BigDecimal;

import jakarta.ejb.EJB;

@Path("/centralisateur")
public class CentralisateurRest {

    @EJB
    private CentralisateurRemote centralisateur; // ✅ Utilisez l'interface

    @GET
    @Path("/soldeTotal/{clientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public BigDecimal getSoldeTotal(@PathParam("clientId") Long clientId) {
        return centralisateur.soldeTotalClient(clientId);
    }

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return centralisateur.combinerDonnees();
    }
}