import CompteCourantService from "../service/comptecourantService.js";

window.showCompteCourant = function() {
    document.getElementById("content").innerHTML = `
      <div class="card">
        <h2>Compte Courant</h2>
        <label for="numero">Numéro de Compte :</label>
        <input type="text" id="numero" placeholder="ex: CC-001" />
        <button onclick="loadSoldeCourantByNumero()">Voir le solde</button>
        <br>
        <label for="clientId">ID Client :</label>
        <input type="number" id="clientId" placeholder="Entrez l'ID client" />
        <button onclick="loadSoldeCourantByClient()">Voir le solde</button>
        <div id="result"></div>
      </div>
    `;
}

window.loadSoldeCourantByNumero = async function() {
    const numero = document.getElementById("numero").value;
    const result = document.getElementById("result");
    if (!numero) return alert("Veuillez entrer un numéro.");
    result.innerHTML = "⏳ Chargement...";

    try {
        const solde = await CompteCourantService.getSoldeByNumero(numero);
        result.innerText = `💰 Solde total sur le compte N° ${numero} : ${solde} Ar`;
    } catch {
        result.innerText = "⚠️ Erreur lors du chargement.";
    }
}

window.loadSoldeCourantByClient = async function() {
    const clientId = document.getElementById("clientId").value;
    const result = document.getElementById("result");
    if (!clientId) return alert("Veuillez entrer un ID client.");
    result.innerHTML = "⏳ Chargement...";

    try {
        const solde = await CompteCourantService.getSoldeByClient(clientId);
        result.innerText = `💰 Solde total du client ${clientId} : ${solde} Ar`;
    } catch {
        result.innerText = "⚠️ Erreur lors du chargement.";
    }
}
