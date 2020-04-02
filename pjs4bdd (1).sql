-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 02 avr. 2020 à 15:33
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `pjs4bdd`
--

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

DROP TABLE IF EXISTS `compte`;
CREATE TABLE IF NOT EXISTS `compte` (
  `idCompte` int(11) NOT NULL AUTO_INCREMENT,
  `typeCompte` varchar(8) DEFAULT NULL,
  `pseudo` varchar(16) DEFAULT NULL,
  `mail` varchar(32) DEFAULT NULL,
  `mdp` varchar(16) DEFAULT NULL,
  `idStockage` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCompte`),
  UNIQUE KEY `U_MAILCOMPTE` (`mail`),
  KEY `FK_COMPTE` (`idStockage`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`idCompte`, `typeCompte`, `pseudo`, `mail`, `mdp`, `idStockage`) VALUES
(1, 'client', 'Leroy', 'a@c', '1234', 3),
(2, 'admin', 'Drouineau', 'a@0c', '123456', 2),
(10, 'client', 'Bouthegourd', 'cl@gm.com', 'CB2910', 1);

-- --------------------------------------------------------

--
-- Structure de la table `contient`
--

DROP TABLE IF EXISTS `contient`;
CREATE TABLE IF NOT EXISTS `contient` (
  `idEntite` int(11) NOT NULL,
  `idEntite2` int(11) NOT NULL,
  PRIMARY KEY (`idEntite`,`idEntite2`),
  KEY `FK_CONTIENT_ENTITE2` (`idEntite2`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `contient`
--

INSERT INTO `contient` (`idEntite`, `idEntite2`) VALUES
(3, 4);

-- --------------------------------------------------------

--
-- Structure de la table `couple_groupe_compte`
--

DROP TABLE IF EXISTS `couple_groupe_compte`;
CREATE TABLE IF NOT EXISTS `couple_groupe_compte` (
  `idCompte` int(11) DEFAULT NULL,
  `idGroupe` int(11) DEFAULT NULL,
  KEY `FK_idCompte` (`idCompte`),
  KEY `FK_idGroupe` (`idGroupe`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `entite`
--

DROP TABLE IF EXISTS `entite`;
CREATE TABLE IF NOT EXISTS `entite` (
  `idEntite` int(11) NOT NULL,
  `nomEntite` varchar(50) DEFAULT NULL,
  `extension` varchar(4) DEFAULT NULL,
  `dateStockage` date DEFAULT NULL,
  `typeEntite` varchar(16) DEFAULT NULL,
  `visibilite` varchar(16) DEFAULT NULL,
  `public` tinyint(1) DEFAULT NULL,
  `tailleEntite` int(11) DEFAULT NULL,
  `idCompte` int(11) NOT NULL,
  `cheminFTP` varchar(100) NOT NULL,
  `IdDossierParent` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEntite`),
  KEY `FK_ENTITE` (`idCompte`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `entite`
--

INSERT INTO `entite` (`idEntite`, `nomEntite`, `extension`, `dateStockage`, `typeEntite`, `visibilite`, `public`, `tailleEntite`, `idCompte`, `cheminFTP`, `IdDossierParent`) VALUES
(1, 'testB1.txt', 'txt', '2020-04-01', 'fichier', 'favoris', 1, NULL, 10, '/classes/Bouthegourd/testB1.txt', -1),
(2, 'testB2.txt', 'txt', '2020-04-01', 'favoris', 'public', 1, NULL, 10, '/classes/Bouthegourd/testB2.txt', -1),
(3, 'DossierB1', NULL, '2020-04-02', 'repertoire', 'favoris', 1, NULL, 10, '/classes/Bouthegourd/DossierB1/', -1),
(4, 'test1DossierB1.txt', 'txt', '2020-04-02', 'fichier', 'favoris', 1, NULL, 10, '/classes/Bouthegourd/DossierB1/test1DossierB1.txt', 3);

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

DROP TABLE IF EXISTS `groupe`;
CREATE TABLE IF NOT EXISTS `groupe` (
  `idGroupe` int(11) NOT NULL,
  `numGroupe` int(11) DEFAULT NULL,
  PRIMARY KEY (`idGroupe`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `partage`
--

DROP TABLE IF EXISTS `partage`;
CREATE TABLE IF NOT EXISTS `partage` (
  `idCompte` int(11) NOT NULL,
  `idCompte2` int(11) NOT NULL,
  `idEntite` int(11) NOT NULL,
  `datePartage` date DEFAULT NULL,
  PRIMARY KEY (`idCompte`,`idCompte2`,`idEntite`),
  KEY `FK_PARTAGE_COMPTE` (`idCompte2`),
  KEY `FK_PARTAGE_ENTITE` (`idEntite`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `stockage`
--

DROP TABLE IF EXISTS `stockage`;
CREATE TABLE IF NOT EXISTS `stockage` (
  `idStockage` int(11) NOT NULL AUTO_INCREMENT,
  `idCompte` int(11) DEFAULT NULL,
  `taille` int(11) DEFAULT NULL,
  `dateCreation` date DEFAULT NULL,
  `nombreElements` int(11) DEFAULT NULL,
  PRIMARY KEY (`idStockage`),
  KEY `FK_COMPTE_STOCKAGE` (`idCompte`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `stockage`
--

INSERT INTO `stockage` (`idStockage`, `idCompte`, `taille`, `dateCreation`, `nombreElements`) VALUES
(1, 10, 500, '2020-04-01', 0),
(2, 2, 500, '2020-04-01', 0),
(3, 1, 500, '2020-04-01', 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
