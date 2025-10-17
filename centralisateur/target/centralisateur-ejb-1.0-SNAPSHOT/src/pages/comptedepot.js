import CompteDepotService from "../service/comptedepotService.js";

window.showCompteDepot = function () {
  document.getElementById("content").innerHTML = `
    <div class="card">
      <h2>Compte Dépôt</h2>
 
      <div style="margin-top:30px; border-top:1px solid #ddd; padding-top:20px;">
        <h3>Ouvrir un compte dépôt</h3>

        <label for="numeroDepot">Numéro de compte :</label>
        <input type="text" id="numeroDepot" placeholder="Numéro de compte" />

        <label for="solde">Montant :</label>
        <input type="number" id="solde" step="0.01" placeholder="Montant du dépôt" />

        <label for="taux">Intérêt (%) :</label>
        <input type="number" id="taux" step="0.01" placeholder="Taux d'intérêt" />

        <label for="dateOuverture">Date d'ouverture :</label>
        <input type="datetime-local" id="dateOuverture" />

        <label for="dateEcheance">Date d'échéance :</label>
        <input type="datetime-local" id="dateEcheance" />

        <label for="clientId">ID Client :</label>
        <input type="number" id="clientId" placeholder="Entrez l'ID du client" />

        <button style="margin-top:10px;" onclick="insertDepot()">Ajouter dépôt</button>
      </div>

      <div style="margin-top:30px; border-top:1px solid #ddd; padding-top:20px;">
        <h3>Consulter un dépôt par numéro de compte</h3>
        <label for="numeroRecherche">Numéro de Compte :</label>
        <input type="text" id="numeroRecherche" placeholder="ex: CD-001" />
        <button onclick="loadDepotByNumero()">Voir le solde</button>
      </div>

      <div style="margin-top:20px;">
        <h3>Consulter les dépôts par client</h3>
        <label for="clientIdRecherche">ID Client :</label>
        <input type="number" id="clientIdRecherche" placeholder="Entrez l'ID client" />
        <button onclick="loadDepotByClient()">Voir le solde</button>
      </div>

      <div id="result" style="margin-top:20px; font-weight:bold;"></div>
    </div>
  `;
};

window.loadDepotByNumero = async function () {
  const numero = document.getElementById("numeroRecherche").value.trim();
  const result = document.getElementById("result");
  if (!numero) return alert("Veuillez entrer un numéro.");
  result.innerHTML = "⏳ Chargement...";
  try {
    const solde = await CompteDepotService.getSoldeByNumero(numero);
    result.innerText = `💰 Solde sur le compte ${numero} : ${solde} Ar`;
  } catch {
    result.innerText = "⚠️ Erreur lors du chargement.";
  }
};

window.loadDepotByClient = async function () {
  const clientId = document.getElementById("clientIdRecherche").value.trim();
  const result = document.getElementById("result");
  if (!clientId) return alert("Veuillez entrer un ID client.");
  result.innerHTML = "⏳ Chargement...";
  try {
    const solde = await CompteDepotService.getSoldeByClient(clientId);
    result.innerText = `💰 Solde total du client ${clientId} : ${solde} Ar`;
  } catch {
    result.innerText = "⚠️ Erreur lors du chargement.";
  }
};

window.insertDepot = async function () {
  const clientId = document.getElementById("clientId").value.trim();
  const numero = document.getElementById("numeroDepot").value.trim();
  const solde = document.getElementById("solde").value.trim();
  const taux = document.getElementById("taux").value.trim();
  const dateOuvertureInput = document.getElementById("dateOuverture").value;
  const dateEcheanceInput = document.getElementById("dateEcheance").value;

  if (!clientId || !numero || !solde || !taux || !dateOuvertureInput || !dateEcheanceInput) {
    alert("⚠️ Veuillez remplir tous les champs obligatoires !");
    return;
  }

  const dateOuverture = new Date(dateOuvertureInput).toISOString(); // déjà UTC
  const dateEcheance = new Date(dateEcheanceInput).toISOString();   // déjà UTC


  const depot = {
    numero: numero,
    solde: parseFloat(solde),
    taux: parseFloat(taux),
    dateOuverture: dateOuverture,
    dateEcheance: dateEcheance,
    statusId: 1,
    clientId: parseInt(clientId)
  };

  console.log("📦 Données envoyées :", JSON.stringify(depot, null, 2));

  const resultDiv = document.getElementById("result");
  resultDiv.innerHTML = "⏳ Envoi en cours...";

  try {
    const response = await CompteDepotService.addDepot(depot);
  
    const data = response; 
    console.log(data);
    resultDiv.innerHTML = "✅ Dépôt ajouté avec succès !";
  
  } catch (err) {
    console.error("❌ Exception JS :", err);
    resultDiv.innerHTML = "⚠️ Erreur lors de l'ajout du dépôt.";
  }
  
};


