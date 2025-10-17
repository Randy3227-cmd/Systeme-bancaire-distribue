import CompteCourantService from "../service/comptecourantService.js";

window.showCompteCourant = function() {
    document.getElementById("content").innerHTML = `
      <div class="card">
        <h2>Compte Courant</h2>
        <div style="margin-top:30px; border-top:1px solid #ddd; padding-top:20px;">
                <h3>Faire un dépot</h3>

                <label for="numero">Numéro de compte :</label>
                <input type="text" id="numero" placeholder="Numéro de compte" />

                <label for="solde">montant :</label>
                <input type="number" id="solde" step="0.01" placeholder="dépot" />

                <label for="taux">Intérêt (%) :</label>
                <input type="number" id="taux" step="0.01" placeholder="Taux" />

                <label for="dateOuverture">Date d'ouverture :</label>
                <input type="datetime-local" id="dateOuverture" />

                <label for="dateEcheance">Date d'écheance :</label>
                <input type="datetime-local" id="dateEcheance" />

                <button style="margin-top:10px;" onclick="insertDepot()">Ajouter dépot</button>
            </div>
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
