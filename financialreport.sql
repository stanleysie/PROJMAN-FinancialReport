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
  PRIMARY KEY (`idreport`),
  KEY `emplyeename_idx` (`employeename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daily_report`
--

LOCK TABLES `daily_report` WRITE;
/*!40000 ALTER TABLE `daily_report` DISABLE KEYS */;
INSERT INTO `daily_report` VALUES (1,'Felongco, Silverio Gil Tansiongco',537,314,15222.5,14051.5,1120,100,193.21,10,1423.21,44.75,0,16645.7,12,18643.1,'2019124-76885',0),(2,'Felongco, Silverio Gil Tansiongco',537,300,14543.8,13425,1080,100,193.21,10,1383.21,44.75,0,15927,12,17838.2,'2019124-76885-1',0);
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
  PRIMARY KEY (`idemployee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Silverio Gil Tansiongco','Felongco','NCR','2326 Juan Luna Street Tondo 1000'),(2,'Gunnar Dominik Subrabas','Navidad','CAT','5/F B And L Building116 Legaspi Street, Legaspi Village 1200'),(3,'Justyn Riley Quiambao','Zoleta','ARMM','99 Maria Clara St. Sta Mesa Heights, 1220'),(4,'Daniela Krista Kulikutan','Quezada','Region I','4J Lopez De Leon Street Cor. Topaz Street Green Heights Village Sucat 1700'),(5,'Faro Clinton Tiong','Tanilon','Region IV-A','721-A Aurora Boulevard New Manila 1112'),(6,'Connor Lance Uson','Bristol','Region III','437 Quintin Paredes Street Binondo 1000'),(7,'Araceli Chrysann Chengco','Jacinto','Region X','8/F Pryce Center 1179 Don Chino Roces Avenue Corner Bagtikan Street 1286'),(8,'Rio Eugene Morgan','Fajardo','Region II','Jennys Avenue, Pasencia Cruz Compound Brgy. Maybunga 1600'),(9,'Julian Forrest Iitaoka','Reoja','Region XI','100 Einstein Street Cor. Finlandia Street Brgy. San Isidro 1200'),(10,'Yessenia Margaret Tanhehco','Padilla','Region VIII','150-A Lt. Artiaga Street 1500'),(11,'Stanley Lawrence','Sie','NCR','De La Salle University, Manila 1004');
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
  PRIMARY KEY (`idreport`),
  KEY `emplyeename_idx` (`employeename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monthly_report`
--

LOCK TABLES `monthly_report` WRITE;
/*!40000 ALTER TABLE `monthly_report` DISABLE KEYS */;
INSERT INTO `monthly_report` VALUES (1,'Reoja, Julian Forrest Iitaoka',396,314,429,396,160,7.92,618.75,10,371.13,33,0,800.13,12,896.146,'2019124-71553',NULL);
/*!40000 ALTER TABLE `monthly_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `province`
--

DROP TABLE IF EXISTS `province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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

-- Dump completed on 2019-12-04  2:28:01
