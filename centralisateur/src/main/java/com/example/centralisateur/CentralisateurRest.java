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
    private CentralisateurRemote centralisateur;

    @GET
    @Path("/soldeTotal/{clientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public BigDecimal getSoldeTotal(@PathParam("clientId") Long clientId) {
        return centralisateur.soldeTotalClient(clientId);
    }

    @GET
    @Path("/soldeCompteCourantTotal/{clientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public BigDecimal getSoldeCompteCourantClient(@PathParam("clientId") Long clientId) {
        return centralisateur.soldeCompteCourantClient(clientId);
    }

    @GET
    @Path("/soldeCompteDepotTotal/{clientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public BigDecimal getSoldeCompteDepotClient(@PathParam("clientId") Long clientId) {
        return centralisateur.soldeCompteDepotClient(clientId);
    }

    @GET
    @Path("/soldeCompteCourantByNumero/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    public BigDecimal getSoldeCompteCourantByNumero(@PathParam("numero") String numero) {
        return centralisateur.soldeCompteCourantByNumero(numero);
    }
    

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return centralisateur.combinerDonnees();
    }
}
