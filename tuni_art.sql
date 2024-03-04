-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 04, 2024 at 01:12 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";

START TRANSACTION;

SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */
;


/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */
;


/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */
;


/*!40101 SET NAMES utf8mb4 */
;

--
-- Database: `tuni'art`
--
-- --------------------------------------------------------
--
-- Table structure for table `art`
--
CREATE TABLE `art` (
    `art_ref` int(11) NOT NULL,
    `art_title` varchar(512) NOT NULL,
    `art_price` float NOT NULL,
    `type` varchar(512) NOT NULL,
    `creation` date NOT NULL,
    `description` varchar(512) NOT NULL,
    `style` varchar(512) NOT NULL,
    `artist_id` int(11) NOT NULL,
    `isAvailable` tinyint (1) DEFAULT 1) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

--
-- Dumping data for table `art`
--
INSERT INTO `art` (`art_ref`, `art_title`, `art_price`, `type`, `creation`, `description`, `style`, `artist_id`, `isAvailable`)
    VALUES (1, 'Art Title', 100, 'Oil Painting', '2024-02-14', 'Description text', 'Abstract', 1, 0),
    (2, 'monaliza', 69000, 'paint', '2024-02-07', 'monaliza', 'recangle', 1, 0),
    (3, 'Sunset Splendor', 500, 'Painting', '2024-02-19', 'A breathtaking sunset scene with vibrant colors', 'Impressionism', 1, 0),
    (7, 'Art Title 1', 100, 'Type 1', '2022-01-01', 'Description 1', 'Style 1', 1, 0),
    (8, 'Art Title 2', 200, 'Type 2', '2022-02-01', 'Description 2', 'Style 2', 1, 1),
    (9, 'Art Title 3', 300, 'Type 3', '2022-03-01', 'Description 3', 'Style 3', 1, 0),
    (10, 'Art Title 4', 400, 'Type 4', '2022-04-01', 'Description 4', 'Style 4', 1, 1),
    (11, 'Art Title 5', 500, 'Type 5', '2022-05-01', 'Description 5', 'Style 5', 1, 0);

-- --------------------------------------------------------
--
-- Table structure for table `artist`
--
CREATE TABLE `artist` (
    `aid` int(11) NOT NULL,
    `fname` varchar(128) NOT NULL,
    `lname` varchar(128) NOT NULL,
    `email` varchar(512) NOT NULL,
    `gender` tinyint (1) NOT NULL,
    `phone_nb` int(11) NOT NULL,
    `profile_pic` varchar(512) DEFAULT NULL,
    `birth_date` date NOT NULL,
    `password` varchar(512) NOT NULL,
    `biography` varchar(512) NOT NULL,
    `portfolio` varchar(512) NOT NULL,
    `verification_code` varchar(512) DEFAULT NULL) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

--
-- Dumping data for table `artist`
--
INSERT INTO `artist` (`aid`, `fname`, `lname`, `email`, `gender`, `phone_nb`, `profile_pic`, `birth_date`, `password`, `biography`, `portfolio`, `verification_code`)
    VALUES (1, 'John', 'Artist', 'john.artist@example.com', 1, 123456789, NULL, '1990-01-01', 'password', 'Biography text', 'Portfolio text', NULL);

-- --------------------------------------------------------
--
-- Table structure for table `cart`
--
CREATE TABLE `cart` (
    `cart_ref` int(11) NOT NULL,
    `uid` int(11) NOT NULL,
    `art_ref` int(11) NOT NULL) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

--
-- Dumping data for table `cart`
--
INSERT INTO `cart` (`cart_ref`, `uid`, `art_ref`)
    VALUES (55, 1, 1),
    (56, 1, 9),
    (57, 1, 7),
    (58, 1, 11),
    (59, 1, 3);

-- --------------------------------------------------------
--
-- Table structure for table `delivery`
--
CREATE TABLE `delivery` (
    `delivery_id` int(11) NOT NULL,
    `order_id` int(11) NOT NULL,
    `estimated_date` date NOT NULL,
    `delivery_fees` float NOT NULL,
    `destination` varchar(512) NOT NULL,
    `state` tinyint (1) NOT NULL,
    `agency_id` int(11) NOT NULL) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

