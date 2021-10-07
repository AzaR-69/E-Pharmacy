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
(27, 6, 'Pepsodent Toothpaste', 50, 'Used to whiten teeth', 400),
(28, 6, 'Baby Oil', 25, 'Used for babies', 482),
(29, 6, 'Baby Powder', 20, 'Used for babies', 85),
(30, 6, 'Baby Health Kit', 150, 'Used for babies', 592),
(31, 6, 'MamaEarth Sampoo', 200, 'Hairfall shampoo', 95),
(32, 7, 'Paracetomol', 1.5, 'Used for cold and fever', 100),
(33, 7, 'Vicks 500', 2, 'Used for cold', 90),
(34, 7, 'Crocine', 2, 'Used for cold and fever', 90),
(36, 7, 'Paracetomol X', 4, 'Used for cold', 500),
(37, 7, 'Dolo 600', 1.5, 'Used for fever', 100),
(39, 8, 'Gloves', 8, 'Used for protection', 150),
(40, 7, 'Vicks action 420', 2.5, 'Used for cold', 150),
(41, 9, 'Crocine', 2, 'Used for cold', 110),
(43, 9, 'Vitamin E ', 2, 'Used for strengthening', 290),
(44, 9, 'Calbol', 1.5, 'Used for cold', 100),
(45, 6, 'Germ Free Soap', 15.5, 'Used for bathing', 150),
(46, 6, 'Mouth Wash', 30, 'Used to clean germs', 195);

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
(6, 'sid_distributor', 'Toiletries'),
(7, 'raja_distributor', 'Medications'),
(8, 'aiden_distributor', 'Basic Supplies'),
(9, 'kdr_distributor', 'Medications');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `username` varchar(200) NOT NULL,
  `orderDate` varchar(200) NOT NULL,
  `total_quantity` int(11) NOT NULL,
  `price` float NOT NULL,
  `address` varchar(8000) NOT NULL,
  `phone_number` varchar(10) NOT NULL,
  `distributor_name` varchar(200) NOT NULL,
  `status` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `username`, `orderDate`, `total_quantity`, `price`, `address`, `phone_number`, `distributor_name`, `status`) VALUES
(6, 'mah_ali', '2021-10-01', 30, 55, 'pune buildings, pune', '7458963322', 'kdr_distributor', 'ACCEPTED'),
(13, 'mah_ali', '2021-10-02', 20, 40, 'Wayne Manor, Gowtham City, Royapettah', '8544111222', 'raja_distributor', 'DELIVERED'),
(15, 'sam_fisher', '2021-10-02', 100, 150, 'Wayne Manor, Gowtham City, Royapettah', '8825908128', 'raja_distributor', 'ACCEPTED'),
(16, 'sam_fisher', '2021-10-02', 25, 1450, 'No:3 ashok nagar, chennai', '8123654444', 'sid_distributor', 'DELIVERED'),
(17, 'azar_bazar', '2021-10-02', 15, 975, 'pune buildings, pune', '8544111222', 'sid_distributor', 'ACCEPTED');

-- --------------------------------------------------------

--
-- Table structure for table `particular_order_prod`
--

CREATE TABLE `particular_order_prod` (
  `id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `item_name` varchar(200) NOT NULL,
  `price` float NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `particular_order_prod`
--

INSERT INTO `particular_order_prod` (`id`, `order_id`, `item_name`, `price`, `quantity`) VALUES
(18, 6, 'Crocine', 20, 10),
(19, 6, 'Vitamin E ', 20, 10),
(20, 6, 'Calbol', 15, 10),
(28, 13, 'Vicks 500', 20, 10),
(29, 13, 'Crocine', 20, 10),
(32, 15, 'Paracetomol', 75, 50),
(33, 15, 'Dolo 600', 75, 50),
(34, 16, 'Baby Oil', 250, 10),
(35, 16, 'Baby Powder', 200, 10),
(36, 16, 'MamaEarth Sampoo', 1000, 5),
(37, 17, 'Baby Powder', 100, 5),
(38, 17, 'Baby Oil', 125, 5),
(39, 17, 'Baby Health Kit', 750, 5);

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
