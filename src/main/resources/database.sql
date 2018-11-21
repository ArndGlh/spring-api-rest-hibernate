-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mer. 21 nov. 2018 à 09:21
-- Version du serveur :  5.7.21
-- Version de PHP :  5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `testdb`
--

-- --------------------------------------------------------

--
-- Structure de la table `game`
--

DROP TABLE IF EXISTS `game`;
CREATE TABLE IF NOT EXISTS `game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `genre` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `game`
--

INSERT INTO `game` (`id`, `genre`, `title`, `year`) VALUES
(1, 'Action-aventure', 'Zelda : Breath of the wild', '2017'),
(2, 'Course', 'Mario Kart 8 deluxe', '2017'),
(3, 'Plateforme', 'Mario Galaxy', '2007'),
(4, 'Action-RPG', 'Dark Souls', '2011'),
(5, 'Action-RPG', 'The Witcher 3', '2015'),
(7, 'Jeu de combat', 'Super Smash Bros Melee', '2001');

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `progress`
--

DROP TABLE IF EXISTS `progress`;
CREATE TABLE IF NOT EXISTS `progress` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `task_id` int(11) NOT NULL,
  `actual_progress` int(11) NOT NULL,
  `max_progress` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKni6ct4x7y17pr3w1wdfrkogho` (`task_id`),
  KEY `FK7fyumbty8qgbd7sfbbjnqdo62` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `progress`
--

INSERT INTO `progress` (`id`, `user_id`, `task_id`, `actual_progress`, `max_progress`) VALUES
(4, 1, 4, 12, 12),
(3, 1, 3, 360, 900),
(2, 1, 2, 70, 70),
(1, 1, 1, 11, 11),
(5, 1, 7, 20, 52);

-- --------------------------------------------------------

--
-- Structure de la table `task`
--

DROP TABLE IF EXISTS `task`;
CREATE TABLE IF NOT EXISTS `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `game_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt4fvut9qol1ykh7ovpa62fkv9` (`game_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `task`
--

INSERT INTO `task` (`id`, `description`, `name`, `game_id`) VALUES
(1, 'Finir la quête principale', 'Quete principale', 1),
(2, 'Finir les quete secondaire', 'Quête secondaire', 1),
(3, 'Récolter toute les noix kogoru', 'Noix kogoru', 1),
(4, 'Finir tout les championnats en 3 étoiles', 'Coupes en or', 2),
(5, 'Finir la quête principale', 'Quête principale', 3),
(6, 'Récolter toute les étoiles', 'Etoiles', 3),
(7, 'Obtenir tout les éléments de personnalisation', 'Eléments de personnalisation', 2);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `email`, `name`, `password`) VALUES
(1, 'martin@mail.fr', 'martin', 'martin');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
