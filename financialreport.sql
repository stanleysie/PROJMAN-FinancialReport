CREATE DATABASE  IF NOT EXISTS `financialreport` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `financialreport`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: financialreport
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `idaccount` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `accounttype` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idaccount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'admin','admin','admin');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `daily_report`
--

DROP TABLE IF EXISTS `daily_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `daily_report` (
  `idreport` int(11) NOT NULL,
  `employeename` varchar(45) DEFAULT NULL,
  `basic_rate` float DEFAULT NULL,
  `no_working_days` float DEFAULT NULL,
  `equivalent_monthly_cost` float DEFAULT NULL,
  `effective_monthly_rate` float DEFAULT NULL,
  `statutory_sss` float DEFAULT NULL,
  `statutory_pagibig` float DEFAULT NULL,
  `statutory_philhealth` float DEFAULT NULL,
  `statutory_ecola` float DEFAULT NULL,
  `total_statutory` float DEFAULT NULL,
  `13th_month` float DEFAULT NULL,
  `incentive` float DEFAULT NULL,
  `total_labor_cost` float DEFAULT NULL,
  `admin_cost` float DEFAULT NULL,
  `contract_cost` float DEFAULT NULL,
  `version` varchar(45) DEFAULT NULL,
  `allowance` float DEFAULT NULL,
  `creator` varchar(200) DEFAULT NULL,
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `idemployee` int(11) NOT NULL,
  `employeefirstname` varchar(45) DEFAULT NULL,
  `employeelastname` varchar(45) DEFAULT NULL,
  `province` varchar(45) DEFAULT NULL,
  `address` varchar(300) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`idemployee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Silverio Gil Tansiongco','Felongco','NCR','2326 Juan Luna Street Tondo 1000',1),(2,'Gunnar Dominik Subrabas','Navidad','CAT','5/F B And L Building116 Legaspi Street, Legaspi Village 1200',1),(3,'Justyn Riley Quiambao','Zoleta','ARMM','99 Maria Clara St. Sta Mesa Heights, 1220',1),(4,'Daniela Krista Kulikutan','Quezada','Region I','4J Lopez De Leon Street Cor. Topaz Street Green Heights Village Sucat 1700',1),(5,'Faro Clinton Tiong','Tanilon','Region IV-A','721-A Aurora Boulevard New Manila 1112',1),(6,'Connor Lance Uson','Bristol','Region III','437 Quintin Paredes Street Binondo 1000',1),(7,'Araceli Chrysann Chengco','Jacinto','Region X','8/F Pryce Center 1179 Don Chino Roces Avenue Corner Bagtikan Street 1286',1),(8,'Rio Eugene Morgan','Fajardo','Region II','Jennys Avenue, Pasencia Cruz Compound Brgy. Maybunga 1600',1),(9,'Julian Forrest Iitaoka','Reoja','Region XI','100 Einstein Street Cor. Finlandia Street Brgy. San Isidro 1200',1),(10,'Yessenia Margaret Tanhehco','Padilla','Region VIII','150-A Lt. Artiaga Street 1500',1),(11,'Stanley Lawrence','Sie','NCR','De La Salle University, Manila 1004',1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monthly_report`
--

DROP TABLE IF EXISTS `monthly_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `monthly_report` (
  `idreport` int(11) NOT NULL,
  `employeename` varchar(45) DEFAULT NULL,
  `basic_rate` float DEFAULT NULL,
  `no_working_days` float DEFAULT NULL,
  `equivalent_monthly_cost` float DEFAULT NULL,
  `effective_monthly_rate` float DEFAULT NULL,
  `statutory_sss` float DEFAULT NULL,
  `statutory_pagibig` float DEFAULT NULL,
  `statutory_philhealth` float DEFAULT NULL,
  `statutory_ecola` float DEFAULT NULL,
  `total_statutory` float DEFAULT NULL,
  `13th_month` float DEFAULT NULL,
  `incentive` float DEFAULT NULL,
  `total_labor_cost` float DEFAULT NULL,
  `admin_cost` float DEFAULT NULL,
  `contract_cost` float DEFAULT NULL,
  `version` varchar(45) DEFAULT NULL,
  `allowance` float DEFAULT NULL,
  `creator` varchar(200) DEFAULT NULL,
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
-- Dumping events for database 'financialreport'
--

--
-- Dumping routines for database 'financialreport'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-09 19:24:49
