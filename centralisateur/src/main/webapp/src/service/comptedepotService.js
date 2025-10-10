// src/service/comptedepotService.js
import { fetchData } from "./fetchUtils.js";

const CompteDepotService = {
  async getSoldeByNumero(numero) {
    return fetchData(`/soldeCompteDepotByNumero/${numero}`);
  },

  async getSoldeByClient(clientId) {
    return fetchData(`/soldeCompteDepotTotal/${clientId}`);
  }
};

export default CompteDepotService;