--
-- Dumping data for table `delivery`
--
INSERT INTO `delivery` (`delivery_id`, `order_id`, `estimated_date`, `delivery_fees`, `destination`, `state`, `agency_id`)
    VALUES (1, 18, '2024-02-26', 20, 'Tunis', 1, 1),
    (2, 19, '2024-02-27', 15, 'Sousse', 0, 2),
    (3, 20, '2024-02-28', 25, 'Sfax', 0, 3),
    (4, 21, '2024-02-29', 30, 'Bizerte', 1, 4),
    (5, 22, '2024-03-01', 40, 'Gabes', 0, 5),
    (6, 23, '2024-03-02', 35, 'Kairouan', 0, 6),
    (7, 24, '2024-03-03', 50, 'Gafsa', 1, 7),
    (8, 18, '2024-03-04', 45, 'Tataouine', 0, 8),
    (9, 19, '2024-03-05', 60, 'Tozeur', 0, 9),
    (10, 20, '2024-03-06', 55, 'Kebili', 1, 10),
    (11, 18, '2024-02-26', 20, 'Tunis', 1, 1),
    (251112, 24, '2024-03-06', 900, 'Ghazela cite', 0, 1),
    (251113, 18, '2024-03-07', 999, 'jjjj', 1, 1),
    (251114, 19, '2024-03-07', 1, 'Tunis', 1, 1),
    (251116, 24, '2024-03-07', 3, 'arienaa', 1, 3),
    (251117, 24, '2024-03-07', 5, 'arienaa', 1, 5),
    (251118, 18, '2024-03-07', 2.34, 'zahrouni', 1, 2),
    (251119, 18, '2024-03-07', 2.34, 'zahrouni', 1, 2),
    (251120, 23, '2024-03-07', 10, 'JANDOUBAA', 1, 1),
    (251121, 23, '2024-03-07', 10, 'kef', 1, 7),
    (251122, 23, '2024-03-07', 10, 'beja', 0, 1),
    (251123, 24, '2024-03-07', 100, 'jamaica', 1, 8),
    (251124, 21, '2024-03-07', 250, 'jamausaica', 1, 5),
    (251125, 21, '2024-03-07', 500, 'tn', 1, 1),
    (251126, 20, '2024-03-07', 0, 'q', 1, 4),
    (251127, 20, '2024-03-07', 0, 'q', 1, 4),
    (251128, 24, '2024-03-07', 0, 'tunis', 1, 4),
    (251129, 18, '2024-03-07', 0, 'tunis', 1, 4),
    (251130, 18, '2024-03-07', 0, 'tunis', 1, 2),
    (251131, 18, '2024-03-07', 0, 'tunis', 0, 2),
    (251132, 18, '2024-03-07', 250, 'tunis', 0, 2),
    (251133, 20, '2024-03-07', 250, 'zahra', 0, 4),
    (251134, 24, '2024-03-07', 250, 'tunis', 1, 5),
    (251135, 18, '2024-03-07', 250, 'TOUNIIIIIIISSSSSS', 1, 1),
    (251136, 22, '2024-03-07', 500, 'njnnj', 0, 2),
    (251137, 20, '2024-03-07', 250, 'tunis', 1, 7),
    (251138, 18, '2024-03-07', 250, 'manouba', 1, 2),
    (251139, 18, '2024-03-07', 250, 'qqqqqqq', 1, 5);

-- --------------------------------------------------------
--
-- Table structure for table `delivery_agency`
--
CREATE TABLE `delivery_agency` (
    `agency_id` int(11) NOT NULL,
    `agency_name` varchar(512) NOT NULL,
    `agency_address` varchar(512) NOT NULL,
    `nb_deliveries` int(11) DEFAULT NULL) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

--
-- Dumping data for table `delivery_agency`
--
INSERT INTO `delivery_agency` (`agency_id`, `agency_name`, `agency_address`, `nb_deliveries`)
    VALUES (1, 'Agency 1', 'Tunis', 3),
    (2, 'Agency 2', 'UAE', 4),
    (3, 'Agency 3', 'Italy', 2),
    (4, '111', '123 Main St, Springfield, IL 62701', 11),
    (5, 'Agency 5', '123 Main St, Springfield, IL 62701', 2),
    (6, 'Agency 6', '123 Main St, Springfield, IL 62701', 1),
    (7, 'Agency 7', '123 Main St, Springfield, IL 62701', 3),
    (8, 'Agency 8', '123 Main St, Springfield, IL 62701', 2),
    (9, 'Agency 9', '123 Main St, Springfield, IL 62701', 1),
    (10, 'Agency 10', '123 Main St, Springfield, IL 62701', 3),
    (11, 'Agency 11', '123 Main St, Springfield, IL 62701', 2);

-- --------------------------------------------------------
--
-- Table structure for table `order`
--
CREATE TABLE `order` (
    `order_id` int(11) NOT NULL,
    `uid` int(11) NOT NULL,
    `order_date` date NOT NULL,
    `totalprice` float NOT NULL,
    `status` tinyint (1) NOT NULL) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

--
-- Dumping data for table `order`
--
INSERT INTO `order` (`order_id`, `uid`, `order_date`, `totalprice`, `status`)
    VALUES (18, 1, '2019-02-02', 6000, 1),
    (19, 1, '2012-01-02', 5000, 1),
    (20, 1, '2023-11-01', 7000, 0),
    (21, 1, '2024-02-26', 3500, 1),
    (22, 1, '2023-11-01', 4500, 1),
    (23, 1, '2017-08-07', 8500, 1),
    (24, 3, '2010-09-09', 9000, 1);

