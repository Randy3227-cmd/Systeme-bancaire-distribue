// src/service/accueilService.js
import { fetchData } from "./fetchUtils.js";

const AccueilService = {
  async getSoldeTotal(clientId) {
    return fetchData(`/soldeTotal/${clientId}`);
  }
};

export default AccueilService;
