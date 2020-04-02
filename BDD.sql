
/* ------------------ BASE DE DONNEES PJS4 ------------------ */
/* -------------------- Armand Drouineau ---------------------- */

CREATE TABLE COMPTE(
   idCompte INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   typeCompte VARCHAR(8),
   pseudo VARCHAR(16),
   mail VARCHAR(32),
   mdp VARCHAR (16),
   UNIQUE KEY (mail)
);

CREATE TABLE STOCKAGE(
   idStockage INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   taille INT,
   dateCreation DATE,
   nombreElements INT,
   idCompte INT,
   FOREIGN KEY (idCompte) REFERENCES COMPTE (idCompte)
);

CREATE TABLE ENTITE(
   idEntite INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   nomEntite VARCHAR(16),
   extension VARCHAR(4),
   dateStockage DATE,
   typeEntite VARCHAR(16),
   visibilite VARCHAR(16),
   public BOOLEAN,
   tailleEntite INT NOT NULL,
   cheminFtp VARCHAR(50),
   idParent INT NOT NULL,
   idCompte INT NOT NULL,
   FOREIGN KEY (idCompte) REFERENCES COMPTE (idCompte)
);

CREATE TABLE PARTAGE(
   idCompte INT PRIMARY KEY,
   idCompte2 INT,
   idEntite INT,
   datePartage DATE,
   FOREIGN KEY (idCompte) REFERENCES COMPTE (idCompte),
   FOREIGN KEY (idCompte2) REFERENCES COMPTE (idCompte),
   FOREIGN KEY (idEntite) REFERENCES ENTITE (idEntite)
);

CREATE TABLE CONTIENT(
   idEntite INT PRIMARY KEY,
   idEntite2 INT,
   FOREIGN KEY (idEntite) REFERENCES ENTITE (idEntite),
   FOREIGN KEY (idEntite2) REFERENCES ENTITE (idEntite)
);

CREATE TABLE GROUPE(
   idGroupe INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   numGroupe INT
);

CREATE TABLE COUPLE_GROUPE_COMPTE(
   idCompte INT PRIMARY KEY,
   idGroupe INT,
   FOREIGN KEY (idCompte) REFERENCES COMPTE (idCompte),
   FOREIGN KEY (idGroupe) REFERENCES GROUPE (idGroupe)
);

/* ------------------TRIGGERS------------------ */

-- SUPPRESSION TABLE CONTIENT 
CREATE OR REPLACE TRIGGER T_DELETE_CONTIENT_COMPTE
AFTER DELETE ON ENTITE
FOR EACH ROW
DELETE FROM CONTIENT 
WHERE CONTIENT.idEntite = Old.idEntite OR CONTIENT.idEntite2 = Old.idEntite;

-- SUPPRESSION ENTITE 
CREATE OR REPLACE TRIGGER T_DELETE_ENTITE_COMPTE
AFTER DELETE ON COMPTE
FOR EACH ROW
DELETE FROM ENTITE 
WHERE ENTITE.idCompte = Old.idCompte;

-- SUPPRESSION ESPACE STOCKAGE
CREATE OR REPLACE TRIGGER T_DELETE_STOCKAGE_COMPTE
AFTER DELETE ON COMPTE
FOR EACH ROW
DELETE FROM STOCKAGE 
WHERE STOCKAGE.idCompte = Old.idCompte;

-- UPDATE NOMBRE ELEMS STOCKAGE (suppression) 
CREATE OR REPLACE TRIGGER T_UPDATE_ELEMS_STOCKAGE
AFTER DELETE ON ENTITE
FOR EACH ROW
UPDATE STOCKAGE SET nombreElements = nombreElements -1 
WHERE STOCKAGE.idCompte = Old.idCompte;

-- UPDATE NOMBRE ELEMS STOCKAGE (Ajout) 
CREATE OR REPLACE TRIGGER T_UPDATE_ELEMS_STOCKAGE_2
AFTER INSERT ON ENTITE
FOR EACH ROW
UPDATE STOCKAGE SET nombreElements = nombreElements +1 
WHERE STOCKAGE.idCompte = New.idCompte;

-- SUPPRESSION PARTAGE DOCS (cote idCompte) 
CREATE OR REPLACE TRIGGER T_DELETE_PARTAGE_DOCS
AFTER DELETE ON COMPTE
FOR EACH ROW
DELETE FROM PARTAGE
WHERE PARTAGE.idCompte = Old.idCompte OR PARTAGE.idCompte2 = Old.idCompte;

-- SUPPRESSION PARTAGE DOCS (cote idEntite)
CREATE OR REPLACE TRIGGER T_DELETE_PARTAGE_DOCS_2
AFTER DELETE ON ENTITE
FOR EACH ROW
DELETE FROM ENTITE
WHERE ENTITE.idCompte = Old.idCompte;

/* ------------------JEU DE TEST------------------ */

INSERT INTO COMPTE (typeCompte, pseudo, mail, mdp) VALUES ('client', 'kiki','a@c','1234');
INSERT INTO COMPTE (typeCompte, pseudo, mail, mdp) VALUES ('admin', 'kiki0','a@0c','123456');
INSERT INTO STOCKAGE (taille, dateCreation, nombreElements, idCompte) VALUES(500, CURDATE(), 1, 1);
INSERT INTO ENTITE (nomEntite, extension, dateStockage, typeEntite, visibilite, public, tailleEntite, idParent, idCompte) VALUES('MonRep', 'rep', CURDATE(), 'repertoire', 'non favori', 0, 50, -1, 1);
INSERT INTO PARTAGE(idCompte, idCompte2, idEntite, datePartage) VALUES(1,2,1,CURDATE());
