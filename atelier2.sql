-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 02 juin 2022 à 08:05
-- Version du serveur :  8.0.21
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `atelier2`
--

-- --------------------------------------------------------

--
-- Structure de la table `absence`
--

DROP TABLE IF EXISTS `absence`;
CREATE TABLE IF NOT EXISTS `absence` (
  `datedebut` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `datefin` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idpersonnelle` int NOT NULL,
  `idmotif` int NOT NULL,
  KEY `idpersonnel` (`idpersonnelle`),
  KEY `idmotif` (`idmotif`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `absence`
--

INSERT INTO `absence` (`datedebut`, `datefin`, `idpersonnelle`, `idmotif`) VALUES
('2022-06-02 08:04:44', '2022-06-02 08:04:44', 3, 2),
('2022-06-02 08:04:54', '2022-06-02 08:04:54', 4, 4),
('2022-06-02 08:05:03', '2022-06-02 08:05:03', 2, 1),
('2022-06-02 08:05:11', '2022-06-02 08:05:11', 5, 4),
('2022-06-02 08:05:22', '2022-06-02 08:05:22', 4, 3),
('2022-06-02 08:05:31', '2022-06-02 08:05:31', 1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `motif`
--

DROP TABLE IF EXISTS `motif`;
CREATE TABLE IF NOT EXISTS `motif` (
  `idmotif` int NOT NULL AUTO_INCREMENT,
  `libelle` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  PRIMARY KEY (`idmotif`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `motif`
--

INSERT INTO `motif` (`idmotif`, `libelle`) VALUES
(1, 'vacances'),
(2, 'maladie'),
(3, 'motif familial'),
(4, 'congé parental');

-- --------------------------------------------------------

--
-- Structure de la table `personnel`
--

DROP TABLE IF EXISTS `personnel`;
CREATE TABLE IF NOT EXISTS `personnel` (
  `idpersonnelle` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `prenom` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tel` varchar(35) NOT NULL,
  `mail` varchar(128) NOT NULL,
  `idservice` int NOT NULL,
  PRIMARY KEY (`idpersonnelle`),
  KEY `idservice` (`idservice`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `personnel`
--

INSERT INTO `personnel` (`idpersonnelle`, `nom`, `prenom`, `tel`, `mail`, `idservice`) VALUES
(1, 'Boyer', 'Tiger', '(573) 811-3252', 'sit.amet@aol.net', 1),
(2, 'Dunn', ' Olivia\r\n', '(825) 718-5897', 'justo.nec.ante@google.com', 2),
(3, 'Hobbs', 'Quentin', '1-856-938-8234', 'rutrum.non.hendrerit@google.ca', 1),
(4, 'Winters', 'Forrest', '(741) 288-9711', 'gravida.sit.amet@google.couk', 3),
(5, 'Calhoun', 'Thomas', '1-624-855-8871', 'amet@outlook.net', 3);

-- --------------------------------------------------------

--
-- Structure de la table `responsable`
--

DROP TABLE IF EXISTS `responsable`;
CREATE TABLE IF NOT EXISTS `responsable` (
  `login` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pwd` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `responsable`
--

INSERT INTO `responsable` (`login`, `pwd`) VALUES
('administrateur', '513cfa4a02a04b721f3f234791b9b4d5eb1df728a59c7b17a44b89050a38a86b');

-- --------------------------------------------------------

--
-- Structure de la table `service`
--

DROP TABLE IF EXISTS `service`;
CREATE TABLE IF NOT EXISTS `service` (
  `idservice` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`idservice`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `service`
--

INSERT INTO `service` (`idservice`, `nom`) VALUES
(1, 'administratif'),
(2, 'médiation culturelle'),
(3, 'prêt');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `absence`
--
ALTER TABLE `absence`
  ADD CONSTRAINT `absence_ibfk_2` FOREIGN KEY (`idmotif`) REFERENCES `motif` (`idmotif`) ON DELETE CASCADE,
  ADD CONSTRAINT `absence_ibfk_3` FOREIGN KEY (`idpersonnelle`) REFERENCES `personnel` (`idpersonnelle`) ON DELETE CASCADE;

--
-- Contraintes pour la table `personnel`
--
ALTER TABLE `personnel`
  ADD CONSTRAINT `personnel_ibfk_1` FOREIGN KEY (`idservice`) REFERENCES `service` (`idservice`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
