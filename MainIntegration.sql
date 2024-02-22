use MainIntegration;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: main_repository
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Dumping data for table `chapter`
--

LOCK TABLES `chapter` WRITE;
/*!40000 ALTER TABLE `chapter` DISABLE KEYS */;
INSERT INTO `chapter` VALUES (44,'Introduction to Programming2','2024-02-13',54),(46,'Introduction to databse','2024-02-13',57),(50,'a',NULL,54);
/*!40000 ALTER TABLE `chapter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `chapter_seq`
--

LOCK TABLES `chapter_seq` WRITE;
/*!40000 ALTER TABLE `chapter_seq` DISABLE KEYS */;
INSERT INTO `chapter_seq` VALUES (101);
/*!40000 ALTER TABLE `chapter_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (54,'PROGRAMMING',' programming is giving a set of instructions to a computer to execute. If you’ve ever cooked using a recipe before, you can think of yourself as the computer and the recipe’s author as a programmer. The recipe author provides you with a set of instructions that you read and then follow. The more complex the instructions, the more complex the result! Programming is the mental process of thinking up instructions to give to a machine (like a computer. Coding is the process of transforming those ideas into a written language that a computer can understand.','2024-02-13','2024-02-13',NULL),(57,'Database','A database is an organized collection of data stored in a computer system and usually controlled by a database management system (DBMS). The data in common databases is modeled in tables, making querying and processing efficient. Structured query language (SQL) is commonly used for data querying and writing.The Database is an essential part of our life. We encounter several activities that involve our interaction with databases, for example in the bank, in the railway station, in school, in a grocery store, etc. These are the instances where we need to store a large amount of data in one place and fetch these data easily. ','2024-02-13','2024-02-13',NULL);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `instructor`
--

LOCK TABLES `instructor` WRITE;
/*!40000 ALTER TABLE `instructor` DISABLE KEYS */;
INSERT INTO `instructor` VALUES (1,'gem','ramirez','gramirez','gramirez@gmail.com','gemgem','','','09292123765'),(2,'mae','ramirez','mramirez','mramirez@gmail.com','gemgem','','','09292123765'),(52,'','gem','gem','12','12','','','gem'),(53,'try','try','try','try@gmail.com','try','','','09201202130');
/*!40000 ALTER TABLE `instructor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `instructor_seq`
--

LOCK TABLES `instructor_seq` WRITE;
/*!40000 ALTER TABLE `instructor_seq` DISABLE KEYS */;
INSERT INTO `instructor_seq` VALUES (151);
/*!40000 ALTER TABLE `instructor_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (1,' What is C Programming','C is a general-purpose programming language created by Dennis Ritchie at the Bell Laboratories in 1972. It is a very popular language, despite being old. The main reason for its popularity is because it is a fundamental language in the field of computer science. C is strongly associated with UNIX, as it was developed to write the UNIX operating system.','C FILE','https://www.w3schools.com/c/c_intro.php',44);
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `topic_seq`
--

LOCK TABLES `topic_seq` WRITE;
/*!40000 ALTER TABLE `topic_seq` DISABLE KEYS */;
INSERT INTO `topic_seq` VALUES (101);
/*!40000 ALTER TABLE `topic_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'xokij58320@seosnaps.com','Xo',_binary '','Kij','$2a$10$pcZB73TcAy1J.qoNHlmUY.6./7k5oeiyfjvJxp4NxSuKMIo1ymqs2',NULL,'STUDENT','Xokij'),(2,'riwecon223@rohoza.com','Riwe',_binary '\0','Con','$2a$10$AUkwO7L8TQQ9gFuQhQ/Wxed85qur5Ndhx1V89uR20D/5tWymDVDXC',NULL,'STUDENT','Riwecon'),(3,'yihexaj750@rohoza.com','Yi',_binary '\0','Hexaj','$2a$10$wZSsVaq11EbklyX3znT1UOUKWvCx/03R.0ju3/FZmHB0AZ94OW2SS',NULL,'STUDENT','Yihejax'),(4,'kotawit728@seosnaps.com','Kot',_binary '','Awit','$2a$10$Fs/l40X/XhxrjPbpipkHzu07i0Mi2UGT2gztlPNdEQNOqa10nt396',NULL,'STUDENT','Kotawit');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `verification_code_entity`
--

LOCK TABLES `verification_code_entity` WRITE;
/*!40000 ALTER TABLE `verification_code_entity` DISABLE KEYS */;
INSERT INTO `verification_code_entity` VALUES (1,'2024-02-14 02:40:56.646000',1707878456646,'331392',1),(2,'2024-02-14 03:24:46.280000',1707881086280,'609901',2),(3,'2024-02-14 03:26:27.057000',1707881187057,'816872',3),(4,'2024-02-14 04:43:03.921000',1707887036761,'140513',4);
/*!40000 ALTER TABLE `verification_code_entity` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-21  9:50:32
