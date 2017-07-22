-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 22, 2017 at 05:49 PM
-- Server version: 5.6.21
-- PHP Version: 5.5.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_m_market`
--

-- --------------------------------------------------------

--
-- Table structure for table `adress`
--

CREATE TABLE IF NOT EXISTS `adress` (
`id_adress` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `nm_penerima` varchar(50) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `provinsi` varchar(50) NOT NULL,
  `notelp` varchar(12) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `adress`
--

INSERT INTO `adress` (`id_adress`, `id_user`, `nm_penerima`, `alamat`, `provinsi`, `notelp`) VALUES
(7, 2, 'asd', 'dsa', 'asdf', '123'),
(8, 19, 'Jemmy Calak', 'Muncul Serpong', 'Tangerang Selatan', '082269219485'),
(9, 19, 'calak', 'dasasdas', 'dasdasdasdas', '091293812983');

-- --------------------------------------------------------

--
-- Table structure for table `bank`
--

CREATE TABLE IF NOT EXISTS `bank` (
`bank_id` int(11) NOT NULL,
  `bank_nm` varchar(15) NOT NULL,
  `norek` int(11) NOT NULL,
  `nm_pemilik` varchar(50) NOT NULL,
  `jmlh_trnsfer` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bank`
--

INSERT INTO `bank` (`bank_id`, `bank_nm`, `norek`, `nm_pemilik`, `jmlh_trnsfer`) VALUES
(1, 'BNI', 1234512345, 'calak', 123333);

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE IF NOT EXISTS `cart` (
`chart_id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_prdk` int(11) NOT NULL,
  `jmlh_prdk` int(5) DEFAULT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`chart_id`, `id_user`, `id_prdk`, `jmlh_prdk`, `create_at`, `update_at`) VALUES
