CREATE DATABASE  IF NOT EXISTS `projectkoala` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `projectkoala`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: projectkoala
-- ------------------------------------------------------
-- Server version	5.6.16

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
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `Id` int(11) NOT NULL,
  `Faculties_Id` int(11) NOT NULL,
  `FullName` varchar(50) DEFAULT NULL,
  `UserName` varchar(50) DEFAULT NULL,
  `PassWord` varchar(50) DEFAULT NULL,
  `Emai` varchar(50) DEFAULT NULL,
  `PhoneNumber` char(20) DEFAULT NULL,
  `IsActive` tinyint(1) DEFAULT NULL,
  `IsRoot` tinyint(1) DEFAULT NULL,
  `ChucVu` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`,`Faculties_Id`),
  KEY `fk_Accounts_Faculties1_idx` (`Faculties_Id`),
  CONSTRAINT `fk_Accounts_Faculties1` FOREIGN KEY (`Faculties_Id`) REFERENCES `faculties` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,1,'Nguyễn Thị Xuân','XuanNT','123456','xuan@gmail.com','+(84)169.825.3452',1,NULL,NULL),(2,2,'Lã Minh Nguyệt','NguyenLM','123456','nguyet@gmail.com','+(84)169.825.3453',1,NULL,NULL),(3,3,'Trần Thị Thu Thủy','ThuyTTT','123456','thuy@gmail.com','+(84)169.825.3454',0,NULL,NULL),(4,4,'Văn Thị Hải Yến','YenVTH','123456','yen@gmail.com','+(84)169.825.3455',1,NULL,NULL),(5,1,'admin','admin','admin','admin','adimn',1,1,NULL);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buslist`
--

