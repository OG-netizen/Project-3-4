-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bank
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `Accounts_id` int NOT NULL AUTO_INCREMENT,
  `Customer_id` int NOT NULL,
  `Debitcard_id` varchar(8) NOT NULL,
  `Transactions_id` int NOT NULL,
  `Balance` float DEFAULT NULL,
  PRIMARY KEY (`Accounts_id`),
  KEY `Customer_id` (`Customer_id`),
  KEY `Debitcard_id` (`Debitcard_id`),
  KEY `Transactions_id` (`Transactions_id`),
  CONSTRAINT `accounts_ibfk_1` FOREIGN KEY (`Customer_id`) REFERENCES `customer` (`Customer_id`),
  CONSTRAINT `accounts_ibfk_2` FOREIGN KEY (`Debitcard_id`) REFERENCES `debitcard` (`Debitcard_id`),
  CONSTRAINT `accounts_ibfk_3` FOREIGN KEY (`Transactions_id`) REFERENCES `transactions` (`Transactions_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,1,'AAAAAAAA',1,300),(2,2,'BBBBBBBB',2,400),(3,3,'CCCCCCCC',3,500),(4,4,'DDDDDDDD',4,1000),(5,5,'EEEEEEEE',5,30),(6,7,'6937E7C1',6,300),(7,7,'24558E2B',7,999),(8,8,'E423D02A',8,42069),(9,8,'792CE9C1',9,200),(10,6,'221FD534',10,10),(11,6,'D93AEAC2',11,0);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `Customer_id` int NOT NULL AUTO_INCREMENT,
  `FirstNaam` varchar(255) NOT NULL,
  `LastNaam` varchar(255) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `City` varchar(255) NOT NULL,
  `Postcode` varchar(255) NOT NULL,
  `Country` varchar(255) NOT NULL,
  PRIMARY KEY (`Customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Harry','Potter','Kelder 1','Hel','6666 HE','Nederland'),(2,'Naruto','Iets','Hidden leaves 69','Bos','2915 NO','Nederland'),(3,'Cody','Martin','Chewsday 129','Boston','7328 BO','Amerika'),(4,'Zack','Martin','Chewsday 129','Boston','7328 BO','Amerika'),(5,'Joe','Lipton','Bruisel 38','Astana','1623 KJ','Kazachstan'),(6,'Jia-Jie','Yeh','Paggaslaan 123','Oud-Beijerland','1269 SM','Nederland'),(7,'Jurgen','van den Berg','vn Hogendorplaan 7','Maasdam','3299 AR','Nederland'),(8,'Wouter','van Huut','Albert Schweitzererf 36','Oud-Beijerland','3263 SL','Nederland');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `debitcard`
--

DROP TABLE IF EXISTS `debitcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `debitcard` (
  `Debitcard_id` varchar(8) NOT NULL,
  `Pincode` varchar(4) NOT NULL,
  `Attemps` int NOT NULL,
  `Blocked` bit DEFAULT NULL,
  PRIMARY KEY (`Debitcard_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `debitcard`
--

LOCK TABLES `debitcard` WRITE;
/*!40000 ALTER TABLE `debitcard` DISABLE KEYS */;
INSERT INTO `debitcard` VALUES ('AAAAAAAA','2332',0,false),('BBBBBBBB','4921',0,false),('CCCCCCCC','9291',0,true),('DDDDDDDD','8226',0,0),('EEEEEEEE','9482',0,false),('6937E7C1','1863',0,false),('24558E2B','5555',0,false),('E423D02A','0001',0,false),('792CE9C1','0002',0,false),('221FD534','1234',0,false),('D93AEAC2','696969',0,false);
/*!40000 ALTER TABLE `debitcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transactions` (
  `Transactions_id` int NOT NULL AUTO_INCREMENT,
  `TransactionType` varchar(255) NOT NULL,
  `Amount` varchar(255) NOT NULL,
  `TransactionDate` varchar(255) NOT NULL,
  PRIMARY KEY (`Transactions_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (1,'PIN','00,00','0000-00-00 00:00:00'),(2,'PIN','00,00','0000-00-00 00:00:00'),(3,'PIN','00,00','0000-00-00 00:00:00'),(4,'PIN','00,00','0000-00-00 00:00:00'),(5,'PIN','00,00','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-19 14:23:16
