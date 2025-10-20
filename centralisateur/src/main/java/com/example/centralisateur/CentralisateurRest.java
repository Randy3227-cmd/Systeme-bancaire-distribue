package com.example.centralisateur;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import main.java.com.banquemodel.banque.model.CompteCourant;
import main.java.com.banquemodel.banque.model.CompteDepot;
import main.java.com.banquemodel.banque.model.Pret;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import main.java.com.banquemodel.banque.model.TransactionRequest;

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
    @Path("/soldeCompteDepotByNumero/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    public BigDecimal getSoldeCompteDepotByNumero(@PathParam("numero") String numero) {
        return centralisateur.soldeCompteDepotByNumero(numero);
    }
    

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return centralisateur.combinerDonnees();
    }

    @GET
    @Path("/pretsClient/{clientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPretsClient(@PathParam("clientId") Long clientId) {
        return centralisateur.pretsClient(clientId);
    }

    @POST
    @Path("/preter")
    public String preter(Pret pret) {
        return centralisateur.preter(pret);
    }

    @POST
    @Path("comptedepot/ouvrir")
    public String ouvrirCompteDepot(CompteDepot compte) {
        return centralisateur.ouvrirCompteDepot(compte);
    }

     @POST
    @Path("/transaction/{transactionType}")
    public Response transaction(
            @PathParam("transactionType") String transactionType,
            TransactionRequest request
    ) {
        String result = centralisateur.transaction(
                transactionType,
                request.getIdCompte(),
                request.getMontant(),
                request.getDateInsertion()
        );

        return Response.ok(result).build();
    }

}