DROP TABLE IF EXISTS `buslist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buslist` (
  `idBusList` int(11) NOT NULL,
  `idStudents` int(11) NOT NULL,
  `LuotDi` varchar(45) NOT NULL,
  `GhiChu` varchar(500) DEFAULT NULL,
  `TienXe` varchar(45) NOT NULL,
  `DiaChi` varchar(120) NOT NULL,
  `IsActive` int(11) DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  PRIMARY KEY (`idBusList`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buslist`
--

LOCK TABLES `buslist` WRITE;
/*!40000 ALTER TABLE `buslist` DISABLE KEYS */;
INSERT INTO `buslist` VALUES (1,5,'1','đức sơn đẹp trai','45000000','dia chi 84 vuon ',1,'2013-08-01','2013-09-01');
/*!40000 ALTER TABLE `buslist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classes` (
  `Id` int(11) NOT NULL,
  `Faculties_Id` int(11) NOT NULL,
  `Semesters` int(11) NOT NULL,
  `Levels_Id` int(11) NOT NULL,
  `Year` year(4) DEFAULT NULL,
  `NameClass` varchar(45) DEFAULT NULL,
  `TeacherClass` varchar(45) DEFAULT NULL,
  `MaxNumber` int(11) DEFAULT NULL,
  `IsActive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Id`,`Faculties_Id`),
  KEY `fk_Classes_Faculties1_idx` (`Faculties_Id`),
  KEY `fk_Classes_Semesters1_idx` (`Semesters`),
  KEY `fk_Classes_Levels1_idx` (`Levels_Id`),
  CONSTRAINT `fk_Classes_Faculties1` FOREIGN KEY (`Faculties_Id`) REFERENCES `faculties` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Classes_Levels1` FOREIGN KEY (`Levels_Id`) REFERENCES `levels` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Classes_Semesters1` FOREIGN KEY (`Semesters`) REFERENCES `semesters` (`SemesterNumber`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES (1,1,1,1,2013,'SUN','Lã Thị Minh',50,1),(2,1,1,1,2013,'SUNSHINE_2','Hoàng Thị Ngọc Lan',50,1),(3,1,1,1,2013,'SUNSHINE_3','Bùi Thị Lan Anh',50,0),(4,1,1,2,2013,'BEEHIVE_1','Trần Thị Hà',50,1),(5,1,1,2,2013,'BEEHIVE_2','Nguyễn Ngọc Anh',50,1),(6,1,1,2,2013,'BEEHIVE_3','Phan Kim Liên',50,0),(7,1,1,3,2013,'CHRYSALIS_1','Nguyễn Thị Thảo',50,1),(8,1,1,3,2013,'CHRYSALIS_2','Doãn Thị Trang',50,1),(9,1,1,4,2013,'KINDERGARTEN_1','Trương Tam Phong',50,1),(10,1,1,4,2013,'KINDERGARTEN_2','Lã Thị Thu Thảo',50,1),(11,1,1,1,2014,'SB','Dương Quá',50,0),(12,1,2,1,2014,'SunShine_Blue','Hồ Thị Niên',20,0);
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classes_has_students`
--

DROP TABLE IF EXISTS `classes_has_students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classes_has_students` (
  `Classes_Id` int(11) NOT NULL,
  `Students_Id` int(11) NOT NULL,
  `Faculties_Id` int(11) NOT NULL,
  PRIMARY KEY (`Classes_Id`,`Students_Id`,`Faculties_Id`),
  KEY `fk_Classes_has_Students_Students1_idx` (`Students_Id`),
  KEY `fk_Classes_has_Students_Classes1_idx` (`Classes_Id`),
  KEY `fk_Classes_has_Students_Faculties1_idx` (`Faculties_Id`),
  CONSTRAINT `fk_Classes_has_Students_Classes1` FOREIGN KEY (`Classes_Id`) REFERENCES `classes` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Classes_has_Students_Faculties1` FOREIGN KEY (`Faculties_Id`) REFERENCES `faculties` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Classes_has_Students_Students1` FOREIGN KEY (`Students_Id`) REFERENCES `students` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes_has_students`
--

LOCK TABLES `classes_has_students` WRITE;
/*!40000 ALTER TABLE `classes_has_students` DISABLE KEYS */;
INSERT INTO `classes_has_students` VALUES (1,1,1),(1,2,1),(1,3,1),(1,4,1),(1,5,1),(2,7,1),(3,12,1),(4,16,1),(4,17,1),(4,18,1),(4,19,1),(4,20,1),(5,21,1),(5,22,1),(5,23,1),(5,24,1),(5,25,1),(6,26,1),(1,27,1),(6,28,1),(1,29,1),(6,30,1),(7,31,1),(1,32,1),(1,33,1),(7,34,1),(7,35,1),(1,36,1),(1,40,1),(9,41,1),(9,42,1),(9,45,1),(4,46,1),(4,48,1),(10,49,1),(4,50,1),(1,51,1),(1,52,1);
/*!40000 ALTER TABLE `classes_has_students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cost`
--

DROP TABLE IF EXISTS `cost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cost` (
  `Id` int(11) NOT NULL,
  `Faculties_Id` int(11) NOT NULL,
  `Semesters` int(11) NOT NULL,
  `NameCost` varchar(45) NOT NULL,
  `Amount` double DEFAULT NULL,
  `year` year(4) NOT NULL,
  `StartDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  PRIMARY KEY (`Id`,`Faculties_Id`,`Semesters`),
  KEY `fk_Cost_Faculties1_idx` (`Faculties_Id`),
  KEY `fk_Cost_Semesters1_idx` (`Semesters`),
  CONSTRAINT `fk_Cost_Faculties1` FOREIGN KEY (`Faculties_Id`) REFERENCES `faculties` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cost_Semesters` FOREIGN KEY (`Semesters`) REFERENCES `semesters` (`SemesterNumber`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cost`
--

LOCK TABLES `cost` WRITE;
/*!40000 ALTER TABLE `cost` DISABLE KEYS */;
INSERT INTO `cost` VALUES (1,1,1,'Phí Đặt Cọc',-5000000,2013,'2014-01-01','2014-01-01'),(2,1,4,'Xe Bus Ha Noi',-5000,2013,'2014-01-01','2014-01-01'),(3,1,2,'Học Phí',24000000,2013,'2014-01-01','2014-01-01'),(4,2,1,'Học Phí',24000000,2013,'2014-01-01','2014-01-01'),(5,2,1,'Học Phí',3000000,2013,'2014-01-01','2014-01-01'),(6,3,2,'Học Phí',24000000,2013,'2014-01-01','2014-01-01'),(7,3,2,'Xe Bus',500000,2013,'2014-01-01','2014-01-01'),(8,4,4,'Học Phí',24000000,2013,'2014-01-01','2014-01-01'),(9,4,4,'Tham Quan',500000,2013,'2014-01-01','2014-01-01'),(11,1,1,'Học Múa',1000000,2013,'2014-01-01','2014-01-01'),(12,1,1,'Học Đàn',5000000,2013,'2014-01-01','2014-01-01'),(13,1,2,'Phí Trông Muộn',20000,2014,'2014-01-01','2014-01-01'),(14,2,1,'Phí Trông Muộn',20000,2013,'2014-01-01','2014-01-01'),(15,3,1,'Phí Trông Muộn',20000,2013,'2014-01-01','2014-01-01'),(16,4,1,'Phí Trông Muộn',20000,2013,'2014-01-01','2014-01-01'),(17,1,1,'Đặt cọc',-123445654,2013,'2014-01-01','2014-01-01'),(18,1,1,'Xe Bus',122433,2013,'2014-01-01','2014-01-01'),(19,1,2,'Phí trông muộn',30000,2013,'2014-01-01','2014-01-01'),(20,1,3,'Phí trông muộn',25000,2013,'2014-01-01','2014-01-01'),(21,1,3,'Phí Đặt Cọc',-6000000,2013,'2014-01-01','2014-01-01'),(22,1,4,'Phí Trông Muộn',30000,2014,'2014-01-01','2014-01-01'),(23,1,1,'Phí Kỳ Hè',1000000,2014,'2014-01-01','2014-01-01');
/*!40000 ALTER TABLE `cost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculties`
--

DROP TABLE IF EXISTS `faculties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faculties` (
  `Id` int(11) NOT NULL,
  `Address` varchar(200) DEFAULT NULL,
  `PhoneNumbers` char(20) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `UpdateDate` date DEFAULT NULL,
  `CreateDate` date DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculties`
--

LOCK TABLES `faculties` WRITE;
/*!40000 ALTER TABLE `faculties` DISABLE KEYS */;
INSERT INTO `faculties` VALUES (1,'340 Bà triệu, Quận Hai Bà Trưng','+(84.4) 3772.3060','BaTrieu@gmail.com','2013-03-08','2013-03-08','Koala House Bà Triệu'),(2,'261 Hoàng Ngân, Quận Cầu Giấy','+(84.4) 3772.3060','HoangNgan@gmail.com','2013-03-08','2013-03-08','Koala House Hoàng Ngân'),(3,'5 Phan Kế Bính, Quận Ba Đình','+(84.4) 3974.7617','PhanKeBinh@gmail.com','2013-03-08','2013-03-08','Koala House Phan Kế Bính'),(4,'3 Nguyễn Huy Tự, Quận Hai Bà Trưng','+ (84.4) 3972.8913','NguyenHuyTu@gmail.com','2013-03-08','2013-03-08','Koala House Nguyễn Huy Tự');
/*!40000 ALTER TABLE `faculties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lateday`
--

DROP TABLE IF EXISTS `lateday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lateday` (
  `Id` int(11) NOT NULL,
  `Faculties_Id` int(11) NOT NULL,
  `Students_Id` int(11) NOT NULL,
  `LateDate` date DEFAULT NULL,
  `Time` int(11) DEFAULT '0',
  `isActive` int(11) DEFAULT NULL,
  `Semester` int(11) DEFAULT NULL,
  `year` year(4) DEFAULT NULL,
  PRIMARY KEY (`Id`,`Faculties_Id`),
  KEY `fk_LateDay_Faculties1_idx` (`Faculties_Id`),
  KEY `fk_LateDay_Students1_idx` (`Students_Id`),
  CONSTRAINT `fk_LateDay_Faculties1` FOREIGN KEY (`Faculties_Id`) REFERENCES `faculties` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_LateDay_Students1` FOREIGN KEY (`Students_Id`) REFERENCES `students` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lateday`
--

LOCK TABLES `lateday` WRITE;
/*!40000 ALTER TABLE `lateday` DISABLE KEYS */;
INSERT INTO `lateday` VALUES (1,1,1,'2013-03-08',50,0,1,2013),(3,1,1,'2013-03-10',10,0,1,2013),(4,1,2,'2013-03-08',30,1,NULL,NULL),(5,1,2,'2013-03-09',20,1,NULL,NULL),(6,1,3,'2014-03-08',20,0,1,2013),(7,1,3,'2014-03-09',10,0,1,2013),(8,1,4,'2014-03-10',20,1,NULL,NULL),(15,1,23,'2014-01-01',10,0,1,2013),(21,1,24,'2014-01-01',10,1,NULL,NULL),(22,1,23,'2014-01-04',30,0,1,2013),(23,1,17,'2014-01-01',12,1,NULL,NULL),(25,1,7,'2014-01-01',10,0,3,2013),(26,1,29,'2014-01-01',101,1,NULL,NULL),(27,1,16,'2014-01-01',10,0,1,2013),(28,1,16,'2014-01-03',23,1,NULL,NULL),(29,1,19,'2014-01-01',10,1,1,2013),(30,1,33,'2014-01-01',10,0,1,2013),(31,1,7,'2014-02-03',20,0,3,2013),(32,1,5,'2014-01-01',10,0,1,2013),(33,1,5,'2014-01-01',41,1,NULL,NULL),(34,1,35,'2014-01-01',12,0,1,2013),(36,1,35,'2014-01-01',10,1,2,2013),(37,1,34,'2014-01-01',10,0,1,2013),(39,1,41,'2014-01-01',10,1,1,2013),(40,1,41,'2014-01-03',20,1,1,2013),(41,1,36,'2014-01-01',10,0,1,2013),(42,1,36,'2014-01-02',12,0,1,2013),(43,1,36,'2015-01-06',25,0,2,2013),(46,1,34,'2014-01-01',22,0,1,2013),(47,1,41,'2014-01-01',40,1,NULL,NULL),(48,1,7,'2014-01-01',12,1,NULL,NULL),(49,1,23,'2014-01-01',55,1,NULL,NULL),(55,1,1,'2014-02-03',20,1,2,2014),(56,1,1,'2014-05-01',23,0,4,2014),(57,1,20,'2014-01-01',12,1,1,2014),(58,1,20,'2014-03-03',11,1,3,2014),(59,1,20,'2014-02-02',12,0,2,2014),(60,1,20,'2014-02-04',20,0,2,2014),(61,1,1,'2014-02-08',69,1,2,2014),(62,1,27,'2014-01-01',69,1,1,2014);
/*!40000 ALTER TABLE `lateday` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `learnsummer`
--

DROP TABLE IF EXISTS `learnsummer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `learnsummer` (
  `id` int(11) NOT NULL,
  `idStudents` int(11) NOT NULL,
  `tuanhoc` varchar(60) NOT NULL,
  `soTuanHoc` int(11) NOT NULL,
  `class` varchar(60) NOT NULL,
  `IsActive` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `learnsummer`
--

LOCK TABLES `learnsummer` WRITE;
/*!40000 ALTER TABLE `learnsummer` DISABLE KEYS */;
INSERT INTO `learnsummer` VALUES (1,5,'2,3,4',3,'aaa',1);
/*!40000 ALTER TABLE `learnsummer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leaves`
--

DROP TABLE IF EXISTS `leaves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leaves` (
  `Id` int(11) NOT NULL,
  `Faculties_Id` int(11) NOT NULL,
  `Students_Id` int(11) NOT NULL,
  `StartDate` varchar(10) DEFAULT NULL,
  `EndDate` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`Id`,`Faculties_Id`),
  KEY `fk_Leaves_Faculties1_idx` (`Faculties_Id`),
  KEY `fk_Leaves_Students1_idx` (`Students_Id`),
  CONSTRAINT `fk_Leaves_Faculties1` FOREIGN KEY (`Faculties_Id`) REFERENCES `faculties` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Leaves_Students1` FOREIGN KEY (`Students_Id`) REFERENCES `students` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leaves`
--

LOCK TABLES `leaves` WRITE;
/*!40000 ALTER TABLE `leaves` DISABLE KEYS */;
INSERT INTO `leaves` VALUES (1,1,1,'2013-03-08','2013-04-01'),(2,1,1,'2013-04-10','2013-04-20'),(3,1,2,'2013-03-08','2013-03-15'),(4,1,3,'2013-03-08','2013-03-15'),(5,1,4,'2013-03-08','2013-03-15'),(6,1,7,'2013-03-08','2013-03-15'),(8,1,5,'3-3-2014','7-3-2014'),(9,1,5,'1-1-2013','3-1-2013');
/*!40000 ALTER TABLE `leaves` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `levels`
--

DROP TABLE IF EXISTS `levels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `levels` (
  `Id` int(11) NOT NULL,
  `Name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `levels`
--

LOCK TABLES `levels` WRITE;
/*!40000 ALTER TABLE `levels` DISABLE KEYS */;
INSERT INTO `levels` VALUES (1,'NẮNG MAI (SUNSHINE)'),(2,'TỔ ONG (BEEHIVE)'),(3,'TỔ KIẾN (CHRYSALIS)'),(4,'MẪU GIÁO (KINDERGARTEN)');
/*!40000 ALTER TABLE `levels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipts`
--

DROP TABLE IF EXISTS `receipts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receipts` (
  `Id` int(11) NOT NULL,
  `Faculties_Id` int(11) NOT NULL,
  `Accounts_Id` int(11) NOT NULL,
  `Students_Id` int(11) NOT NULL,
  `No` int(11) DEFAULT NULL,
  `NamePayer` varchar(45) DEFAULT NULL,
  `NameCasher` varchar(45) DEFAULT NULL,
  `Number` double DEFAULT NULL,
  `InWords` varchar(50) DEFAULT NULL,
  `IsReturn` tinyint(1) DEFAULT NULL,
  `CreateDate` date DEFAULT NULL,
  `IsTransfer` tinyint(1) DEFAULT NULL,
  `Percent` int(11) DEFAULT NULL,
  `Reason` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`Id`,`Faculties_Id`),
  KEY `fk_Receipts_Faculties1_idx` (`Faculties_Id`),
  KEY `fk_Receipts_Accounts1_idx` (`Accounts_Id`),
  KEY `fk_Receipts_Students1_idx` (`Students_Id`),
  CONSTRAINT `fk_Receipts_Accounts1` FOREIGN KEY (`Accounts_Id`) REFERENCES `accounts` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Receipts_Faculties1` FOREIGN KEY (`Faculties_Id`) REFERENCES `faculties` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Receipts_Students1` FOREIGN KEY (`Students_Id`) REFERENCES `students` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipts`
--

LOCK TABLES `receipts` WRITE;
/*!40000 ALTER TABLE `receipts` DISABLE KEYS */;
INSERT INTO `receipts` VALUES (1,1,1,1,1,'phan văn A','trâm',50000000,NULL,NULL,'2014-04-09',1,10,'học sinh đặc biệt'),(3,4,1,3,1,'','',1000,NULL,NULL,'2012-01-01',0,10,'vi anh thich'),(4,4,1,7,4,'anh quyền','anh quyền nốt',10000000,NULL,NULL,'2012-01-01',0,0,'null'),(5,4,1,10,5,'theesanh quyền','em quyền',10000000,NULL,NULL,'2012-01-01',0,10,'thich thế'),(6,1,1,9,6,'','',10000000,NULL,NULL,'2012-01-01',0,0,'Không Có'),(7,1,1,1,7,'','',800000,NULL,NULL,'2012-01-01',0,0,'Không Có'),(8,1,1,12,8,'','',1233,NULL,NULL,'2012-01-01',0,0,'Không Có'),(9,1,1,1,9,'','',654654646,NULL,NULL,'2012-01-01',0,0,'Không Có'),(10,1,1,14,10,'','',123,NULL,NULL,'2012-01-01',0,0,'Không Có'),(11,1,1,1,11,'','',50000,NULL,NULL,'2015-01-01',0,0,'Không Có'),(12,1,1,5,12,'','',100000,NULL,NULL,'2015-01-01',0,0,'Không Có'),(13,1,1,21,13,'','',200000,NULL,NULL,'2015-01-01',0,15,''),(14,1,1,29,14,'','',800000,NULL,NULL,'2015-01-01',0,15,''),(15,1,1,24,15,'abc','def',24005000,NULL,NULL,'2014-04-17',0,0,'Không Có'),(16,1,1,29,16,'acv','aeg',11017000,NULL,NULL,'2014-04-17',0,0,'Không Có'),(17,1,1,29,17,'abc','ádg',1017000,NULL,NULL,'2014-04-17',0,0,'Không Có'),(18,1,1,29,18,'sdgasg','dsg',1017000,NULL,NULL,'2014-04-17',0,0,'Không Có'),(19,1,1,33,19,'áddsg','ágdga',5000000,NULL,NULL,'2014-04-17',0,0,'Không Có'),(20,1,1,5,20,'dfdagas','sgdgaag',8110000,NULL,NULL,'2014-04-17',0,0,'Không Có'),(21,1,1,5,21,'fsadff','daffa',5000000,NULL,NULL,'2014-04-17',0,0,'Không Có'),(22,1,1,5,22,'dssdg','sadg',20445654,NULL,NULL,'2014-04-20',0,0,'Không Có'),(23,1,1,1,23,'hj','adg',9868000,NULL,NULL,'2014-04-20',0,12,''),(24,1,1,35,24,'sadgsd','đsg',3611200,NULL,NULL,'2014-04-21',0,12,''),(25,1,1,36,25,'sagsd','asgs',440000,NULL,NULL,'2014-04-21',0,0,'Không Có'),(26,1,1,36,26,'ssdg','sgdsdg',750000,NULL,NULL,'2014-04-21',0,0,'Không Có'),(27,1,1,34,27,'sadg','asdgsgd',1,NULL,NULL,'2014-04-21',0,0,'Không Có'),(28,1,1,20,28,'fgh','fhjfvh',5000000,NULL,NULL,'2014-04-22',0,0,'Không Có'),(29,1,1,20,29,'sdg','sdgsa',19000000,NULL,NULL,'2014-04-22',0,0,'Không Có'),(30,1,1,20,30,'df','fgg',118445654,NULL,NULL,'2014-04-22',0,0,'Không Có'),(31,1,1,20,31,'fgdh','gfgh',5000000,NULL,NULL,'2014-04-22',0,0,'Không Có'),(32,1,1,20,32,'dsf','sadg',5000000,NULL,NULL,'2014-04-22',0,0,'Không Có'),(33,1,1,20,33,'erre','asgag',5000000,NULL,NULL,'2014-04-22',0,0,'Không Có'),(34,1,1,20,34,'sdfasdg','as',1,NULL,NULL,'2014-04-22',0,0,'Không Có'),(35,1,1,20,35,'dsf','aasdg',6000000,NULL,NULL,'2014-04-22',0,0,'Không Có'),(36,1,1,20,36,'kjasklf','oisjdgj',4360000,NULL,NULL,'2014-04-28',0,0,'Không Có'),(37,1,1,34,37,'abad','kjsad',24005000,NULL,NULL,'2014-04-28',0,0,'Không Có'),(38,1,1,7,38,'sas','safs',153195654,NULL,NULL,'2014-04-28',0,0,'Không Có'),(39,1,1,1,39,'fdsg','asdg',3187567,NULL,NULL,'2014-05-13',0,0,'Không Có'),(40,1,1,1,40,'ff','ggh',1000000,NULL,NULL,'2014-05-13',0,0,'Không Có'),(41,1,1,18,41,'quyen','quyen',30000000,NULL,NULL,'2014-05-14',0,0,'Không Có');
/*!40000 ALTER TABLE `receipts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semesters`
--

DROP TABLE IF EXISTS `semesters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `semesters` (
  `SemesterNumber` int(11) NOT NULL,
  `Faculties_Id` int(11) NOT NULL,
  `Year` year(4) NOT NULL,
  `StartDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  `IsActivity` int(11) DEFAULT NULL,
  PRIMARY KEY (`SemesterNumber`,`Faculties_Id`,`Year`),
  KEY `fk_Semesters_Faculties1_idx` (`Faculties_Id`),
  CONSTRAINT `fk_Semesters_Faculties1` FOREIGN KEY (`Faculties_Id`) REFERENCES `faculties` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semesters`
--

LOCK TABLES `semesters` WRITE;
/*!40000 ALTER TABLE `semesters` DISABLE KEYS */;
INSERT INTO `semesters` VALUES (1,1,2013,'2013-03-06','2013-04-10',0),(1,1,2014,'2013-09-01','2014-02-01',1),(1,2,2013,'2013-03-08','2013-03-08',0),(1,3,2013,'2013-03-08','2013-03-08',0),(1,4,2013,'2013-03-08','2013-03-08',0),(2,1,2013,'2013-04-11','2013-06-07',0),(2,1,2014,'2014-02-02','2014-03-01',1),(2,2,2013,'2013-03-08','2013-03-08',0),(2,3,2013,'2013-03-08','2013-03-08',0),(2,4,2013,'2013-03-08','2013-03-08',0),(3,1,2013,'2013-06-08','2013-08-07',0),(3,1,2014,'2014-03-02','2014-04-01',1),(3,2,2013,'2013-03-08','2013-03-08',0),(3,3,2013,'2013-03-08','2013-03-08',0),(3,4,2013,'2013-03-08','2013-03-08',0),(4,1,2013,'2013-08-08','2013-09-08',0),(4,1,2014,'2014-04-03','2014-06-01',1),(4,2,2013,'2013-03-08','2013-03-08',0),(4,3,2013,'2013-03-08','2013-03-08',0),(4,4,2013,'2013-03-08','2013-03-08',0),(5,1,2013,'2013-03-06','2013-09-08',0),(5,1,2014,'2013-09-01','2014-06-01',1);
/*!40000 ALTER TABLE `semesters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students` (
  `Id` int(11) NOT NULL,
  `Faculties_Id` int(11) NOT NULL,
  `FullName` varchar(50) DEFAULT NULL,
  `BrithDay` date DEFAULT NULL,
  `PhoneNumberFather` char(16) DEFAULT NULL,
  `Father` varchar(45) DEFAULT NULL,
  `IsClient` tinyint(1) DEFAULT NULL,
  `CreateDate` date DEFAULT NULL,
  `Debt` float DEFAULT NULL,
  `isactive` int(11) DEFAULT NULL,
  `Mother` varchar(45) DEFAULT NULL,
  `PhoneNumberMother` varchar(16) DEFAULT NULL,
  `HomePhone` varchar(16) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Sex` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`,`Faculties_Id`),
  KEY `fk_Students_Faculties1_idx` (`Faculties_Id`),
  CONSTRAINT `fk_Students_Faculties1` FOREIGN KEY (`Faculties_Id`) REFERENCES `faculties` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,1,'Nguyễn Hải Anh','2013-03-08','0169.437.9201','Nguyễn Ngọc Lan',1,'2013-03-08',0,0,NULL,NULL,NULL,NULL,NULL),(2,1,'Nguyễn Ngọc Anh','2013-03-09','0169.437.9207','Nguyễn Hoài Nam',1,'2013-03-08',0,0,NULL,NULL,NULL,NULL,NULL),(3,1,'Bùi Thiện Thắng','2013-03-08','0169.437.9201','Bùi Thiện Minh',0,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(4,1,'Lê Mạnh Kiên','2013-03-08','0169.437.9203','Nguyễn Ngọc Lan',1,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(5,1,'Văn Minh Tăng','2013-03-08','0169.437.9201','Nguyễn Văn Minh',1,'2013-03-08',100000000,1,NULL,NULL,NULL,NULL,NULL),(7,1,'Lại Văn Dầu','2013-03-08','0169.437.9201','Lại Văn Nghĩa',1,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(9,1,'Lưu Xuân Hằng','2013-03-08','0169.437.9201','Đỗ Hoài Nam',1,'2013-03-08',0,0,NULL,NULL,NULL,NULL,NULL),(10,1,'Lã Văn Nam','2013-03-08','0169.437.9201','Phạm Thái Hòa',1,'2013-03-08',0,0,NULL,NULL,NULL,NULL,NULL),(12,1,'Phạm Thái Bạch','2013-03-08','0169.437.9201','Đỗ Hoài Nam',1,'2013-03-08',0,0,NULL,NULL,NULL,NULL,NULL),(14,1,'Phạm Thanh Khuê','2013-03-08','0169.437.9201','Phạm Thái Hòa',1,'2013-03-08',0,0,NULL,NULL,NULL,NULL,NULL),(16,1,'Trần Văn Sĩ','2011-02-09','0169.437.9201','Bùi Thiện Minh',1,'2013-03-08',0,0,NULL,NULL,NULL,NULL,NULL),(17,1,'Luyện Thị Xuân','2013-03-08','0169.437.9201','Lại Văn Nghĩa',1,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(18,1,'Ngô Văn Hòa','2013-03-08','0169.437.9201','Đỗ Hoài Nam',1,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(19,1,'Trần Sỹ Quyết','2013-03-08','0169.437.9201','Nguyễn Ngọc Lan',0,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(20,1,'Nghiêm Đình Mừng','2013-03-08','0169.437.9201','Bùi Thiện Minh',1,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(21,1,'Nguyễn Khắc Quý','2013-03-08','0169.437.9201','Bùi Thiện Minh',1,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(22,1,'Trần Thái Lan','2013-03-08','0169.437.9201','Lại Văn Nghĩa',1,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(23,1,'Phùng Văn Hưng','2013-03-08','0169.437.9201','Nguyễn Ngọc Lan',1,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(24,1,'Lại Cao Thắng','2013-03-08','0169.437.9201','Lại Văn Nghĩa',1,'2013-03-08',0,0,NULL,NULL,NULL,NULL,NULL),(25,1,'Lê Công Vinh','2013-03-08','0169.437.9201','Bùi Thiện Minh',1,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(26,1,'Tạ Bá Trung','2013-03-08','0169.437.9201','Lại Văn Nghĩa',1,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(27,1,'Nguyễn Văn Bách','2013-03-08','0169.437.9201','Nguyễn Ngọc Lan',0,'2013-03-08',0,0,NULL,NULL,NULL,NULL,NULL),(28,1,'Nguyễn Thị Yến','2013-03-08','0169.437.9201','Bùi Thiện Minh',0,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(29,1,'Hoàng Ngọc Minh','2013-03-08','0169.437.9201','Nguyễn Hoài Nam',1,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(30,1,'Phạm Văn Bách','2013-03-08','0169.437.9201','Bùi Thiện Minh',1,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(31,1,'Trần Quốc Việt','2013-03-08','0169.437.9201','Nguyễn Ngọc Lan',1,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(32,1,'Bùi Thị Lan Anh ','2013-03-08','0169.437.9201','Lại Văn Nghĩa',1,'2013-03-08',0,0,NULL,NULL,NULL,NULL,NULL),(33,1,'Phạm Thế Thái','2013-03-08','0169.437.9201','Lại Văn Nghĩa',1,'2013-03-08',200000,0,NULL,NULL,NULL,NULL,NULL),(34,1,'Bùi Quốc Dũng','2013-03-08','0169.437.9201','Bùi Thiện Minh',1,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(35,1,'Hoàng Văn Anh','2012-03-08','0169.437.9201','Nguyễn Hoài Nam',1,'2013-03-08',1000000,1,NULL,NULL,NULL,NULL,NULL),(36,1,'Trần Hải Nam','2013-03-08','0169.437.9201','Lại Văn Nghĩa',1,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(40,1,'Nguyễn Văn Sĩ','2013-03-08','0169.437.9201','Lại Văn Nghĩa',1,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(41,1,'Lê Thị Minh Nguyệt','2013-03-08','0169.437.9201','Lại Văn Nghĩa',1,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(42,1,'Phạm Thị Huyền','2013-03-08','0169.437.9201','Lại Văn Nghĩa',1,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(45,1,'Nguyễn Văn Cường','2013-03-08','0169.437.9201','Bùi Thiện Minh',1,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(46,2,'Nguyễn Đức Sơn','2013-03-08','0169.437.9201','Lại Văn Nghĩa',1,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(48,3,'Nguyễn Thị Quyên','2013-03-08','0169.437.9201','Bùi Thiện Minh',1,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(49,4,'Nguyễn Thái Hà','2013-03-08','0169.437.9201','Nguyễn Hoài Nam',1,'2013-03-08',0,0,NULL,NULL,NULL,NULL,NULL),(50,1,'Phùng Ngọc Minh','2013-03-08','0169.437.9201','Nguyễn Ngọc Lan',1,'2013-03-08',0,1,NULL,NULL,NULL,NULL,NULL),(51,1,'Trần Hải Nam (b)','1990-01-01','012345666','ádf',0,NULL,0,1,NULL,NULL,NULL,NULL,NULL),(52,1,'Trần Hải Nam (c)','1990-02-02','0123345669','aaa',0,NULL,0,1,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students_has_cost`
--

DROP TABLE IF EXISTS `students_has_cost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students_has_cost` (
  `Students_Id` int(11) NOT NULL,
  `Cost_Id` int(11) NOT NULL,
  `Faculties_Id` int(11) NOT NULL,
  `IsDebt` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Students_Id`,`Cost_Id`,`Faculties_Id`),
  KEY `fk_Students_has_Cost_Cost1_idx` (`Cost_Id`),
  KEY `fk_Students_has_Cost_Students1_idx` (`Students_Id`),
  KEY `fk_Students_has_Cost_Faculties1_idx` (`Faculties_Id`),
  CONSTRAINT `fk_Students_has_Cost_Cost1` FOREIGN KEY (`Cost_Id`) REFERENCES `cost` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Students_has_Cost_Faculties1` FOREIGN KEY (`Faculties_Id`) REFERENCES `faculties` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Students_has_Cost_Students1` FOREIGN KEY (`Students_Id`) REFERENCES `students` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students_has_cost`
--

LOCK TABLES `students_has_cost` WRITE;
/*!40000 ALTER TABLE `students_has_cost` DISABLE KEYS */;
INSERT INTO `students_has_cost` VALUES (1,11,1,0),(1,12,1,0),(1,13,1,0),(1,18,1,0),(1,22,1,0),(5,1,1,0),(5,12,1,0),(5,13,1,0),(5,17,1,0),(5,23,1,1),(7,3,1,0),(7,12,1,0),(7,17,1,0),(7,20,1,0),(12,11,1,0),(16,12,1,1),(16,13,1,1),(18,3,1,0),(18,11,1,0),(18,12,1,0),(20,3,1,0),(20,11,1,0),(20,13,1,0),(20,17,1,0),(24,2,1,0),(24,13,1,0),(29,1,1,0),(29,11,1,0),(29,12,1,0),(33,1,1,0),(33,13,1,0),(34,2,1,0),(34,3,1,0),(34,13,1,0),(35,1,1,0),(35,13,1,0),(36,13,1,0),(36,19,1,0),(36,20,1,1),(45,13,1,1),(49,8,4,1),(49,11,4,1),(49,16,4,1);
/*!40000 ALTER TABLE `students_has_cost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `summerweek`
--

DROP TABLE IF EXISTS `summerweek`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `summerweek` (
  `id` int(11) NOT NULL,
  `tuanHoc` int(11) NOT NULL,
  `startDay` varchar(60) NOT NULL,
  `endDay` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `summerweek`
--

LOCK TABLES `summerweek` WRITE;
/*!40000 ALTER TABLE `summerweek` DISABLE KEYS */;
INSERT INTO `summerweek` VALUES (1,1,'2014-1-2','2014-2-3'),(2,2,'2014-1-2','2014-2-3'),(3,3,'2014-1-2','2014-2-3'),(4,4,'2014-1-2','2014-2-3'),(5,5,'2014-1-2','2014-2-3'),(6,6,'2014-1-2','2014-2-3'),(7,7,'2014-1-2','2014-2-3'),(8,8,'2014-1-2','2014-2-3');
/*!40000 ALTER TABLE `summerweek` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'projectkoala'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-06-26  0:42:44
