-- MySQL dump 10.13  Distrib 5.7.28, for Linux (x86_64)
--
-- Host: localhost    Database: db_port
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.8-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `master_dock`
--

DROP TABLE IF EXISTS `master_dock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `master_dock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dock_code` varchar(45) DEFAULT NULL,
  `port_id` int(11) DEFAULT NULL,
  `status_id` int(11) NOT NULL,
  `ship_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_master_dock_master_port_idx` (`port_id`),
  KEY `fk_master_dock_master_status1_idx` (`status_id`),
  KEY `fk_master_dock_master_ship1_idx` (`ship_id`),
  CONSTRAINT `fk_master_dock_master_port` FOREIGN KEY (`port_id`) REFERENCES `master_port` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_master_dock_master_ship1` FOREIGN KEY (`ship_id`) REFERENCES `master_ship` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_master_dock_master_status1` FOREIGN KEY (`status_id`) REFERENCES `master_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `master_dock`
--

LOCK TABLES `master_dock` WRITE;
/*!40000 ALTER TABLE `master_dock` DISABLE KEYS */;
INSERT INTO `master_dock` VALUES (1,'DKH-001',1,4,NULL),(2,'DKC-001',2,4,NULL);
/*!40000 ALTER TABLE `master_dock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `master_port`
--

DROP TABLE IF EXISTS `master_port`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `master_port` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `port_code` varchar(45) NOT NULL,
  `port_name` varchar(45) DEFAULT NULL,
  `master_port_id` int(11) NOT NULL,
  `master_port_port_code` varchar(45) NOT NULL,
  PRIMARY KEY (`id`,`port_code`),
  KEY `fk_master_port_master_port1_idx` (`master_port_id`,`master_port_port_code`),
  CONSTRAINT `fk_master_port_master_port1` FOREIGN KEY (`master_port_id`, `master_port_port_code`) REFERENCES `master_port` (`id`, `port_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `master_port`
--

LOCK TABLES `master_port` WRITE;
/*!40000 ALTER TABLE `master_port` DISABLE KEYS */;
INSERT INTO `master_port` VALUES (1,'PORT001','Himalaya Port',0,''),(2,'PORT002','Cilegon Port',0,''),(4,'PORT003','Bayah Port',0,''),(12,'PORT004','Pike Port',0,''),(14,'PORT005','Himalaya 1 Port',0,''),(15,'PORT006','Bayah 2 Port',0,''),(16,'PORT007','Bayah 3 Port',0,''),(17,'PORT008','Bayah 3 Port',0,''),(18,'PORT009','majalengka Port',0,'');
/*!40000 ALTER TABLE `master_port` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `master_ship`
--

DROP TABLE IF EXISTS `master_ship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `master_ship` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code_ship` varchar(45) NOT NULL,
  `ship_name` varchar(45) DEFAULT NULL,
  `captain` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `master_ship`
--

LOCK TABLES `master_ship` WRITE;
/*!40000 ALTER TABLE `master_ship` DISABLE KEYS */;
INSERT INTO `master_ship` VALUES (1,'SH001','Ant Ship','budiman'),(2,'SH002','Leon Ship','budimakmur'),(4,'SH003','Tiger Ship','burhan'),(7,'SH004','Jungle Ship','suntoro'),(8,'SH005','Fish Ship','kuntori');
/*!40000 ALTER TABLE `master_ship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `master_status`
--

DROP TABLE IF EXISTS `master_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `master_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `master_status`
--

LOCK TABLES `master_status` WRITE;
/*!40000 ALTER TABLE `master_status` DISABLE KEYS */;
INSERT INTO `master_status` VALUES (1,'sailing'),(2,'docked'),(3,'unloading'),(4,'available'),(5,'not available'),(6,'in repair');
/*!40000 ALTER TABLE `master_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `master_transaction`
--

DROP TABLE IF EXISTS `master_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `master_transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_code` varchar(12) NOT NULL,
  `transaction_date` date NOT NULL,
  `port_id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL,
  `ship_id` int(11) NOT NULL,
  `load` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_master_transaction_master_port1_idx` (`port_id`),
  KEY `fk_master_transaction_master_status1_idx` (`status_id`),
  KEY `fk_master_transaction_master_ship1_idx` (`ship_id`),
  CONSTRAINT `fk_master_transaction_master_port1` FOREIGN KEY (`port_id`) REFERENCES `master_port` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_master_transaction_master_ship1` FOREIGN KEY (`ship_id`) REFERENCES `master_ship` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_master_transaction_master_status1` FOREIGN KEY (`status_id`) REFERENCES `master_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `master_transaction`
--

LOCK TABLES `master_transaction` WRITE;
/*!40000 ALTER TABLE `master_transaction` DISABLE KEYS */;
INSERT INTO `master_transaction` VALUES (1,'T-001','2019-11-23',2,2,1,10),(2,'T-001','2019-11-23',2,3,1,10),(3,'T-001','2019-11-23',2,1,1,10);
/*!40000 ALTER TABLE `master_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `master_user`
--

DROP TABLE IF EXISTS `master_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `master_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `master_user`
--

LOCK TABLES `master_user` WRITE;
/*!40000 ALTER TABLE `master_user` DISABLE KEYS */;
INSERT INTO `master_user` VALUES (1,'waisal','123'),(2,'samsul','123');
/*!40000 ALTER TABLE `master_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-23 18:10:12
