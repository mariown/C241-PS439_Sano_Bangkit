-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 15, 2024 at 04:19 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sano-db`
--

-- --------------------------------------------------------

--
-- Table structure for table `diabetesprediction`
--

CREATE TABLE `diabetesprediction` (
  `id` int(11) NOT NULL,
  `ffpg` double DEFAULT NULL,
  `fpg` double DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `hdl` double DEFAULT NULL,
  `ldl` double DEFAULT NULL,
  `sbp` double DEFAULT NULL,
  `createdAt` datetime(3) NOT NULL DEFAULT current_timestamp(3),
  `updatedAt` datetime(3) NOT NULL,
  `idUser` int(11) NOT NULL,
  `diabetes_risk` double DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `suggestion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `diabetesprediction`
--


-- --------------------------------------------------------

--
-- Table structure for table `heartdiseaseprediction`
--

CREATE TABLE `heartdiseaseprediction` (
  `id` int(11) NOT NULL,
  `troponin` double DEFAULT NULL,
  `kcm` double DEFAULT NULL,
  `pressureheight` int(11) DEFAULT NULL,
  `createdAt` datetime(3) NOT NULL DEFAULT current_timestamp(3),
  `updatedAt` datetime(3) NOT NULL,
  `idUser` int(11) NOT NULL,
  `heart_disease_risk` double DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `suggestion` varchar(255) DEFAULT NULL,
  `presurelow` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `glucose` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `heartdiseaseprediction`
--


-- --------------------------------------------------------

--
-- Table structure for table `strokeprediction`
--

CREATE TABLE `strokeprediction` (
  `id` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `bmi` double DEFAULT NULL,
  `hypertension` int(11) DEFAULT NULL,
  `heart_disease` int(11) DEFAULT NULL,
  `smoking_status` int(11) DEFAULT NULL,
  `createdAt` datetime(3) NOT NULL DEFAULT current_timestamp(3),
  `updatedAt` datetime(3) NOT NULL,
  `idUser` int(11) NOT NULL,
  `avg_glucose_level` double DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `stroke_risk` double DEFAULT NULL,
  `suggestion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `strokeprediction`
--


-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `refresh_token` text DEFAULT NULL,
  `createdAt` datetime(3) NOT NULL DEFAULT current_timestamp(3),
  `updatedAt` datetime(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

-- --------------------------------------------------------

--
-- Table structure for table `_prisma_migrations`
--

CREATE TABLE `_prisma_migrations` (
  `id` varchar(36) NOT NULL,
  `checksum` varchar(64) NOT NULL,
  `finished_at` datetime(3) DEFAULT NULL,
  `migration_name` varchar(255) NOT NULL,
  `logs` text DEFAULT NULL,
  `rolled_back_at` datetime(3) DEFAULT NULL,
  `started_at` datetime(3) NOT NULL DEFAULT current_timestamp(3),
  `applied_steps_count` int(10) UNSIGNED NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `_prisma_migrations`
--

INSERT INTO `_prisma_migrations` (`id`, `checksum`, `finished_at`, `migration_name`, `logs`, `rolled_back_at`, `started_at`, `applied_steps_count`) VALUES
('44241f2c-04cc-493a-88e0-43fe96395b14', '704503b6026ff0d6607ad371c7b1d288ac3391c96da9a1d37366dbcf7b6fef67', '2024-06-15 12:01:21.746', '20240615120121_migrate_keenam', NULL, NULL, '2024-06-15 12:01:21.735', 1),
('49b87363-86a4-44ad-88e3-2b36486683e1', '36625dfec253d8e30105c42b12e4d633c30d99b27a332d2dbec09a1a04ac1251', '2024-06-15 11:56:10.445', '20240615115610_migrate_keempat', NULL, NULL, '2024-06-15 11:56:10.427', 1),
('4fdfcae3-679e-408d-9d26-cb556e838d1d', '13af9d3f1ba60468bbecd10c01ab4e22060e2a8814dbcb06b116b5d02d82a0a8', '2024-06-15 11:26:59.389', '20240615112659_migrate_kedua', NULL, NULL, '2024-06-15 11:26:59.373', 1),
('5338fcff-4557-44e0-93ac-1394cf021401', '850a56f8b8565fa9408fdfaa9632995fdd22424a8321cfa1cf40574898972a0b', '2024-06-15 05:21:28.767', '20240614165127_remove_relation_database', NULL, NULL, '2024-06-15 05:21:28.711', 1),
('7d7fd1c5-76a3-4a7b-b1c9-beb9549a4a2a', 'a248e01a33836f73954ed3d7eebbb4f030c17c32dd7cf558f05777111bf402b6', '2024-06-15 11:59:33.008', '20240615115932_migrate_kelima', NULL, NULL, '2024-06-15 11:59:32.991', 1),
('9b958fdf-13c6-48e7-a555-2ca68b1ff288', 'a25d6347fd83619f3ce4e0696082468672693ff637d79ef5332a45b3114ca34f', '2024-06-15 05:21:28.709', '20240614163001_init', NULL, NULL, '2024-06-15 05:21:28.486', 1),
('a0cdabd6-04b3-4392-a8e8-ae6c549162c7', 'eac01b7b567d0dbcf03fefbab379ed885bdd8db03241dd058491e4b40c48a4a0', '2024-06-15 05:21:28.977', '20240614165523_back_the_relation', NULL, NULL, '2024-06-15 05:21:28.769', 1),
('a576babb-0e2f-405a-a801-c7b534245bd2', '521c3fcf2ca8417784860e0f02fd342bd001732a241d4b20a4d49c7237c20c1c', '2024-06-15 11:44:07.869', '20240615114407_migrate_ketiga', NULL, NULL, '2024-06-15 11:44:07.848', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `diabetesprediction`
--
ALTER TABLE `diabetesprediction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `diabetesprediction_idUser_fkey` (`idUser`);

--
-- Indexes for table `heartdiseaseprediction`
--
ALTER TABLE `heartdiseaseprediction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `heartdiseaseprediction_idUser_fkey` (`idUser`);

--
-- Indexes for table `strokeprediction`
--
ALTER TABLE `strokeprediction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `strokeprediction_idUser_fkey` (`idUser`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_key` (`email`);

--
-- Indexes for table `_prisma_migrations`
--
ALTER TABLE `_prisma_migrations`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `diabetesprediction`
--
ALTER TABLE `diabetesprediction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `heartdiseaseprediction`
--
ALTER TABLE `heartdiseaseprediction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `strokeprediction`
--
ALTER TABLE `strokeprediction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `diabetesprediction`
--
ALTER TABLE `diabetesprediction`
  ADD CONSTRAINT `diabetesprediction_idUser_fkey` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `heartdiseaseprediction`
--
ALTER TABLE `heartdiseaseprediction`
  ADD CONSTRAINT `heartdiseaseprediction_idUser_fkey` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `strokeprediction`
--
ALTER TABLE `strokeprediction`
  ADD CONSTRAINT `strokeprediction_idUser_fkey` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
