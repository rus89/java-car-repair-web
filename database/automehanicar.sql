-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 21, 2012 at 11:01 PM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `automehanicar`
--

-- --------------------------------------------------------

--
-- Table structure for table `automobil`
--

CREATE TABLE IF NOT EXISTS `automobil` (
  `SifraAutomobila` int(11) NOT NULL,
  `BrojRegistracije` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `Model` int(11) NOT NULL,
  `JMBGVlasnika` varchar(13) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SifraAutomobila`),
  KEY `JMBGVlasnika` (`JMBGVlasnika`),
  KEY `Model` (`Model`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `dugovanje`
--

CREATE TABLE IF NOT EXISTS `dugovanje` (
  `SifraDugovanja` int(11) NOT NULL,
  `VrednostDugovanja` double NOT NULL,
  `JMBGVlasnika` varchar(13) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SifraDugovanja`),
  KEY `JMBGVlasnika` (`JMBGVlasnika`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `intervencija`
--

CREATE TABLE IF NOT EXISTS `intervencija` (
  `SifraIntervencije` int(11) NOT NULL,
  `SifraZaposlenog` int(11) NOT NULL,
  `SifraAutomobila` int(11) NOT NULL,
  `DatumIntervencije` datetime NOT NULL,
  PRIMARY KEY (`SifraIntervencije`),
  KEY `SifraZaposlenog` (`SifraZaposlenog`,`SifraAutomobila`),
  KEY `SifraAutomobila` (`SifraAutomobila`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `markaautomobila`
--

CREATE TABLE IF NOT EXISTS `markaautomobila` (
  `SifraMarke` int(11) NOT NULL,
  `NazivMarke` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SifraMarke`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `modelautomobila`
--

CREATE TABLE IF NOT EXISTS `modelautomobila` (
  `SifraModela` int(11) NOT NULL,
  `SifraMarke` int(11) NOT NULL,
  `NazivModela` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SifraModela`,`SifraMarke`),
  KEY `SifraMarke` (`SifraMarke`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `opisintervencije`
--

CREATE TABLE IF NOT EXISTS `opisintervencije` (
  `SifraOpisa` int(11) NOT NULL,
  `Opis` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SifraOpisa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `uradjeneintervencije`
--

CREATE TABLE IF NOT EXISTS `uradjeneintervencije` (
  `SifraIntervencije` int(11) NOT NULL,
  `RB` int(11) NOT NULL,
  `SifraOpisa` int(11) NOT NULL,
  PRIMARY KEY (`SifraIntervencije`,`RB`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `vlasnikautomobila`
--

CREATE TABLE IF NOT EXISTS `vlasnikautomobila` (
  `JMBG` varchar(13) COLLATE utf8_unicode_ci NOT NULL,
  `Ime` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `Prezime` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`JMBG`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `zaposleni`
--

CREATE TABLE IF NOT EXISTS `zaposleni` (
  `SifraZaposlenog` int(11) NOT NULL,
  `JMBG` varchar(13) COLLATE utf8_unicode_ci NOT NULL,
  `Ime` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `Prezime` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `Username` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `Password` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DatumRodjenja` date NOT NULL,
  `Adresa` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Telefon` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Pol` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SifraZaposlenog`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `zaposleni`
--

INSERT INTO `zaposleni` (`SifraZaposlenog`, `JMBG`, `Ime`, `Prezime`, `Username`, `Password`, `DatumRodjenja`, `Adresa`, `Telefon`, `Email`, `Pol`) VALUES
(1, '1008989732530', 'Milan', 'Rusimov', 'rus', 'rus', '1989-08-10', 'Obiliceva 61', '064/2718765', 'rusmil89@gmail.com', 'muski');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `automobil`
--
ALTER TABLE `automobil`
  ADD CONSTRAINT `automobil_ibfk_1` FOREIGN KEY (`Model`) REFERENCES `modelautomobila` (`SifraModela`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `automobil_ibfk_2` FOREIGN KEY (`JMBGVlasnika`) REFERENCES `vlasnikautomobila` (`JMBG`) ON UPDATE CASCADE;

--
-- Constraints for table `dugovanje`
--
ALTER TABLE `dugovanje`
  ADD CONSTRAINT `dugovanje_ibfk_1` FOREIGN KEY (`JMBGVlasnika`) REFERENCES `vlasnikautomobila` (`JMBG`) ON UPDATE CASCADE;

--
-- Constraints for table `intervencija`
--
ALTER TABLE `intervencija`
  ADD CONSTRAINT `intervencija_ibfk_1` FOREIGN KEY (`SifraZaposlenog`) REFERENCES `zaposleni` (`SifraZaposlenog`) ON UPDATE CASCADE,
  ADD CONSTRAINT `intervencija_ibfk_2` FOREIGN KEY (`SifraAutomobila`) REFERENCES `automobil` (`SifraAutomobila`) ON UPDATE CASCADE;

--
-- Constraints for table `modelautomobila`
--
ALTER TABLE `modelautomobila`
  ADD CONSTRAINT `modelautomobila_ibfk_1` FOREIGN KEY (`SifraMarke`) REFERENCES `markaautomobila` (`SifraMarke`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `uradjeneintervencije`
--
ALTER TABLE `uradjeneintervencije`
  ADD CONSTRAINT `uradjeneintervencije_ibfk_1` FOREIGN KEY (`SifraIntervencije`) REFERENCES `intervencija` (`SifraIntervencije`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
