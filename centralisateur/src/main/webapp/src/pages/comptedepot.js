import CompteDepotService from "../service/comptedepotService.js";

window.showCompteDepot = function() {
    document.getElementById("content").innerHTML = `
      <div class="card">
        <h2>Compte Dépôt</h2>
        <label for="numero">Numéro de Compte :</label>
        <input type="text" id="numero" placeholder="ex: CD-001" />
        <button onclick="loadDepotByNumero()">Voir le solde</button>
        <br>
        <label for="clientId">ID Client :</label>
        <input type="number" id="clientId" placeholder="Entrez l'ID client" />
        <button onclick="loadDepotByClient()">Voir le solde</button>
        <div id="result"></div>
      </div>
    `;
}

window.loadDepotByNumero = async function() {
    const numero = document.getElementById("numero").value;
    const result = document.getElementById("result");
    if (!numero) return alert("Veuillez entrer un numéro.");
    result.innerHTML = "⏳ Chargement...";
    try {
        const solde = await CompteDepotService.getSoldeByNumero(numero);
        result.innerText = `💰 Solde sur le compte ${numero} : ${solde} Ar`;
    } catch {
        result.innerText = "⚠️ Erreur lors du chargement.";
    }
}

window.loadDepotByClient = async function() {
    const clientId = document.getElementById("clientId").value;
    const result = document.getElementById("result");
    if (!clientId) return alert("Veuillez entrer un ID client.");
    result.innerHTML = "⏳ Chargement...";
    try {
        const solde = await CompteDepotService.getSoldeByClient(clientId);
        result.innerText = `💰 Solde total du client ${clientId} : ${solde} Ar`;
    } catch {
        result.innerText = "⚠️ Erreur lors du chargement.";
    }
}
