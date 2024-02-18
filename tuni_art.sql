-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 14, 2024 at 05:12 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10
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
    `artist_id` int(11) NOT NULL) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

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
    `verification_code` varchar(512) DEFAULT NULL) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- --------------------------------------------------------
--
-- Table structure for table `auction`
--
CREATE TABLE `auction` (
    `auction_ref` int(11) NOT NULL,
    `auction_name` varchar(512) NOT NULL,
    `start_date` date NOT NULL,
    `end_date` date NOT NULL,
    `threshold` float NOT NULL,
    `aid` int(11) NOT NULL) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- --------------------------------------------------------
--
-- Table structure for table `cart`
--
CREATE TABLE `cart` (
    `cart_ref` int(11) NOT NULL,
    `totalPrice` float NOT NULL,
    `uid` int(11) NOT NULL,
    `state` varchar(512) NOT NULL) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- --------------------------------------------------------
--
-- Table structure for table `cart_art`
--
CREATE TABLE `cart_art` (
    `cart_ref` int(11) NOT NULL,
    `art_ref` int(11) NOT NULL,
    `quantity` int(11) NOT NULL) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

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
    `agency_id` int(11) NOT NULL) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- --------------------------------------------------------
--
-- Table structure for table `delivery_agency`
--
CREATE TABLE `delivery_agency` (
    `agency_id` int(11) NOT NULL,
    `agency_name` varchar(512) NOT NULL,
    `agency_address` varchar(512) NOT NULL,
    `nb_deliveries` int(11) DEFAULT NULL) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- --------------------------------------------------------
--
-- Table structure for table `event`
--
CREATE TABLE `event` (
    `event_id` int(11) NOT NULL,
    `event_title` varchar(512) NOT NULL,
    `category` varchar(512) NOT NULL,
    `event_date` date NOT NULL,
    `duration` int(11) NOT NULL,
    `aid` int(11) NOT NULL) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- --------------------------------------------------------
--
-- Table structure for table `order`
--
CREATE TABLE `order` (
    `order_id` int(11) NOT NULL,
    `order_date` date NOT NULL,
    `paymentMethod` varchar(512) NOT NULL,
    `receiptionMethod` varchar(512) NOT NULL,
    `cart_ref` int(11) NOT NULL) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

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
    `comment` varchar(512) NOT NULL) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

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
    `verification_code` varchar(512) DEFAULT NULL) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

--
-- Dumping data for table `user`
--
INSERT INTO `user` (`uid`, `fname`, `lname`, `email`, `gender`, `phone_nb`, `profile_pic`, `birth_date`, `password`, `verification_code`)
    VALUES (2, 'Arij', 'Mahouechi', 'arij.mahouechi@esprit.tn', 1, 22334455, NULL, '2001-10-12', '12131415', NULL);

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
-- Indexes for table `auction`
--
ALTER TABLE `auction`
    ADD PRIMARY KEY (`auction_ref`),
    ADD KEY `fk_artist_auction` (`aid`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
    ADD PRIMARY KEY (`cart_ref`),
    ADD KEY `fk_user_cart` (`uid`);

--
-- Indexes for table `cart_art`
--
ALTER TABLE `cart_art`
    ADD PRIMARY KEY (`cart_ref`, `art_ref`),
    ADD KEY `fk_art_cartart` (`art_ref`);

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
-- Indexes for table `event`
--
ALTER TABLE `event`
    ADD PRIMARY KEY (`event_id`),
    ADD KEY `fk_artist_event` (`aid`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
    ADD PRIMARY KEY (`order_id`),
    ADD KEY `fk_cart_order` (`cart_ref`);

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
ALTER TABLE `art` MODIFY `art_ref` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `artist`
--
ALTER TABLE `artist` MODIFY `aid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `auction`
--
ALTER TABLE `auction` MODIFY `auction_ref` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart` MODIFY `cart_ref` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `delivery`
--
ALTER TABLE `delivery` MODIFY `delivery_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `delivery_agency`
--
ALTER TABLE `delivery_agency` MODIFY `agency_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `event`
--
ALTER TABLE `event` MODIFY `event_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order` MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review` MODIFY `review_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user` MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT = 3;

--
-- Constraints for dumped tables
--
--
-- Constraints for table `art`
--
ALTER TABLE `art`
    ADD CONSTRAINT `fk_artist_art` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`aid`);

--
-- Constraints for table `auction`
--
ALTER TABLE `auction`
    ADD CONSTRAINT `fk_artist_auction` FOREIGN KEY (`aid`) REFERENCES `artist` (`aid`);

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
    ADD CONSTRAINT `fk_user_cart` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`);

--
-- Constraints for table `cart_art`
--
ALTER TABLE `cart_art`
    ADD CONSTRAINT `fk_art_cartart` FOREIGN KEY (`art_ref`) REFERENCES `art` (`art_ref`),
    ADD CONSTRAINT `fk_cart_cartart` FOREIGN KEY (`cart_ref`) REFERENCES `cart` (`cart_ref`);

--
-- Constraints for table `delivery`
--
ALTER TABLE `delivery`
    ADD CONSTRAINT `fk_agency_delivery` FOREIGN KEY (`agency_id`) REFERENCES `delivery_agency` (`agency_id`),
    ADD CONSTRAINT `fk_order_delivery` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`);

--
-- Constraints for table `event`
--
ALTER TABLE `event`
    ADD CONSTRAINT `fk_artist_event` FOREIGN KEY (`aid`) REFERENCES `artist` (`aid`);

--
-- Constraints for table `order`
--
ALTER TABLE `order`
    ADD CONSTRAINT `fk_cart_order` FOREIGN KEY (`cart_ref`) REFERENCES `cart` (`cart_ref`);

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

