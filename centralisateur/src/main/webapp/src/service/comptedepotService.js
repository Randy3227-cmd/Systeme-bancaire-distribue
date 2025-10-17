// src/service/comptedepotService.js
import { fetchData } from "./fetchUtils.js";

const CompteDepotService = {
  async getSoldeByNumero(numero) {
    return fetchData(`/soldeCompteDepotByNumero/${numero}`);
  },

  async getSoldeByClient(clientId) {
    return fetchData(`/soldeCompteDepotTotal/${clientId}`);
  },

  async addDepot(depotData){
    return fetchData('/comptedepot/ouvrir', {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body:JSON.stringify(depotData)
    });
  }
};

export default CompteDepotService;
