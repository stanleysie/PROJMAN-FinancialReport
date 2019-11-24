CREATE DATABASE IF NOT EXISTS financialreport /*!40100 DEFAULT CHARACTER SET utf8*/;
USE financialreport;

-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: financialreport
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `daily_report`
--

DROP TABLE IF EXISTS `daily_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `daily_report` (
  `idreport` int(11) NOT NULL,
  `employeename` varchar(45) DEFAULT NULL,
  `basic_rate` float DEFAULT NULL,
  `no_working_days` float DEFAULT NULL,
  `no_abscence` int(11) DEFAULT NULL,
  `total_days_reported` float DEFAULT NULL,
  `late_hours` float DEFAULT NULL,
  `late_rate` float DEFAULT NULL,
  `late_deduction` float DEFAULT NULL,
  `undertime_hours` float DEFAULT NULL,
  `undertime_rate` float DEFAULT NULL,
  `undertime_deduction` float DEFAULT NULL,
  `total_deduction` float DEFAULT NULL,
  `night_diff_hours` float DEFAULT NULL,
  `night_diff_rate` float DEFAULT NULL,
  `night_diff_earning` float DEFAULT NULL,
  `ot_regday_hours` float DEFAULT NULL,
  `ot_regday_rate` float DEFAULT NULL,
  `ot_regday_earning` float DEFAULT NULL,
  `rd_or_sh_hours` float DEFAULT NULL,
  `rd_or_sh_rate` float DEFAULT NULL,
  `rd_or_sh_earning` float DEFAULT NULL,
  `sh_and_rd_hours` float DEFAULT NULL,
  `sh_and_rd_rate` float DEFAULT NULL,
  `sh_and_rd_earning` float DEFAULT NULL,
  `lh_hours` float DEFAULT NULL,
  `lh_rate` float DEFAULT NULL,
  `lh_earning` float DEFAULT NULL,
  `lh_and_rd_hours` float DEFAULT NULL,
  `lh_and_rd_rate` float DEFAULT NULL,
  `lh_and_rd_earning` float DEFAULT NULL,
  `gross_pay` float DEFAULT NULL,
  `statutory_sss` float DEFAULT NULL,
  `statutory_pagibig` float DEFAULT NULL,
  `statutory_philhealth` float DEFAULT NULL,
  `statutory_ecola` float DEFAULT NULL,
  `total_statutory` float DEFAULT NULL,
  `13th_month` float DEFAULT NULL,
  `incentive` float DEFAULT NULL,
  `total` float DEFAULT NULL,
  `admin_fee` float DEFAULT NULL,
  `net_payroll` float DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`idreport`),
  KEY `emplyeename_idx` (`employeename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daily_report`
--

LOCK TABLES `daily_report` WRITE;
/*!40000 ALTER TABLE `daily_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `daily_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employee` (
  `idemployee` int(11) NOT NULL,
  `employeefirstname` varchar(45) DEFAULT NULL,
  `employeelastname` varchar(45) DEFAULT NULL,
  `province` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idemployee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monthly_report`
--

DROP TABLE IF EXISTS `monthly_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `monthly_report` (
  `idreport` int(11) NOT NULL,
  `employeename` varchar(45) DEFAULT NULL,
  `monthly_rate` float DEFAULT NULL,
  `basic_rate` float DEFAULT NULL,
  `no_working_days` float DEFAULT NULL,
  `total_days_rendered` float DEFAULT NULL,
  `no_absence` int(11) DEFAULT NULL,
  `absent_rate` float DEFAULT NULL,
  `absent_deduction` float DEFAULT NULL,
  `late_hours` float DEFAULT NULL,
  `late_rate` float DEFAULT NULL,
  `late_deduction` float DEFAULT NULL,
  `undertime_hours` float DEFAULT NULL,
  `undertime_rate` float DEFAULT NULL,
  `undertime_deduction` float DEFAULT NULL,
  `total_deduction` float DEFAULT NULL,
  `night_diff_hours` float DEFAULT NULL,
  `night_diff_rate` float DEFAULT NULL,
  `night_diff_earning` float DEFAULT NULL,
  `ot_regday_hours` float DEFAULT NULL,
  `ot_regday_rate` float DEFAULT NULL,
  `ot_regday_earning` float DEFAULT NULL,
  `rd_or_sh_hours` float DEFAULT NULL,
  `rd_or_sh_rate` float DEFAULT NULL,
  `rd_or_sh_earning` float DEFAULT NULL,
  `sh_and_rd_hours` float DEFAULT NULL,
  `sh_and_rd_rate` float DEFAULT NULL,
  `sh_and_rd_earning` float DEFAULT NULL,
  `lh_hours` float DEFAULT NULL,
  `lh_rate` float DEFAULT NULL,
  `lh_earning` float DEFAULT NULL,
  `lh_and_rd_hours` float DEFAULT NULL,
  `lh_and_rd_rate` float DEFAULT NULL,
  `lh_and_rd_earning` float DEFAULT NULL,
  `gross_pay` float DEFAULT NULL,
  `statutory_sss` float DEFAULT NULL,
  `statutory_pagibig` float DEFAULT NULL,
  `statutory_philhealth` float DEFAULT NULL,
  `statutory_ecola` float DEFAULT NULL,
  `total_statutory` float DEFAULT NULL,
  `13th_month` float DEFAULT NULL,
  `incentive` float DEFAULT NULL,
  `total` float DEFAULT NULL,
  `admin_fee` float DEFAULT NULL,
  `net_payroll` float DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`idreport`),
  KEY `emplyeename_idx` (`employeename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monthly_report`
--

LOCK TABLES `monthly_report` WRITE;
/*!40000 ALTER TABLE `monthly_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `monthly_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `province`
--

DROP TABLE IF EXISTS `province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `province` (
  `idProvince` int(11) NOT NULL,
  `provincename` varchar(45) DEFAULT NULL,
  `salarymin` float DEFAULT NULL,
  `salarymax` float DEFAULT NULL,
  PRIMARY KEY (`idProvince`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `province`
--

LOCK TABLES `province` WRITE;
/*!40000 ALTER TABLE `province` DISABLE KEYS */;
INSERT INTO `province` VALUES (1,'NCR',500,537),(2,'CAR',340,350),(3,'Region I',282,340),(4,'Region II',320,360),(5,'Region III',284,400),(6,'Region IV-A',303,400),(7,'Region IV-B',294,320),(8,'Region V',310,310),(9,'Region VI',310,395),(10,'Region VII',313,386),(11,'Region VIII',285,315),(12,'Region IX',303,316),(13,'Region X',331,365),(14,'Region XI',381,396),(15,'Region XII',290,311),(16,'Region XIII',320,320),(17,'ARMM',270,280);
/*!40000 ALTER TABLE `province` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-24 19:26:42
