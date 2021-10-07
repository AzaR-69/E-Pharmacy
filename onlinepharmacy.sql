-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 07, 2021 at 12:08 PM
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
-- Database: `onlinepharmacy`
--

-- --------------------------------------------------------

--
-- Table structure for table `distributor_items`
--

CREATE TABLE `distributor_items` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `item_name` varchar(20) NOT NULL,
  `price` float NOT NULL,
  `quantity` int(11) NOT NULL,
  `items_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `distributor_items`
--

INSERT INTO `distributor_items` (`id`, `description`, `item_name`, `price`, `quantity`, `items_id`) VALUES
(7, 'Used for protection', 'Hand Sanitizer', 10, 250, 2),
(13, 'Used for cold', 'Paracetomol', 1.5, 150, 3),
(11, 'Used for babies', 'Baby Health Kit', 15, 200, 2),
(12, 'Used for babies', 'Baby Oil', 12, 400, 2),
(16, 'Used for cold', 'Paracetomol X', 2, 500, 3),
(15, 'Used for cold', 'Colbol', 1.5, 500, 3),
(17, 'Used for cold', 'Vicks action 420', 3, 400, 3),
(18, 'Used for protection', 'Face Mask', 10, 500, 2),
(19, 'Used for protection', 'Hand Gloves', 15, 500, 2),
(20, 'Used for protection', 'Mouth Wash', 30, 400, 2),
(21, 'Used for cold', 'Crocine', 2, 600, 3);

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `id` int(11) NOT NULL,
  `category` varchar(20) NOT NULL,
  `distributor` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id`, `category`, `distributor`) VALUES
(2, 'Basic Supplies', 'shah_distributor'),
(3, 'Medications', 'sid_distributor');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `distributor_name` varchar(255) NOT NULL,
  `order_date` varchar(255) NOT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  `price` float NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_quantity` int(11) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `particular_order`
--

CREATE TABLE `particular_order` (
  `id` int(11) NOT NULL,
  `item_name` varchar(255) NOT NULL,
  `price` float NOT NULL,
  `quantity` int(11) NOT NULL,
  `order_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `particular_product_order`
--

CREATE TABLE `particular_product_order` (
  `id` int(11) NOT NULL,
  `item_name` varchar(255) NOT NULL,
  `price` float NOT NULL,
  `quantity` int(11) NOT NULL,
  `order_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `spring_session`
--

CREATE TABLE `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `spring_session`
--

INSERT INTO `spring_session` (`PRIMARY_ID`, `SESSION_ID`, `CREATION_TIME`, `LAST_ACCESS_TIME`, `MAX_INACTIVE_INTERVAL`, `EXPIRY_TIME`, `PRINCIPAL_NAME`) VALUES
('97080039-6620-49ef-81af-b4162a843b42', 'cf94ac9d-b627-4f36-ab6e-b2f3051ce168', 1633597838248, 1633597838307, 1800, 1633599638307, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `spring_session_attributes`
--

CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  `username` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `name`, `password`, `phone_number`, `role`, `username`) VALUES
(6, 'shah_distributor@gmail.com', 'Shahul Hameed', '$2a$10$0oTzY7fcPgFWpdJ8YqJiWu8U0TA/FNSMmwJIQx50LuuRTCtZMTTXy', '8745963221', 'DISTRIBUTOR', 'shah_distributor'),
(4, 'cypher@admin.com', 'Cypher Raja', '$2a$10$53o06LSF5IN9ZULdeBuNF.Ox/QsR6kX2u9l/r61.0ZFBnduzeNvdW', '8745963215', 'ADMIN', 'cypher_admin'),
(10, 'azar@gmail.com', 'Azaruddin', '$2a$10$Z85qhIj9ReIVu3N/bMChpOJz9FIh6SYIaMLuSUG/IogfvxlOd.NBm', '8741236995', 'USER', 'azar_bazar'),
(9, 'sid_distributor@gmail.com', 'Sidra Mirza', '$2a$10$VdlWbLfkradM4OMjJ9KLF.CPaoovP580r8mr5ljVyInc/ETDjtbt6', '7125489630', 'DISTRIBUTOR', 'sid_distributor');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `distributor_items`
--
ALTER TABLE `distributor_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6jdnxeehmx32on9kyt0wwyxqy` (`items_id`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `particular_order`
--
ALTER TABLE `particular_order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKitknjmkrlpeui2hseqxt1ppr` (`order_id`);

--
-- Indexes for table `particular_product_order`
--
ALTER TABLE `particular_product_order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjkrihd27w9qftihhw8p2r919h` (`order_id`);

--
-- Indexes for table `spring_session`
--
ALTER TABLE `spring_session`
  ADD PRIMARY KEY (`PRIMARY_ID`),
  ADD UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  ADD KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  ADD KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`);

--
-- Indexes for table `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
  ADD PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `distributor_items`
--
ALTER TABLE `distributor_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `particular_order`
--
ALTER TABLE `particular_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `particular_product_order`
--
ALTER TABLE `particular_product_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
  ADD CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
