-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 02, 2021 at 06:44 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pharmacy`
--
CREATE DATABASE pharmacy;
USE pharmacy;
-- --------------------------------------------------------

--
-- Table structure for table `distributor_item`
--

CREATE TABLE `distributor_item` (
  `id` int(255) NOT NULL,
  `items_id` int(255) NOT NULL,
  `item_name` varchar(50) NOT NULL,
  `price` float NOT NULL,
  `description` varchar(50) NOT NULL,
  `quantity` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `distributor_item`
--

INSERT INTO `distributor_item` (`id`, `items_id`, `item_name`, `price`, `description`, `quantity`) VALUES
(18, 5, 'Hand Sanitizer', 20, 'Used to remove germs', 400),
(19, 5, 'Gloves', 10, 'Used to cover hands', 250),
(20, 5, 'Disposable Face masks', 10, 'Used to cover face', 500),
(21, 5, 'Thermometer', 100, 'Used to check temperature', 200),
(22, 5, 'First aid kit', 50, 'Used in emergency situations', 400),
(23, 5, 'Bandage Strips', 5, 'Used to cover injury', 500),
(39, 8, 'Gloves', 8, 'Used for protection', 150);

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `id` int(11) NOT NULL,
  `distributor` varchar(20) NOT NULL,
  `category` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id`, `distributor`, `category`) VALUES
(5, 'shah_distributor', 'Basic Supplies'),
(8, 'aiden_distributor', 'Basic Supplies');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `username` varchar(200) NOT NULL,
  `orderDate` varchar(200) NOT NULL,
  `total_quantity` int(11),
  `price` float,
  `address` varchar(8000) NOT NULL,
  `phone_number` varchar(10) NOT NULL,
  `distributor_name` varchar(200),
  `medicine` int,
  `message` varchar(100),
  `prescription` blob,
  `status` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `particular_order_prod`
--

CREATE TABLE `particular_order_prod` (
  `id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `item_name` varchar(200) NOT NULL,
  `price` float,
  `quantity` int(11)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(255) NOT NULL,
  `name` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phoneNumber` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  `role` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `username`, `email`, `phoneNumber`, `password`, `role`) VALUES
(2, 'Azar', 'azar_bazar', 'azar@google.com', '7458966633', 'ajarajar', 'USER'),
(5, 'Cypher ', 'cypher_admin', 'cypher@admin.com', '8547123691', ' admin_cypher', 'ADMIN'),
(8, 'Shahul Hameed', 'shah_distributor', 'shah_distributor@gmail.com', '8793014522', 'shahshah', 'DISTRIBUTOR'),
(9, 'Rajesh Khana', 'raja_distributor', 'raja@distributor.com', '7412889633', 'raja_raja', 'DISTRIBUTOR'),
(12, 'Sam Fisher', 'sam_fisher', 'samfisher@gmail.com', '7445633222', 'splintercell', 'USER'),
(17, 'Mahmood Ali', 'mah_ali', 'ali@gmail.com', '8542222233', 'boxerda_69', 'USER'),
(18, 'KDR', 'kdr_distributor', 'kdr@gmail.com', '7120458963', 'armykdr_', 'DISTRIBUTOR'),
(19, 'Sidra Mirza', 'sid_distributor', 'sid@distributor.com', '9647851222', 'mirzasid', 'DISTRIBUTOR'),
(20, 'Clara', 'admin_clara', 'clara@gmail.com', '9745863214', 'clara_admin', 'ADMIN'),
(21, 'Mohammed Azaruddin', 'admin_azar', 'azaradmin@gmail.com', '8745663332', 'azar_admin', 'ADMIN'),
(22, 'Pranav P', 'pranav_p', 'pranav_p@gmail.com', '8996547714', 'ppranav_69', 'USER'),
(23, 'Saravana', 'admin_saravana', 'saravana@hotmail.com', '7854233699', 'saravana', 'ADMIN');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `distributor_item`
--
ALTER TABLE `distributor_item`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `order_id` (`order_id`);

--
-- Indexes for table `particular_order_prod`
--
ALTER TABLE `particular_order_prod`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `id` (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `distributor_item`
--
ALTER TABLE `distributor_item`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `particular_order_prod`
--
ALTER TABLE `particular_order_prod`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `particular_order_prod`
--
ALTER TABLE `particular_order_prod`
  ADD CONSTRAINT `particular_order_prod_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
