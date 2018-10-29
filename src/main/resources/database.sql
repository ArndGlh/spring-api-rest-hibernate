-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 29 oct. 2018 à 10:43
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
  `completion` double NOT NULL,
  `game_id` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqcjaw15mhrccxnip4h6yt7yfl` (`game_id`),
  KEY `FK7fyumbty8qgbd7sfbbjnqdo62` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `progress`
--

INSERT INTO `progress` (`id`, `completion`, `game_id`, `user_id`) VALUES
(1, 80, 1, 1),
(2, 40, 2, 2),
(3, 100, 3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `progress_task`
--

DROP TABLE IF EXISTS `progress_task`;
CREATE TABLE IF NOT EXISTS `progress_task` (
  `progress_id` int(11) NOT NULL,
  `taskList_id` int(11) NOT NULL,
  UNIQUE KEY `UK_7ekc6yvvf8iiiq7qoo9q78kqa` (`taskList_id`),
  KEY `FKqsxe2v0a747o7d2258x2hku7x` (`progress_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `progress_task`
--

INSERT INTO `progress_task` (`progress_id`, `taskList_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 4),
(3, 5),
(3, 6),
(2, 7);

-- --------------------------------------------------------

--
-- Structure de la table `task`
--

DROP TABLE IF EXISTS `task`;
CREATE TABLE IF NOT EXISTS `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `actual_progress` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `max_progress` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `progress_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqnwncr3ta48j7nhci0ags92rx` (`progress_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `task`
--

INSERT INTO `task` (`id`, `actual_progress`, `description`, `max_progress`, `name`, `progress_id`) VALUES
(1, 100, 'Finir la quête principale', 100, 'Quete principale', 1),
(2, 100, 'Finir les quete secondaire', 100, 'Quête secondaire', 1),
(3, 360, 'Récolter toute les noix kogoru', 900, 'Noix kogoru', 1),
(4, 40, 'Finir tout les championnats en 3 étoiles', 100, 'Coupes en or', 2),
(5, 100, 'Finir la quête principale', 100, 'Quête principale', 3),
(6, 100, 'Récolter toute les étoiles', 100, 'Etoiles', 3),
(7, 40, 'Obtenir tout les éléments de personnalisation', 100, 'Eléments de personnalisation', 2);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `email`, `name`, `password`) VALUES
(1, 'maill@mail.fr', 'test', ''),
(2, 'martin@mail.fr', 'martin', 'martin');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
