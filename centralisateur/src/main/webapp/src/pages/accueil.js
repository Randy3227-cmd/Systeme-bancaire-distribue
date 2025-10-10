import AccueilService from "../service/accueilService.js";

window.showAccueil = function () {
    document.getElementById("content").innerHTML = `
      <div class="card">
        <h2>Vérifier le solde total</h2>
        <label for="clientId">ID Client :</label>
        <input type="number" id="clientId" placeholder="Entrez l'ID client" />
        <button onclick="loadSoldeTotal()">Voir le solde</button>
        <div id="result"></div>
      </div>
    `;
}

window.loadSoldeTotal = async function() {
    const clientId = document.getElementById("clientId").value;
    const result = document.getElementById("result");
    if (!clientId) return alert("Veuillez entrer un ID client.");

    result.innerHTML = "⏳ Chargement...";
    try {
        const solde = await AccueilService.getSoldeTotal(clientId);
        result.innerText = `💰 Solde total du client ${clientId} : ${solde} Ar`;
    } catch {
        result.innerText = "⚠️ Impossible de récupérer le solde.";
    }
}
