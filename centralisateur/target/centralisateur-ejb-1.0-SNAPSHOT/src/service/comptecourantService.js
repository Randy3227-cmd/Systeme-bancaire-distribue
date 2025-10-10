// src/service/comptecourantService.js
import { fetchData } from "./fetchUtils.js";

const CompteCourantService = {
  async getSoldeByNumero(numero) {
    return fetchData(`/soldeCompteCourantByNumero/${numero}`);
  },

  async getSoldeByClient(clientId) {
    return fetchData(`/soldeCompteCourantTotal/${clientId}`);
  }
};

export default CompteCourantService;
