import CompteCourantService from "../service/comptecourantService.js";

window.showCompteCourant = function() {
    document.getElementById("content").innerHTML = `
      <div class="card">
        <h2>Compte Courant</h2>
        <div style="margin-top:30px; border-top:1px solid #ddd; padding-top:20px;">
                <h3>Faire une transaction</h3>

                <label for="numero">ID de compte :</label>
                <input type="text" id="numero" placeholder="Numéro de compte" />

                <label for="solde">montant :</label>
                <input type="number" id="solde" step="0.01" placeholder="dépot" />

                <label for="dateInsertion">Date insertion :</label>
                <input type="datetime-local" id="dateInsertion" />

                <label for="typeTransaction">Type de transaction :</label>
                <select id="typeTransaction">
                    <option value="1">ENTREE</option>
                    <option value="2">SORTIE</option>
                </select>

                <button style="margin-top:10px;" onclick="transaction()">Ajouter dépot</button>
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

window.transaction = async function() {
    const numero = document.getElementById("numero").value;
    const montant = parseFloat(document.getElementById("solde").value);
    const dateInsertion = document.getElementById("dateInsertion").value;
    const typeTransaction = document.getElementById("typeTransaction").value; // ENTREE / SORTIE

    if (!numero || isNaN(montant) || !dateInsertion || !typeTransaction) {
        return alert("⚠️ Veuillez remplir tous les champs correctement.");
    }

    const transactionData = {
        idCompte: parseInt(numero),
        montant: montant,
        dateInsertion: dateInsertion
    };

    try {
        await CompteCourantService.transaction(typeTransaction, transactionData);
        alert("✅ Transaction effectuée avec succès !");
    } catch (err) {
        console.error(err);
        alert("❌ Échec de la transaction.");
    }
};
