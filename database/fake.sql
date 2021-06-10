-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: sql11.freesqldatabase.com    Database: sql11416191
-- ------------------------------------------------------
-- Server version	5.5.62-0ubuntu0.14.04.1

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
-- Table structure for table `Accounts`
--

DROP TABLE IF EXISTS `Accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Accounts` (
  `Accounts_id` int(11) NOT NULL AUTO_INCREMENT,
  `Customer_id` int(11) NOT NULL,
  `Debitcard_id` varchar(8) NOT NULL,
  `Transactions_id` int(11) NOT NULL,
  `Balance` float DEFAULT NULL,
  `Username` varchar(10) DEFAULT NULL,
  `Passwords` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`Accounts_id`),
  KEY `Customer_id` (`Customer_id`),
  KEY `Debitcard_id` (`Debitcard_id`),
  KEY `Transactions_id` (`Transactions_id`),
  CONSTRAINT `Accounts_ibfk_1` FOREIGN KEY (`Customer_id`) REFERENCES `Customer` (`Customer_id`),
  CONSTRAINT `Accounts_ibfk_2` FOREIGN KEY (`Debitcard_id`) REFERENCES `Debitcard` (`Debitcard_id`),
  CONSTRAINT `Accounts_ibfk_3` FOREIGN KEY (`Transactions_id`) REFERENCES `Transactions` (`Transactions_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Accounts`
--

LOCK TABLES `Accounts` WRITE;
/*!40000 ALTER TABLE `Accounts` DISABLE KEYS */;
INSERT INTO `Accounts` VALUES (1,1,'6937E7C1',1,1000,'Noobmaster','1234'),(2,2,'24558E2B',2,840310,'Mastermast','1234'),(3,3,'E423D02A',3,21,'frikandelb','1234'),(4,4,'221FD534',4,0,'JiaHax','1234');
/*!40000 ALTER TABLE `Accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer` (
  `Customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `FirstNaam` varchar(255) NOT NULL,
  `LastNaam` varchar(255) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `City` varchar(255) NOT NULL,
  `Postcode` varchar(255) NOT NULL,
  `Country` varchar(255) NOT NULL,
  PRIMARY KEY (`Customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (1,'Bryan','Chung','Grande Marche','Niamey','3166NE','Niger'),(2,'Jurgen','van den Berg','IB street 73','Niamey','3156NE','Niger'),(3,'Wouter','van Huut','Dattiers','Niamey','3169NE','Niger'),(4,'Jia-jie','Yeh','Poste','Niamey','3101NE','Niger');
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Debitcard`
--

DROP TABLE IF EXISTS `Debitcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Debitcard` (
  `Debitcard_id` varchar(8) NOT NULL,
  `Iban` varchar(16) DEFAULT NULL,
  `Pincode` varchar(4) DEFAULT NULL,
  `Attempts` int(11) DEFAULT NULL,
  `Blocked` bit(1) DEFAULT NULL,
  PRIMARY KEY (`Debitcard_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Debitcard`
--

LOCK TABLES `Debitcard` WRITE;
/*!40000 ALTER TABLE `Debitcard` DISABLE KEYS */;
INSERT INTO `Debitcard` VALUES ('221FD534','NI42FAKE87654321','5678',0,_binary ''),('24558E2B','NI42FAKE12345678','5555',0,_binary '\0'),('6937E7C1','NI42FAKE12345679','1234',0,_binary ''),('E423D02A','NI42FAKE43215678','4321',0,_binary '\0');
/*!40000 ALTER TABLE `Debitcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Transactions`
--

DROP TABLE IF EXISTS `Transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Transactions` (
  `Transactions_id` int(11) NOT NULL AUTO_INCREMENT,
  `TransactionType` varchar(255) NOT NULL,
  `Amount` float NOT NULL,
  `TransactionDate` datetime NOT NULL,
  PRIMARY KEY (`Transactions_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Transactions`
--

LOCK TABLES `Transactions` WRITE;
/*!40000 ALTER TABLE `Transactions` DISABLE KEYS */;
INSERT INTO `Transactions` VALUES (1,'PIN',0,'0000-00-00 00:00:00'),(2,'PIN',0,'0000-00-00 00:00:00'),(3,'PIN',0,'0000-00-00 00:00:00'),(4,'PIN',0,'0000-00-00 00:00:00');
/*!40000 ALTER TABLE `Transactions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-10 11:49:24
