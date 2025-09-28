-- ----------------------------
-- Table Status
-- ----------------------------
INSERT INTO Status (id, description) VALUES
(1, 'Actif'),
(2, 'Bloqué'),
(3, 'Fermé');

-- ----------------------------
-- Table Client
-- ----------------------------
INSERT INTO Client (id, nom) VALUES
(1, 'RABEARISOA'),
(2, 'RANDRIANARISOA'),
(3, 'ANDRIANASOLO');

-- ----------------------------
-- Table TypeOperationCompteCourant
-- ----------------------------
INSERT INTO Type_Operation_Compte_Courant (id, description) VALUES
(1, 'ENTREE'),
(2, 'SORTIE');

-- ----------------------------
-- Table CompteCourant
-- ----------------------------
INSERT INTO Compte_Courant ( numero, solde, taxe, status_id, client_id) VALUES
('CC-1004', 1500.00, 5.00, 1, 1);

-- ----------------------------
-- Table OperationCompteCourant
-- ----------------------------
INSERT INTO Operation_Compte_Courant (id, montant, date_Insertion, type_operation_id, compte_courant_id, client_id) VALUES
(1, 500.00, '2025-09-25 10:00:00', 1, 1, 1),
(2, 200.00, '2025-09-25 11:00:00', 2, 1, 1),
(3, 1000.00, '2025-09-24 15:30:00', 1, 2, 2),
(4, 300.00, '2025-09-25 09:15:00', 2, 2, 2),
(5, 150.00, '2025-09-23 14:00:00', 1, 3, 3);
