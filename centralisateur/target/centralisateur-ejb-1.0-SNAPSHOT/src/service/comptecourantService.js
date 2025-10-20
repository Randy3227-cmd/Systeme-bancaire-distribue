// src/service/comptecourantService.js
import { fetchData } from "./fetchUtils.js";

const CompteCourantService = {
  async getSoldeByNumero(numero) {
    return fetchData(`/soldeCompteCourantByNumero/${numero}`);
  },

  async getSoldeByClient(clientId) {
    return fetchData(`/soldeCompteCourantTotal/${clientId}`);
  },

  async transaction(typeTransaction, transactionData) {
    return fetchData(`/transaction/${typeTransaction}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(transactionData)
    });
}

};

export default CompteCourantService;