(113, 19, 3, NULL, '2017-07-22 15:13:41', '0000-00-00 00:00:00'),
(114, 19, 3, NULL, '2017-07-22 15:13:56', '0000-00-00 00:00:00'),
(115, 19, 3, NULL, '2017-07-22 15:14:17', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id_category` int(5) NOT NULL,
  `nm` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE IF NOT EXISTS `order` (
`id_pesan` int(11) NOT NULL,
  `id_invoice` int(11) DEFAULT NULL,
  `id_user` int(11) NOT NULL,
  `id_product` int(11) DEFAULT NULL,
  `id_toko` int(11) DEFAULT NULL,
  `status_order` int(11) DEFAULT NULL,
  `metode_payment` int(11) DEFAULT NULL,
  `note_product` varchar(100) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `shipping` float DEFAULT NULL,
  `id_penerima2` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`id_pesan`, `id_invoice`, `id_user`, `id_product`, `id_toko`, `status_order`, `metode_payment`, `note_product`, `quantity`, `shipping`, `id_penerima2`) VALUES
(8, NULL, 20, 1, 1, NULL, 1, 'ww', 1, NULL, NULL),
(9, NULL, 20, 1, 1, NULL, NULL, NULL, 1, NULL, NULL),
(10, NULL, 20, 1, 1, NULL, NULL, NULL, 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `order2`
--

CREATE TABLE IF NOT EXISTS `order2` (
`id_order` int(11) NOT NULL,
  `id_invoice` int(11) DEFAULT NULL,
  `id_user` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  `id_toko` int(11) NOT NULL,
  `status_order` varchar(255) DEFAULT NULL,
  `metode_payment` int(11) NOT NULL,
  `note_product` varchar(255) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `shipping` int(11) DEFAULT NULL,
  `id_penerima2` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order2`
--

INSERT INTO `order2` (`id_order`, `id_invoice`, `id_user`, `id_product`, `id_toko`, `status_order`, `metode_payment`, `note_product`, `quantity`, `shipping`, `id_penerima2`) VALUES
(1, NULL, 20, 0, 0, NULL, 0, NULL, 0, NULL, NULL),
(2, NULL, 20, 0, 0, NULL, 0, NULL, 0, NULL, NULL),
(3, NULL, 20, 3, 1, NULL, 0, NULL, 1, NULL, NULL),
(4, NULL, 20, 3, 1, NULL, 0, NULL, 1, NULL, NULL),
(5, NULL, 20, 3, 1, NULL, 2, 'Barang Warna merah.', 1, NULL, NULL),
(6, NULL, 19, 0, 1, NULL, 2, 'Barang Warna merah.', 0, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `order_detail`
--

CREATE TABLE IF NOT EXISTS `order_detail` (
  `id_psn_detail` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `pesan_detail_quantity` int(11) NOT NULL,
  `pesan_detail_shiping` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE IF NOT EXISTS `payment` (
  `transfer_id` int(11) NOT NULL,
  `bank_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `penerima`
--

CREATE TABLE IF NOT EXISTS `penerima` (
`penerima_id` int(11) NOT NULL,
  `penerima_nm` text NOT NULL,
  `penerima_almt` varchar(200) NOT NULL,
  `penerima_notelp` varchar(12) NOT NULL,
  `penerima_kdpos` mediumint(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
`product_id` int(11) NOT NULL,
  `product_nm` varchar(100) NOT NULL,
  `product_price` int(11) NOT NULL,
  `product_desc` varchar(700) NOT NULL,
  `product_weight` float NOT NULL,
  `product_count` tinyint(2) NOT NULL,
  `product_color` text NOT NULL,
  `product_img` varchar(100) NOT NULL,
  `id_category` int(5) DEFAULT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_id`, `product_nm`, `product_price`, `product_desc`, `product_weight`, `product_count`, `product_color`, `product_img`, `id_category`, `create_at`, `update_at`) VALUES
(1, 'qsas', 123, 'adfsd', 3, 3, 'dfsdfasd', 'http://192.168.43.117/db_m_market_localhost/images/mac1.jpg', NULL, '2017-05-18 16:30:14', '0000-00-00 00:00:00'),
(2, 'as', 12, 'asdas', 2, 12, 'asd', 'http://192.168.43.117/db_m_market_localhost/images/img4.png', NULL, '2017-05-18 16:30:07', '0000-00-00 00:00:00'),
(3, 'e', 1, 'sd', 12, 12, 'as', 'http://192.168.43.117/db_m_market_localhost/images/img4.png', NULL, '2017-05-18 16:30:43', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `toko`
--

CREATE TABLE IF NOT EXISTS `toko` (
`id_toko` int(11) NOT NULL,
  `nm_toko` varchar(255) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_addres` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `toko`
--

INSERT INTO `toko` (`id_toko`, `nm_toko`, `id_user`, `id_addres`) VALUES
(1, 'camart', 2, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`user_id` int(11) NOT NULL,
  `user_email` varchar(50) NOT NULL,
  `user_pw` varchar(50) NOT NULL,
  `user_nm` varchar(50) NOT NULL,
  `user_jk` varchar(12) DEFAULT NULL,
  `user_notelp` varchar(12) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_at` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `id_adres` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `user_email`, `user_pw`, `user_nm`, `user_jk`, `user_notelp`, `create_at`, `update_at`, `id_adres`) VALUES
(2, 'admin', 'admin', 'admin', NULL, NULL, '2017-05-07 12:58:46', '0000-00-00 00:00:00', NULL),
(6, 'a', 'a', 'a', 'Laki-Laki', '1', '2017-05-12 17:26:03', '0000-00-00 00:00:00', NULL),
(7, 'jemmy', 'jemmy', 'jemmy', 'Laki-Laki', '1', '2017-05-12 17:33:59', '0000-00-00 00:00:00', NULL),
(17, 'w', 'w', 'w', 'Laki-Laki', '2', '2017-05-12 18:13:59', '0000-00-00 00:00:00', NULL),
(18, 's', 's', 's', 'Laki-Laki', '1', '2017-05-12 18:15:26', '0000-00-00 00:00:00', NULL),
(19, 'calak', 'calak', 'calak', 'Laki-Laki', '1', '2017-05-12 18:16:55', '0000-00-00 00:00:00', NULL),
(20, 'sapta', 'sapta', 'sapta', 'Perempuan', '1', '2017-05-30 17:38:55', '0000-00-00 00:00:00', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `adress`
--
ALTER TABLE `adress`
 ADD PRIMARY KEY (`id_adress`), ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `bank`
--
ALTER TABLE `bank`
 ADD PRIMARY KEY (`bank_id`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
 ADD PRIMARY KEY (`chart_id`), ADD KEY `id_user` (`id_user`), ADD KEY `id_prdk` (`id_prdk`), ADD KEY `id_user_2` (`id_user`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
 ADD PRIMARY KEY (`id_category`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
 ADD PRIMARY KEY (`id_pesan`), ADD UNIQUE KEY `product_id_2` (`id_product`,`id_toko`,`metode_payment`), ADD KEY `user_id` (`id_user`), ADD KEY `user_id_2` (`id_user`), ADD KEY `pesan_id` (`id_pesan`), ADD KEY `user_id_3` (`id_user`), ADD KEY `product_id` (`id_product`,`id_toko`,`metode_payment`), ADD KEY `product_id_3` (`id_product`,`id_toko`,`metode_payment`), ADD KEY `metode_paymen` (`metode_payment`), ADD KEY `id_penerima2` (`id_penerima2`), ADD KEY `toko_id` (`id_toko`);

--
-- Indexes for table `order2`
--
ALTER TABLE `order2`
 ADD PRIMARY KEY (`id_order`);

--
-- Indexes for table `order_detail`
--
ALTER TABLE `order_detail`
 ADD KEY `pesan_id` (`id_psn_detail`,`product_id`,`pesan_detail_quantity`), ADD KEY `product_id` (`product_id`), ADD KEY `id_psn_detail` (`id_psn_detail`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
 ADD PRIMARY KEY (`transfer_id`), ADD KEY `bank_id` (`bank_id`), ADD KEY `transfer_id` (`transfer_id`), ADD KEY `bank_id_2` (`bank_id`);

--
-- Indexes for table `penerima`
--
ALTER TABLE `penerima`
 ADD PRIMARY KEY (`penerima_id`), ADD KEY `penerima_id` (`penerima_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
 ADD PRIMARY KEY (`product_id`), ADD KEY `id_category` (`id_category`), ADD KEY `id_category_2` (`id_category`), ADD KEY `id_category_3` (`id_category`), ADD KEY `id_category_4` (`id_category`), ADD KEY `id_category_5` (`id_category`), ADD KEY `id_category_6` (`id_category`), ADD KEY `id_category_7` (`id_category`), ADD KEY `id_category_8` (`id_category`), ADD KEY `id_category_9` (`id_category`), ADD KEY `id_category_10` (`id_category`), ADD KEY `id_category_11` (`id_category`), ADD KEY `id_category_12` (`id_category`), ADD KEY `id_category_13` (`id_category`), ADD KEY `id_category_14` (`id_category`), ADD KEY `id_category_15` (`id_category`), ADD KEY `id_category_16` (`id_category`), ADD KEY `id_category_17` (`id_category`), ADD KEY `id_category_18` (`id_category`), ADD KEY `id_category_19` (`id_category`), ADD KEY `id_category_20` (`id_category`);

--
-- Indexes for table `toko`
--
ALTER TABLE `toko`
 ADD PRIMARY KEY (`id_toko`), ADD KEY `id_user` (`id_user`,`id_addres`), ADD KEY `id_addres` (`id_addres`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`user_id`), ADD KEY `id_adres` (`id_adres`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `adress`
--
ALTER TABLE `adress`
MODIFY `id_adress` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `bank`
--
ALTER TABLE `bank`
MODIFY `bank_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
MODIFY `chart_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=116;
--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
MODIFY `id_pesan` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `order2`
--
ALTER TABLE `order2`
MODIFY `id_order` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `penerima`
--
ALTER TABLE `penerima`
MODIFY `penerima_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `toko`
--
ALTER TABLE `toko`
MODIFY `id_toko` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=21;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `adress`
--
ALTER TABLE `adress`
ADD CONSTRAINT `adress_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
ADD CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`id_prdk`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `cart_ibfk_3` FOREIGN KEY (`id_user`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`bank_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `product`
--
ALTER TABLE `product`
ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `toko`
--
ALTER TABLE `toko`
ADD CONSTRAINT `toko_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
