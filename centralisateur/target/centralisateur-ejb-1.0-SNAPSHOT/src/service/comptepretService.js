// src/service/comptepretService.js
import { fetchData } from "./fetchUtils.js";

const ComptePretService = {
  async getPretsByClient(clientId) {
    return fetchData(`/pretsClient/${clientId}`);
  },

  async addPret(pretData) {
    return fetchData('/preter', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(pretData)
    });
  }
  
};

export default ComptePretService;
