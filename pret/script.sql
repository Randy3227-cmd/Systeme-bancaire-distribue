
CREATE TABLE IF NOT EXISTS client (
    id BIGSERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS etat_pret (
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS type_interet (
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS pret (
    id BIGSERIAL PRIMARY KEY,
    montant NUMERIC(15,2) NOT NULL,
    interet NUMERIC(5,2) NOT NULL,
    date_ouverture DATE NOT NULL,
    date_fermeture DATE,
    client_id BIGINT NOT NULL,
    CONSTRAINT fk_pret_client FOREIGN KEY (client_id) REFERENCES client (id)
);

CREATE TABLE IF NOT EXISTS echeance (
    id BIGSERIAL PRIMARY KEY,
    date_paiement TIMESTAMP,
    montant_capital NUMERIC(15,2) NOT NULL,
    montant_interet NUMERIC(15,2) NOT NULL,
    est_payee BOOLEAN DEFAULT FALSE,
    pret_id BIGINT NOT NULL,
    CONSTRAINT fk_echeance_pret FOREIGN KEY (pret_id) REFERENCES pret (id)
);
