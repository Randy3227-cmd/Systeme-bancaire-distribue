-- ===========================
-- TABLE TITULAIRE (client)
-- ===========================
CREATE TABLE titulaire (
    id SERIAL PRIMARY KEY,
    last_name VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- ===========================
-- TABLE COMPTE COURANT
-- ===========================
CREATE TABLE compte_courant (
    id SERIAL PRIMARY KEY,
    id_titulaire INT NOT NULL REFERENCES titulaire(id) ON DELETE CASCADE,
    solde NUMERIC(15,2) NOT NULL DEFAULT 0.00
);

-- ===========================
-- TABLE TYPE D’OPÉRATION
-- (ex : DEPOT, RETRAIT, INTERET)
-- ===========================
CREATE TABLE operation_type (
    id SERIAL PRIMARY KEY,
    description VARCHAR(50) UNIQUE NOT NULL
);

-- ===========================
-- TABLE OPÉRATIONS COMPTE COURANT
-- ===========================
CREATE TABLE operation_compte (
    id SERIAL PRIMARY KEY,
    id_current_account INT NOT NULL REFERENCES compte_courant(id) ON DELETE CASCADE,
    id_operation_type INT NOT NULL REFERENCES operation_type(id),
    montant NUMERIC(15,2) NOT NULL,
    date_operation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ===========================
-- TABLE COMPTE DE DÉPÔT (épargne)
-- ===========================
CREATE TABLE compte_depot (
    id SERIAL PRIMARY KEY,
    id_titulaire INT NOT NULL REFERENCES titulaire(id) ON DELETE CASCADE,
    solde NUMERIC(15,2) NOT NULL DEFAULT 0.00,
    taux_interet NUMERIC(5,2) NOT NULL, -- ex: 2.50 = 2,5 %
    open_date DATE NOT NULL DEFAULT CURRENT_DATE
);

-- ===========================
-- TABLE OPÉRATIONS COMPTE DÉPÔT
-- ===========================
CREATE TABLE operation_depot (
    id SERIAL PRIMARY KEY,
    id_compte INT NOT NULL REFERENCES compte_depot(id) ON DELETE CASCADE,
    id_operation_type INT NOT NULL REFERENCES operation_type(id),
    montant NUMERIC(15,2) NOT NULL,
    date_operation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ===========================
-- TABLE PRET (crédit)
-- ===========================
CREATE TABLE pret (
    id SERIAL PRIMARY KEY,
    id_titulaire INT NOT NULL REFERENCES titulaire(id) ON DELETE CASCADE,
    montant_initial NUMERIC(15,2) NOT NULL,
    montant_restant NUMERIC(15,2) NOT NULL,
    taux_interet NUMERIC(5,2) NOT NULL,  -- ex: 8.00 = 8 %
    duree_mois INT NOT NULL,             -- durée du prêt en mois
    date_debut DATE NOT NULL DEFAULT CURRENT_DATE
);

-- ===========================
-- TABLE ECHEANCE (remboursement mensuel)
-- ===========================
CREATE TABLE echeance (
    id SERIAL PRIMARY KEY,
    id_pret INT NOT NULL REFERENCES pret(id) ON DELETE CASCADE,
    montant_echeance NUMERIC(15,2) NOT NULL,
    date_echeance DATE NOT NULL,
    payee BOOLEAN DEFAULT FALSE
);

-- ===========================
-- INSERTIONS DE BASE
-- ===========================
-- Types d’opérations
INSERT INTO operation_type (description) VALUES
('DEPOT'),
('RETRAIT'),
('INTERET');
