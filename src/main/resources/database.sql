-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 04 jan. 2019 à 15:33
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

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

--
-- Déchargement des données de la table `progress`
--

INSERT INTO `progress` (`id`, `actual_progress`, `max_progress`, `task_id`, `user_id`) VALUES
(4, 12, 12, 4, 1),
(3, 360, 900, 3, 1),
(2, 70, 70, 2, 1),
(1, 11, 11, 1, 1),
(5, 20, 52, 7, 1);

--
-- Déchargement des données de la table `task`
--

INSERT INTO `task` (`id`, `description`, `name`, `game_id`, `max_progess`) VALUES
(1, 'Finir la quête principale', 'Quete principale', 1, 11),
(2, 'Finir les quete secondaire', 'Quête secondaire', 1, 70),
(3, 'Récolter toute les noix kogoru', 'Noix kogoru', 1, 900),
(4, 'Finir tout les championnats en 3 étoiles', 'Coupes en or', 2, 12),
(5, 'Finir la quête principale', 'Quête principale', 3, 10),
(6, 'Récolter toute les étoiles', 'Etoiles', 3, 121),
(7, 'Obtenir tout les éléments de personnalisation', 'Eléments de personnalisation', 2, 52);

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `email`, `name`, `password`) VALUES
(1, 'martin@mail.fr', 'martin', 'martin');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
