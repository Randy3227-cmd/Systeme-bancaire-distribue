// src/pages/comptepret.js
import ComptePretService from "../service/comptepretService.js";

window.showComptePret = function() {
    document.getElementById("content").innerHTML = `
        <div class="card">
        <h2>Compte Prêt</h2>
            <div style="margin-top:30px; border-top:1px solid #ddd; padding-top:20px;">
                <h3>Ajouter un nouveau prêt</h3>

                <label for="montant">Montant :</label>
                <input type="number" id="montant" step="0.01" placeholder="Montant du prêt" />

                <label for="interet">Intérêt (%) :</label>
                <input type="number" id="interet" step="0.01" placeholder="Taux d'intérêt" />

                <label for="dateOuverture">Date d'ouverture :</label>
                <input type="date" id="dateOuverture" />

                <label for="dateFermeture">Date de fermeture :</label>
                <input type="date" id="dateFermeture" />

                <label for="typeInteretId">Type d'intérêt (ID) :</label>
                <input type="number" id="typeInteretId" placeholder="ID type d'intérêt" />

                <button style="margin-top:10px;" onclick="insertPret()">Ajouter le prêt</button>
            </div>
            
            <h3>Pret par Client</h3>
            <label for="clientId">ID Client :</label>
            <input type="number" id="clientId" placeholder="Entrez l'ID client" />
            <button onclick="loadComptePret()">Voir le solde</button>
            <div id="result"></div>
            
        </div>
    `;
}

window.loadComptePret = async function() {
    const clientId = document.getElementById("clientId").value.trim();
    const resultDiv = document.getElementById("result");

    if (!clientId) {
        alert("Veuillez entrer un ID Client.");
        return;
    }

    resultDiv.innerHTML = "⏳ Chargement...";

    try {
        const prets = await ComptePretService.getPretsByClient(clientId);
        renderPrets(prets, resultDiv);
    } catch (error) {
        resultDiv.innerHTML = "⚠️ Impossible de récupérer les prêts.";
    }
}

function renderPrets(prets, container) {
    if (!prets || prets.length === 0) {
        container.innerHTML = "💳 Aucun prêt trouvé pour ce client.";
        return;
    }

    let html = '<div style="display:flex; flex-wrap:wrap; gap:20px;">';

    prets.forEach((pret, i) => {
        html += `
            <div style="flex:1 1 calc(50% - 20px); border:1px solid #ccc; padding:15px; border-radius:8px; box-shadow:0 4px 8px rgba(0,0,0,0.1);">
                <h3>Prêt #${i + 1}</h3>
                <p><strong>Montant:</strong> ${pret.montant} Ar</p>
                <p><strong>Intérêt:</strong> ${(pret.interet * 100).toFixed(2)}%</p>
                <p><strong>Date ouverture:</strong> ${pret.dateOuverture}</p>
                <p><strong>Date fermeture:</strong> ${pret.dateFermeture}</p>

                <div style="margin-top:10px;">
                    <h4>Échéances :</h4>
                    <div style="display:grid; grid-template-columns:repeat(6,1fr); gap:10px;">
                        ${renderEcheances(pret.echeances)}
                    </div>
                </div>
            </div>
        `;
    });

    html += '</div>';
    container.innerHTML = html;
}

function renderEcheances(echeances) {
    if (!echeances || echeances.length === 0) {
        return `<div style="grid-column: span 6; text-align:center;">Aucune échéance pour ce prêt.</div>`;
    }

    return echeances.map((ech, index) => `
        <div style="padding:5px; background:#f0f4f8; border-radius:6px; border:1px solid #ddd; text-align:center;">
            <p><strong>#${index + 1}</strong></p>
            <p>M.C : ${ech.montantCapital} Ar</p>
            <p>M.I : ${ech.montantInteret} Ar</p>
            <p>Total : ${ech.montantCapital + ech.montantInteret} Ar</p>
            <p>${ech.estPayee ? "✅ Payée" : "❌ Non payée"}</p>
        </div>
    `).join('');
}


window.insertPret = async function() {
    const clientId = document.getElementById("clientId").value.trim();
    const montant = document.getElementById("montant").value;
    const interet = document.getElementById("interet").value;
    const dateOuverture = document.getElementById("dateOuverture").value;
    const dateFermeture = document.getElementById("dateFermeture").value;
    const typeInteretId = document.getElementById("typeInteretId").value;

    if (!clientId || !montant || !interet || !dateOuverture || !dateFermeture || !typeInteretId) {
        alert("Veuillez remplir tous les champs obligatoires !");
        return;
    }

    const pret = {
        client: { id: parseInt(clientId) },
        montant: parseFloat(montant),
        interet: parseFloat(interet),
        dateOuverture: dateOuverture,   
        dateFermeture: dateFermeture,   
        typeInteret: { id: parseInt(typeInteretId) }
    };
    const resultDiv = document.getElementById("result");
    resultDiv.innerHTML = "⏳ Envoi en cours...";

    try {
        const response = await ComptePretService.addPret(pret); 
        resultDiv.innerHTML = "✅ Prêt ajouté avec succès !";
        loadComptePret(); 
    } catch (err) {
        console.error(err);
        resultDiv.innerHTML = "⚠️ Erreur lors de l'ajout du prêt.";
    }
}