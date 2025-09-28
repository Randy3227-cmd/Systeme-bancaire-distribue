CREATE TABLE Client(
   id SERIAL PRIMARY KEY,
   nom VARCHAR(50) NOT NULL
);

CREATE TABLE Status(
   id SERIAL PRIMARY KEY,
   description VARCHAR(50) NOT NULL
);

CREATE TABLE TypeOperationCompteCourant(
   id SERIAL PRIMARY KEY,
   description VARCHAR(50) NOT NULL
);

CREATE TABLE EtatPret(
   id SERIAL PRIMARY KEY,
   description VARCHAR(50)
);

CREATE TABLE CompteCourant(
   id SERIAL PRIMARY KEY,
   numero VARCHAR(50) NOT NULL UNIQUE,
   solde NUMERIC(15,2),
   taxes NUMERIC(15,2),
   status_id INTEGER NOT NULL,
   client_id INTEGER NOT NULL,
   FOREIGN KEY(status_id) REFERENCES Status(id),
   FOREIGN KEY(client_id) REFERENCES Client(id)
);

CREATE TABLE OperationCompteCourant(
   id SERIAL PRIMARY KEY,
   montant NUMERIC(15,2),
   dateInsertion TIMESTAMP NOT NULL,
   type_operation_id INTEGER NOT NULL,
   compte_courant_id INTEGER NOT NULL,
   client_id INTEGER NOT NULL,
   FOREIGN KEY(type_operation_id) REFERENCES TypeOperationCompteCourant(id),
   FOREIGN KEY(compte_courant_id) REFERENCES CompteCourant(id),
   FOREIGN KEY(client_id) REFERENCES Client(id)
);

CREATE TABLE CompteDepot(
   id SERIAL PRIMARY KEY,
   numero VARCHAR(50) NOT NULL UNIQUE,
   solde NUMERIC(15,2),
   taux NUMERIC(15,2) NOT NULL,
   dateOuverture TIMESTAMP NOT NULL,
   dateEcheance TIMESTAMP NOT NULL,
   status_id INTEGER NOT NULL,
   client_id INTEGER NOT NULL,
   FOREIGN KEY(status_id) REFERENCES Status(id),
   FOREIGN KEY(client_id) REFERENCES Client(id)
);

CREATE TABLE Pret(
   id SERIAL PRIMARY KEY,
   montant NUMERIC(15,2),
   interet NUMERIC(15,2),
   dateFermeture DATE,
   dateOuverture DATE,
   etat_id INTEGER NOT NULL,
   client_id INTEGER NOT NULL,
   FOREIGN KEY(etat_id) REFERENCES EtatPret(id),
   FOREIGN KEY(client_id) REFERENCES Client(id)
);

CREATE TABLE Echeance(
   id SERIAL PRIMARY KEY,
   datePaiement TIMESTAMP NOT NULL,
   montantCapital NUMERIC(15,2),
   montantInteret NUMERIC(15,2),
   estPayee BOOLEAN,
   pret_id INTEGER NOT NULL,
   FOREIGN KEY(pret_id) REFERENCES Pret(id)
);