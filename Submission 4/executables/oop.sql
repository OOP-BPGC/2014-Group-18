-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 22, 2014 at 06:29 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `oop`
--

-- --------------------------------------------------------

--
-- Table structure for table `cab`
--

CREATE TABLE IF NOT EXISTS `cab` (
  `AppNo` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `Student` varchar(40) COLLATE latin1_spanish_ci NOT NULL,
  `ID` varchar(12) COLLATE latin1_spanish_ci NOT NULL,
  `Day` tinyint(4) NOT NULL DEFAULT '1',
  `Month` tinyint(4) NOT NULL DEFAULT '1',
  `Year` int(11) NOT NULL DEFAULT '2014',
  `Hour` tinyint(4) NOT NULL DEFAULT '18',
  `Minutes` tinyint(4) NOT NULL DEFAULT '30',
  `Source` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Destination` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Duration` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1 for one way and 2 for round trip',
  `Fare` int(11) NOT NULL DEFAULT '0',
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY `AppNo` (`AppNo`),
  FULLTEXT KEY `Student` (`Student`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=39 ;

--
-- Dumping data for table `cab`
--

INSERT INTO `cab` (`AppNo`, `Student`, `ID`, `Day`, `Month`, `Year`, `Hour`, `Minutes`, `Source`, `Destination`, `Duration`, `Fare`, `Timestamp`) VALUES
(20, 'Master Shifu', '2012A8PSTEST', 18, 11, 2014, 2, 18, 'Bits', 'Madgaon', 1, 600, '2014-11-17 18:48:02'),
(27, 'Master Shifu', '2012A8PSTEST', 22, 10, 114, 2, 0, 'Airport', 'Bits', 1, 250, '2014-11-21 11:14:19'),
(28, 'Master Shifu', '2012A8PSTEST', 22, 10, 114, 0, 0, 'Vasco', 'Bits', 1, 300, '2014-11-21 11:22:11'),
(34, 'Udit Guru', '2012B3A8232G', 24, 11, 2014, 15, 0, 'Vasco', 'Bits', 2, 800, '2014-11-22 11:03:01'),
(35, 'Rohit Gupta', '2012A8PS379G', 25, 11, 2014, 19, 0, 'Bits', 'Panjim', 2, 1000, '2014-11-22 11:20:17'),
(36, 'Rohit Gupta', '2012A8PS379G', 26, 11, 2014, 14, 0, 'Bits', 'Vasco', 1, 300, '2014-11-22 14:00:20'),
(38, 'Rohit Gupta', '2012A8PS379G', 26, 11, 2014, 18, 0, 'Bits', 'Madgaon', 2, 1200, '2014-11-22 17:28:10');

-- --------------------------------------------------------

--
-- Table structure for table `cabroutes`
--

CREATE TABLE IF NOT EXISTS `cabroutes` (
  `From` text COLLATE latin1_spanish_ci NOT NULL,
  `BITS` int(10) unsigned NOT NULL,
  `Vasco` int(10) unsigned NOT NULL,
  `Panjim` int(10) unsigned NOT NULL,
  `Airport` int(10) unsigned NOT NULL,
  `Madgaon` int(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Dumping data for table `cabroutes`
--

INSERT INTO `cabroutes` (`From`, `BITS`, `Vasco`, `Panjim`, `Airport`, `Madgaon`) VALUES
('Bits', 0, 300, 500, 250, 600),
('Vasco', 400, 0, 700, 200, 900),
('Panjim', 500, 700, 0, 650, 400),
('Airport', 250, 200, 700, 0, 900),
('Madgaon', 600, 900, 400, 700, 0);

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE IF NOT EXISTS `room` (
  `AppNo` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `RoomNo` varchar(4) COLLATE latin1_spanish_ci DEFAULT NULL COMMENT 'eg. A603',
  `Student` varchar(40) COLLATE latin1_spanish_ci NOT NULL COMMENT 'Booked by',
  `ID` varchar(12) COLLATE latin1_spanish_ci NOT NULL COMMENT 'Student''s ID',
  `Day` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1-30',
  `Month` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1-12',
  `Year` smallint(6) NOT NULL COMMENT 'eg.2014',
  `Start` float NOT NULL DEFAULT '0' COMMENT 'eg,16.30',
  `End` float NOT NULL DEFAULT '0' COMMENT 'eg.18.30',
  `Duration` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'in hours',
  `Reason` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `Granted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 for queue 1 for granted 2 for denied',
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Reason denied` varchar(40) COLLATE latin1_spanish_ci DEFAULT NULL COMMENT 'mentioned only in case of denied',
  `Email` varchar(40) COLLATE latin1_spanish_ci NOT NULL COMMENT 'email of applicant',
  UNIQUE KEY `AppNo` (`AppNo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=42 ;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`AppNo`, `RoomNo`, `Student`, `ID`, `Day`, `Month`, `Year`, `Start`, `End`, `Duration`, `Reason`, `Granted`, `Timestamp`, `Reason denied`, `Email`) VALUES
(21, 'a343', 'Master Shifu', '2012A8PSTEST', 12, 12, 1234, 12, 14, 1, 'exam', 1, '2014-11-17 08:47:26', 'null', ''),
(25, 'a323', 'Master Shifu', '2012A8PSTEST', 12, 12, 8912, 12, 14, 2, 'sadasd', 2, '2014-11-17 09:44:29', 'not allowed', 'mastershifu6@gmail.com'),
(26, 'a122', 'Rohit Gupta', '2012A8PS379G', 18, 11, 2014, 12, 14, 2, 'aadsd', 1, '2014-11-17 11:41:34', 'null', 'guptarohit994@gmail.com'),
(27, 'a343', 'Master Shifu', '2012A8PSTEST', 19, 11, 2014, 8, 23.59, 19, 'qweqwe', 0, '2014-11-17 19:00:05', NULL, 'mastershifu6@gmail.com'),
(28, 'a323', 'Master Shifu', '2012A8PSTEST', 19, 11, 2014, 8, 10, 2, 'class thermo', 1, '2014-11-17 19:40:28', 'null', 'mastershifu6@gmail.com'),
(30, 'a432', 'Master Shifu', '2012A8PSTEST', 20, 11, 2014, 16, 18, 2, 'test', 2, '2014-11-19 09:24:03', 'no test tomorrow', 'mastershifu6@gmail.com'),
(31, 'a987', 'Master Shifu', '2012A8PSTEST', 20, 11, 2014, 16, 18, 2, 'try', 0, '2014-11-19 09:32:57', NULL, 'mastershifu6@gmail.com'),
(32, 'a321', 'Rohit Gupta', '2012A8PS379G', 20, 11, 2014, 19, 20, 1, 'erwwerw', 2, '2014-11-19 09:54:02', 'no', 'guptarohit994@gmail.com'),
(33, 'a602', 'Rohit Gupta', '2012A8PS379G', 20, 11, 2014, 16, 18, 2, 'exam', 0, '2014-11-19 10:00:08', NULL, 'guptarohit994@gmail.com'),
(36, 'c302', 'Master Shifu', '2012A8PSTEST', 25, 11, 2014, 16, 18, 2, 'yolo', 2, '2014-11-22 05:48:00', 'nope ', 'mastershifu6@gmail.com'),
(38, 'a505', 'Udit Guru', '2012B3A8232G', 25, 11, 2014, 18, 19, 1, 'testing', 2, '2014-11-22 11:03:57', ' noo', 'mastershifu6@gmail.com'),
(39, 'a505', 'Rohit Gupta', '2012A8PS379G', 26, 11, 2014, 23, 24, 1, 'adfcdsc', 2, '2014-11-22 11:22:28', 'sfdfsdfsdf', 'guptarohit994@gmail.com'),
(40, 'c301', 'Rohit Gupta', '2012A8PS379G', 24, 11, 2014, 0, 1, 1, 'sdfdsf', 2, '2014-11-22 16:32:41', ' scsc', 'guptarohit994@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `Name` varchar(40) COLLATE latin1_spanish_ci NOT NULL,
  `User` varchar(20) COLLATE latin1_spanish_ci NOT NULL COMMENT 'user name',
  `ID` varchar(12) COLLATE latin1_spanish_ci NOT NULL,
  `Contact` varchar(40) COLLATE latin1_spanish_ci NOT NULL,
  `Password` varchar(10) COLLATE latin1_spanish_ci NOT NULL,
  `Admin` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1 for admin 0 for user',
  `Loan` int(11) NOT NULL DEFAULT '0' COMMENT 'Amount to be paid by the user',
  UNIQUE KEY `ID` (`ID`,`Contact`),
  UNIQUE KEY `user` (`User`),
  FULLTEXT KEY `Name` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`Name`, `User`, `ID`, `Contact`, `Password`, `Admin`, `Loan`) VALUES
('Priyanka', 'pmp', '2012A3PS121G', 'priyanka.mp2808@gmail.com', 'pmp', 0, 0),
('Rohit Gupta', 'guptarohit994', '2012A8PS379G', 'guptarohit994@gmail.com', 'rohitg', 0, 2500),
('Admin', 'admin', '2012A8PSADMG', 'guptarohit994@gmail.com', 'admin', 1, 0),
('Master Shifu', 'test', '2012A8PSTEST', 'mastershifu6@gmail.com', 'test', 0, 900),
('Udit Guru', 'uditg', '2012B3A8232G', 'mastershifu6@gmail.com', 'uditg', 0, 800);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
