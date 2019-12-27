CREATE DATABASE  IF NOT EXISTS `db1` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `db1`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: db1
-- ------------------------------------------------------
-- Server version	5.1.45-community

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
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (28);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_details`
--

DROP TABLE IF EXISTS `login_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email_id` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_details`
--

LOCK TABLES `login_details` WRITE;
/*!40000 ALTER TABLE `login_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `login_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news_search`
--

DROP TABLE IF EXISTS `news_search`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news_search` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `searchwords` varchar(255) DEFAULT NULL,
  `analystdetails_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKso6ywqn5ft66s5ekk8cxki5dx` (`analystdetails_id`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news_search`
--

LOCK TABLES `news_search` WRITE;
/*!40000 ALTER TABLE `news_search` DISABLE KEYS */;
INSERT INTO `news_search` VALUES (1,'friends',4),(2,'mobile',4),(3,'apple',4),(6,'food',32),(7,'modi',32),(8,'movie',32),(11,'samsung',24),(12,'phone',24),(14,'marvel',32),(16,'water',32),(26,'bee',24),(22,'aaaaa',32),(27,'ear',24),(24,'environment',32),(29,'tcs',32),(28,'electricity',24);
/*!40000 ALTER TABLE `news_search` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `analyst_details`
--

DROP TABLE IF EXISTS `analyst_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `analyst_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active_status` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `roles` int(11) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `analyst_details`
--

LOCK TABLES `analyst_details` WRITE;
/*!40000 ALTER TABLE `analyst_details` DISABLE KEYS */;
INSERT INTO `analyst_details` VALUES (36,'','max@gmail.com','$2a$10$HB9epuIusLSj7JLLyoTV3.akLO5TXiigmc.52QeYRovVDJkC6NQMe',2,'max'),(3,'\0','a@gmail.com','$2a$10$qNsA1dADuyGQo9BbOhCU2.zEoYVxLRdBrVs18IBH3u.Vdw.7EUdE2',2,'ayushi'),(4,'','hat@gmail.com','$2a$10$75BFiPlvowZ2WvBHHe5Yw.1euKkzMZ.DX4NlfmMuRZTh4LwWG1Moa',2,'hat'),(6,'\0','goat@gmail.com','$2a$10$qOfai0/FjRU99GblI.KJEuUr5Il6SwpbW8c0f7qYpLoaPSktg1n36',2,'goat'),(7,'\0','van@van.com','$2a$10$rJmnJC4ZUd.fRf97mGJldO0XYTa4ztqVbEn8OhFzuyqoILUwqOO9u',2,'van'),(8,'\0','vans@van.com','$2a$10$UKIoqtw.bu5A.rsZ8RbQfOANViZAd2WrnBgrKPT/Ewwtw5rMX6ufG',2,'van'),(11,'','aass@gmail.com','$2a$10$EPy2pALBq2rcM4ioPoYLduJCoYXrrYLYmE.WSKW5wV7zPCgrhK.rC',2,'aass'),(12,'','aa@gmail.com','$2a$10$VOVBMqBN20wWQKzcchiUaOaS2FLH0Nu5lyB8HTKF7m9fZL4f6F20i',2,'hat'),(26,'','ghgvhbgjgh@gmail.com','$2a$10$sxHeLiBxm9ZD9M96vxKHL.aDeaFKnGugDbnKjZW3JS5/1Hs8yYDTi',2,'aass'),(25,'\0','ghgjgh@gmail.com','$2a$10$4qeKLe7HEdC7P6pgA3yenubSteUeZOwSz0rqHfsE.btN79ZLGPHou',2,'aass'),(16,'\0','acassyt@gmail.com','$2a$10$KgYAbVY835h9ABYYkCFtLe3an0wanKcoEN4cTXBQArhDRjAP9body',2,'aass'),(17,'','acdassyt@gmail.com','$2a$10$l9LiRhxv5hBTjQvwbuiBNOz7xjmpbLB2bagyfNPg06VEfm8m5FwLq',2,'aass'),(37,'','ccc@gmail.com','$2a$10$ExSlKEhwIn9VfFFf9nR.yuIDBIE8LXb2ob82VMR.oTmnJBdmVy/XK',2,'ccc'),(24,'','admin@gmail.com','$2a$10$BVQPfUPJhly0G7UNUULJceqTJS.g4wHsN7Uelvt.2WxJkKi6vVM8a',1,'admin'),(23,'\0','acdassytyyy@gmail.com','$2a$10$ugToky95wNewGFHqc58K1eSUXhDeCr1/kL1AiyedPwoGW0OSiLLeC',2,'aass'),(28,'','ddd@gmail.com','$2a$10$fXsCxCzvegmUhRCfUb1sS.wEGJmHPRdnvRR5o2fHQ2ZYGpqCqbwp6',2,'ddd'),(35,'','ssss@ss.com','$2a$10$Xbo86gMmJTMNqW6Psmq02eJt1xCiFX2n9DBAkmUSWdwKgxdOo2rOq',2,'sss'),(34,'','dos@gmail.com','$2a$10$FsmOqVgRsUSoveSCk7kAnerAnKIH5rS6sJFdRhRww/rkZ9e65x7fW',2,'dos'),(32,'','ayushi@gmail.com','$2a$10$ayC.BfANsmtCe/4fR8mAre0cFNwcyLxD8aXYivooy5Ykscf3xDS2y',2,'ayushi');
/*!40000 ALTER TABLE `analyst_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-06 15:06:55
