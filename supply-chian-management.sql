-- MySQL dump 10.13  Distrib 8.1.0, for macos13.3 (x86_64)
--
-- Host: localhost    Database: supplydb
-- ------------------------------------------------------
-- Server version	8.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `product_id` int DEFAULT NULL,
                             `stock` int DEFAULT '0',
                             `damagedGoods_count` int DEFAULT '0',
                             `valuation` varchar(255) DEFAULT 'None',
                             `expiredGoods_count` int DEFAULT '0',
                             PRIMARY KEY (`id`),
                             KEY `ProductID` (`product_id`),
                             CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (1,1,112,5,'Good',0),(2,2,9998,16,'None',0),(3,3,0,0,'None',0),(4,4,0,0,'None',0),(5,5,0,0,'None',0),(6,6,0,0,'None',0),(7,7,0,0,'None',0),(8,8,5,1,'None',0),(9,9,0,0,'None',0),(10,10,0,0,'None',0),(11,11,11,0,'None',0),(12,12,0,0,'None',0),(13,13,91,10,'normal',0);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `user_id` int DEFAULT NULL,
                         `total_amount` decimal(10,2) NOT NULL,
                         `product_id` int DEFAULT NULL,
                         `count` int NOT NULL,
                         `order_type` enum('Create','Return') NOT NULL DEFAULT 'Create',
                         `status` enum('Created','In_Progress','Accepted','Rejected','Done') NOT NULL DEFAULT 'Created',
                         `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (`id`),
                         KEY `UserID` (`user_id`),
                         KEY `product_id` (`product_id`),
                         CONSTRAINT `order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                         CONSTRAINT `order_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,2,1000.00,1,100,'Create','Done','2023-11-17 13:25:06','2023-11-17 14:10:42'),(2,4,3750.00,5,15,'Create','Created','2023-11-17 13:25:06','2023-11-17 13:25:27'),(3,2,31.50,13,21,'Create','In_Progress','2023-11-17 13:25:06','2023-11-17 13:25:27'),(4,2,150.00,1,15,'Create','Created','2023-11-17 13:25:06','2023-11-17 13:25:27'),(5,2,605.00,11,11,'Create','Done','2023-11-17 13:25:06','2023-11-17 14:10:35'),(6,2,6000.00,4,12,'Create','Rejected','2023-11-17 13:25:06','2023-11-17 14:09:20'),(7,4,3250.00,5,13,'Create','In_Progress','2023-11-17 13:25:06','2023-11-17 14:17:35'),(8,3,28.00,7,14,'Create','Created','2023-11-17 13:25:06','2023-11-17 13:25:27'),(9,2,250.00,12,100,'Create','In_Progress','2023-11-17 13:25:06','2023-11-17 14:32:43'),(10,2,151.50,13,101,'Create','Done','2023-11-17 13:25:06','2023-11-17 14:10:31'),(11,3,6000.00,8,5,'Create','Done','2023-11-17 13:25:06','2023-11-17 14:10:26'),(12,3,2400.00,9,6,'Create','Rejected','2023-11-17 13:25:06','2023-11-17 14:10:01'),(13,2,18000.00,3,18,'Create','Created','2023-11-17 13:25:06','2023-11-17 13:25:27'),(14,4,427.50,6,19,'Create','Created','2023-11-17 13:25:06','2023-11-17 13:25:27'),(15,3,210800.00,10,1000,'Create','Created','2023-11-17 13:25:06','2023-11-17 13:25:27'),(16,2,12000.00,2,10000,'Create','Done','2023-11-17 13:25:06','2023-11-17 14:14:44'),(17,2,2000.00,3,2,'Create','Rejected','2023-11-17 13:25:55','2023-11-17 13:25:55'),(18,2,7.50,13,5,'Return','Rejected','2023-11-17 14:11:28','2023-11-17 14:15:45'),(19,3,1200.00,8,1,'Return','Created','2023-11-17 14:14:18','2023-11-17 14:14:18'),(20,2,2.40,2,2,'Return','Done','2023-11-17 14:15:15','2023-11-17 14:16:35'),(21,4,247.50,6,11,'Create','In_Progress','2023-11-17 14:17:08','2023-11-17 14:17:31'),(22,2,120.00,1,12,'Create','Done','2023-11-17 14:32:23','2023-11-17 14:32:55'),(23,2,520.00,1,52,'Create','Created','2023-11-17 14:33:24','2023-11-17 14:33:24'),(24,2,20.00,1,2,'Return','Accepted','2023-11-17 14:34:00','2023-11-17 14:34:33'),(25,2,100.00,1,10,'Create','Done','2023-05-03 15:00:00','2023-05-03 18:00:00'),(26,2,100.00,1,10,'Create','Done','2023-06-03 15:00:00','2023-06-03 18:00:00'),(27,2,100.00,1,10,'Create','Done','2023-07-03 15:00:00','2023-07-03 18:00:00'),(28,2,100.00,1,10,'Create','Done','2023-07-03 15:00:00','2023-08-03 18:00:00'),(29,2,100.00,1,10,'Create','Done','2023-07-03 15:00:00','2023-09-03 18:00:00'),(30,2,100.00,1,10,'Create','Done','2023-08-03 15:00:00','2023-10-03 18:00:00'),(31,2,110.00,1,11,'Create','Done','2023-09-03 15:00:00','2023-09-03 18:00:00');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `product_name` varchar(255) NOT NULL,
                           `description` varchar(255) DEFAULT NULL,
                           `price` decimal(10,2) NOT NULL,
                           `user_id` int NOT NULL,
                           `stage` enum('On_Market','Off_Market') NOT NULL DEFAULT 'Off_Market',
                           PRIMARY KEY (`id`),
                           KEY `userId` (`user_id`),
                           CONSTRAINT `product_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Clothes','beautiful',10.00,2,'Off_Market'),(2,'water','pure!!!',1.20,2,'Off_Market'),(3,'laptop','mac',1000.00,2,'On_Market'),(4,'PC','dell allienware',500.00,2,'Off_Market'),(5,'PC','used',250.00,4,'Off_Market'),(6,'mouse','brand new',22.50,4,'Off_Market'),(7,'cola','coca original taste',2.00,3,'Off_Market'),(8,'iphone','14 pro',1200.00,3,'Off_Market'),(9,'iphone','13 used',400.00,3,'Off_Market'),(10,'watch','brand new smart watch',210.80,3,'Off_Market'),(11,'Computer Science','a good book',55.00,2,'Off_Market'),(12,'floss','oral-B advanced',2.50,2,'Off_Market'),(13,'floss','oral-B normal',1.50,2,'Off_Market');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `username` varchar(255) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `email` varchar(255) NOT NULL,
                        `contact_name` varchar(255) DEFAULT NULL,
                        `phone` varchar(255) DEFAULT NULL,
                        `user_role` enum('admin','supplier','distributor','manufacturer') NOT NULL,
                        `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                        `address` varchar(255) DEFAULT NULL,
                        `image_path` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','21232f297a57a5a743894a0e4a801fc3','zzhang14@scu.edu','Administrator','1234567890','admin','2023-11-16 09:33:16','500 El Camino Real, Santa Clara, CA 95053','https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png'),(2,'supplier1','81dc9bdb52d04dc20036dbd8313ed055','zz@outlook.com','Zhe','1234567890','supplier','2023-11-16 10:01:39','somewhere at sunnyvale','https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg'),(3,'distributor1','81dc9bdb52d04dc20036dbd8313ed055','124@qq.com','Yuan','1234567890','distributor','2023-11-16 10:05:22','santa clara','https://writestylesonline.com/wp-content/uploads/2018/11/Three-Statistics-That-Will-Make-You-Rethink-Your-Professional-Profile-Picture.jpg'),(4,'manufacturer','81dc9bdb52d04dc20036dbd8313ed055','cc@gmail.com','chenghuiwan','234345346346','manufacturer','2023-11-16 10:08:24','san jose','https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-17  7:04:23