-- --------------------------------------------------------
--
-- Table structure for table `review`
--
CREATE TABLE `review` (
    `review_id` int(11) NOT NULL,
    `art_ref` int(11) NOT NULL,
    `uid` int(11) NOT NULL,
    `date_published` date NOT NULL,
    `rating` int(11) NOT NULL,
    `comment` varchar(512) NOT NULL) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- --------------------------------------------------------
--
-- Table structure for table `user`
--
CREATE TABLE `user` (
    `uid` int(11) NOT NULL,
    `fname` varchar(128) NOT NULL,
    `lname` varchar(128) NOT NULL,
    `email` varchar(512) NOT NULL,
    `gender` tinyint (1) NOT NULL,
    `phone_nb` int(11) NOT NULL,
    `profile_pic` varchar(512) DEFAULT NULL,
    `birth_date` date NOT NULL,
    `password` varchar(512) NOT NULL,
    `verification_code` varchar(512) DEFAULT NULL) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

--
-- Dumping data for table `user`
--
INSERT INTO `user` (`uid`, `fname`, `lname`, `email`, `gender`, `phone_nb`, `profile_pic`, `birth_date`, `password`, `verification_code`)
    VALUES (1, 'Arij', 'Mahouechi', 'arij.mahouechi@esprit.tn', 1, 22334455, NULL, '2001-10-12', '12131415', NULL),
    (2, 'John', 'Doe', 'john.doe@example.com', 1, 123456789, NULL, '1990-01-01', 'password', NULL),
    (3, 'John', 'Doe', 'john.doe@example.com', 1, 123456789, NULL, '1990-01-01', 'password123', 'verification123'),
    (4, 'John', 'Doe', 'john.doe@example.com', 1, 123456789, NULL, '1990-01-01', 'password', NULL),
    (5, 'John', 'Doe', 'john.doe@example.com', 1, 123456789, NULL, '1990-01-01', 'password', NULL),
    (6, 'John', 'Doe', 'john.doe@example.com', 1, 123456789, NULL, '1990-01-01', 'password', NULL);

--
-- Indexes for dumped tables
--
--
-- Indexes for table `art`
--
ALTER TABLE `art`
    ADD PRIMARY KEY (`art_ref`),
    ADD KEY `fk_artist_art` (`artist_id`);

--
-- Indexes for table `artist`
--
ALTER TABLE `artist`
    ADD PRIMARY KEY (`aid`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
    ADD PRIMARY KEY (`cart_ref`),
    ADD KEY `fk_user_cart` (`uid`);

--
-- Indexes for table `delivery`
--
ALTER TABLE `delivery`
    ADD PRIMARY KEY (`delivery_id`),
    ADD KEY `fk_order_delivery` (`order_id`),
    ADD KEY `fk_agency_delivery` (`agency_id`);

--
-- Indexes for table `delivery_agency`
--
ALTER TABLE `delivery_agency`
    ADD PRIMARY KEY (`agency_id`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
    ADD PRIMARY KEY (`order_id`),
    ADD KEY `fk_user` (`uid`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
    ADD PRIMARY KEY (`review_id`),
    ADD KEY `fk_art_review` (`art_ref`),
    ADD KEY `fk_user_review` (`uid`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
    ADD PRIMARY KEY (`uid`);

--
-- AUTO_INCREMENT for dumped tables
--
--
-- AUTO_INCREMENT for table `art`
--
ALTER TABLE `art` MODIFY `art_ref` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT = 12;

--
-- AUTO_INCREMENT for table `artist`
--
ALTER TABLE `artist` MODIFY `aid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT = 2;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart` MODIFY `cart_ref` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT = 60;

--
-- AUTO_INCREMENT for table `delivery`
--
ALTER TABLE `delivery` MODIFY `delivery_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT = 251140;

--
-- AUTO_INCREMENT for table `delivery_agency`
--
ALTER TABLE `delivery_agency` MODIFY `agency_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT = 12;

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order` MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT = 26;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review` MODIFY `review_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user` MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT = 8;

--
-- Constraints for dumped tables
--
--
-- Constraints for table `art`
--
ALTER TABLE `art`
    ADD CONSTRAINT `fk_artist_art` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`aid`);

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
    ADD CONSTRAINT `fk_user_cart` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`);

--
-- Constraints for table `delivery`
--
ALTER TABLE `delivery`
    ADD CONSTRAINT `fk_agency_delivery` FOREIGN KEY (`agency_id`) REFERENCES `delivery_agency` (`agency_id`),
    ADD CONSTRAINT `fk_order_delivery` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`);

--
-- Constraints for table `order`
--
ALTER TABLE `order`
    ADD CONSTRAINT `fk_user` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`);

--
-- Constraints for table `review`
--
ALTER TABLE `review`
    ADD CONSTRAINT `fk_art_review` FOREIGN KEY (`art_ref`) REFERENCES `art` (`art_ref`),
    ADD CONSTRAINT `fk_user_review` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`);

COMMIT;


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */
;


/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */
;


/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */
;

