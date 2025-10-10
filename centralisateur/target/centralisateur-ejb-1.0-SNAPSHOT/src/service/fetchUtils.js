// src/service/fetchUtils.js
/**
 * Fonction utilitaire pour récupérer des données depuis une API REST
 * @param {string} endpoint - Le chemin après la base URL (ex: "/soldeTotal/1")
 * @returns {Promise<any>} - Les données JSON
 */


// src/service/fetchUtils.js
import { API_BASE_URL } from "../config/apiConfig.js";

export async function fetchData(endpoint, options = {}) {
  const url = `${API_BASE_URL}${endpoint}`;

  try {
    const response = await fetch(url, options);
    if (!response.ok) {
      const text = await response.text();
      throw new Error(`Erreur HTTP ${response.status} : ${text}`);
    }
    return await response.json();
  } catch (error) {
    console.error("Erreur lors de l'appel à l'API :", error);
    throw error;
  }
}
