-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 18, 2023 at 02:49 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `oop_penyewaan_kamar`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_kamar`
--

CREATE TABLE `tb_kamar` (
  `Nama` varchar(40) DEFAULT NULL,
  `Jenis Kelamin` varchar(1) DEFAULT NULL,
  `NIK` int(20) NOT NULL,
  `Makanan` varchar(20) DEFAULT NULL,
  `Minuman` varchar(20) DEFAULT NULL,
  `Tipe Kamar` varchar(20) DEFAULT NULL,
  `Total Pembayaran` int(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_kamar`
--

INSERT INTO `tb_kamar` (`Nama`, `Jenis Kelamin`, `NIK`, `Makanan`, `Minuman`, `Tipe Kamar`, `Total Pembayaran`) VALUES
('Malik', 'L', 221334, 'Potato', 'jusApel', 'StandartRoom', 210000),
('Malik', 'L', 2218091, 'Salad Buah', 'Jus Mangga', 'Family Room', 1200000);

-- --------------------------------------------------------

--
-- Table structure for table `tb_makanan`
--

CREATE TABLE `tb_makanan` (
  `Id` int(11) NOT NULL,
  `Nama` varchar(91) NOT NULL,
  `Tagihan` int(90) NOT NULL,
  `No.Kamar` int(80) NOT NULL,
  `Makanan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_makanan`
--

INSERT INTO `tb_makanan` (`Id`, `Nama`, `Tagihan`, `No.Kamar`, `Makanan`) VALUES
(1, 'Malik Abdullah', 2, 91, 'Salad Buah'),
(2, 'malik', 2, 3, 'Potato Wadges');

-- --------------------------------------------------------

--
-- Table structure for table `tb_minuman`
--

CREATE TABLE `tb_minuman` (
  `Nama` varchar(100) NOT NULL,
  `Tagihan` int(80) NOT NULL,
  `No.Kamar` int(70) NOT NULL,
  `Minuman` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_kamar`
--
ALTER TABLE `tb_kamar`
  ADD PRIMARY KEY (`NIK`);

--
-- Indexes for table `tb_makanan`
--
ALTER TABLE `tb_makanan`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_makanan`
--
ALTER TABLE `tb_makanan`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
