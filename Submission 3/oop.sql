-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 14, 2014 at 06:45 PM
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
  UNIQUE KEY `AppNo` (`AppNo`),
  FULLTEXT KEY `Student` (`Student`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=14 ;

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
('Vasco', 300, 0, 700, 200, 900),
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
  UNIQUE KEY `AppNo` (`AppNo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=14 ;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`AppNo`, `RoomNo`, `Student`, `ID`, `Day`, `Month`, `Year`, `Start`, `End`, `Duration`, `Reason`, `Granted`) VALUES
(12, 'a121', 'test', '2012A8PSTEST', 12, 12, 12, 12, 14, 2, 'erfewf', 2);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `Name` varchar(40) COLLATE latin1_spanish_ci NOT NULL,
  `User` varchar(20) COLLATE latin1_spanish_ci NOT NULL COMMENT 'user name',
  `ID` varchar(12) COLLATE latin1_spanish_ci NOT NULL,
  `Contact` varchar(10) COLLATE latin1_spanish_ci NOT NULL,
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
('Rohit Gupta', 'guptarohit994', '2012A8PS379G', '9637395968', 'rohitg', 0, 0),
('Admin', 'admin', '2012A8PSADMG', '1234567890', 'admin', 1, 0),
('test', 'test', '2012A8PSTEST', '1234567890', 'test', 0, 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
