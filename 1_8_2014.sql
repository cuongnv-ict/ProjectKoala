CREATE DATABASE  IF NOT EXISTS `projectkoala` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `projectkoala`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: projectkoala
-- ------------------------------------------------------
-- Server version	5.6.14

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
INSERT INTO `accounts` VALUES (1000,1,'admin','admin','admin','admin','adimn',1,0,NULL),(1054812280,1,'cuong','cuong','123','LeTung@yahoo.com','0123456789',NULL,1,'Kế Toán');
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
INSERT INTO `buslist` VALUES (1,2,'1','aaaa','100000','aaa',1,'2014-01-01','2014-02-01');
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
INSERT INTO `classes` VALUES (1,1,1,1,2014,'SB','Tạ Thị Bích Loan',20,0),(2,1,1,1,2014,'ICT','as',2,0);
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
  `Class_Id_Old` varchar(45) DEFAULT ' ',
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
INSERT INTO `classes_has_students` VALUES (1,2,1,'ICT'),(1,3,1,'ICT'),(1,4,1,'ICT');
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
INSERT INTO `cost` VALUES (1,1,1,'học phí',5000000,2013,'2014-01-01','2015-01-01'),(2,1,5,'Phí Đặt Cọc',-7000000,2013,'2014-01-01','2015-01-01'),(3,1,1,'Hoàn cờ vua',15000000,2013,'2014-01-01','2015-01-01'),(4,1,1,'học nhạc',1000000,2013,'2014-01-01','2015-01-01'),(5,1,1,'học múa',5000000,2013,'2014-01-01','2014-01-01'),(6,1,1,'học nhảy',2000000,2013,'2014-01-01','2014-01-01'),(7,1,1,'học xx',20000000,2013,'2014-01-01','2014-02-01'),(8,1,1,'Phí Trông Muộn',20000,2013,'2014-01-02','2015-01-01'),(9,1,1,'hoàn Học Phí',10000,2013,'2014-01-01','2015-01-01'),(10,1,4,'Phí Học Hè',30000,2013,'2014-01-01','2015-01-01'),(11,1,2,'Phí Trông Muộn',50000,2013,'2014-01-01','2014-01-01'),(12,1,4,'Phí Học Hè',50000,2014,'2014-01-01','2014-01-01'),(13,1,2,'Hoàn Học Phí',20000,2014,'2014-01-01','2015-01-01'),(14,1,4,'Phí Học Hè',100000,2015,'2014-01-01','2015-01-01');
/*!40000 ALTER TABLE `cost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dropbox`
--

DROP TABLE IF EXISTS `dropbox`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dropbox` (
  `id` int(11) NOT NULL,
  `mail` varchar(45) DEFAULT NULL,
  `pass` varchar(45) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `remeber` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dropbox`
--

LOCK TABLES `dropbox` WRITE;
/*!40000 ALTER TABLE `dropbox` DISABLE KEYS */;
/*!40000 ALTER TABLE `dropbox` ENABLE KEYS */;
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
INSERT INTO `lateday` VALUES (1,1,2,'2014-01-04',20,0,1,2013),(2,1,2,'2014-01-07',10,0,2,2013),(3,1,2,'2014-01-08',10,0,2,2013);
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
  `Semester` int(11) DEFAULT NULL,
  `year` year(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `learnsummer`
--

LOCK TABLES `learnsummer` WRITE;
/*!40000 ALTER TABLE `learnsummer` DISABLE KEYS */;
INSERT INTO `learnsummer` VALUES (1,2,'4,5',2,'sb',0,4,2014),(2,2,'2,3,4',3,'sb',0,4,2013),(3,2,'5,6',2,'sb',0,4,2015);
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
  `StartDate` varchar(10) NOT NULL,
  `EndDate` varchar(10) NOT NULL,
  `NumberOfDay` varchar(45) NOT NULL,
  `IsActive` varchar(45) NOT NULL,
  `Semester` int(11) DEFAULT NULL,
  `year` year(4) DEFAULT NULL,
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
INSERT INTO `leaves` VALUES (1,1,2,'2014-8-8','2014-8-8','5','0',1,2013),(2,1,2,'2014-8-8','2014-8-8','10','0',2,2014);
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
INSERT INTO `receipts` VALUES (1,1,1000,2,1,'hgg','gg',9000,NULL,NULL,'2014-07-16',0,0,'Không Có'),(2,1,1000,2,2,'hhhhh','hhh',990000,NULL,NULL,'2014-07-16',0,0,'Không Có'),(3,1,1000,2,3,'fff','ffff',1000002,NULL,NULL,'2014-07-18',0,0,'Không Có'),(4,1,1000,2,4,'aaa','aaaa',540000,NULL,NULL,'2014-07-19',0,0,'Không Có'),(5,1,1000,2,5,'aa','aaaa',1000000,NULL,NULL,'2014-07-19',0,0,'Không Có'),(6,1,1000,2,6,'sdgasd','asdgsa',300000,NULL,NULL,'2014-07-19',0,0,'Không Có');
/*!40000 ALTER TABLE `receipts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refund`
--

DROP TABLE IF EXISTS `refund`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `refund` (
  `id` int(11) NOT NULL,
  `idStudent` int(11) NOT NULL,
  `StartDate` date DEFAULT NULL,
  `EndDate` date NOT NULL,
  `Note` varchar(200) DEFAULT NULL,
  `IsActive` int(11) NOT NULL,
  `SoTien` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refund`
--

LOCK TABLES `refund` WRITE;
/*!40000 ALTER TABLE `refund` DISABLE KEYS */;
/*!40000 ALTER TABLE `refund` ENABLE KEYS */;
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
INSERT INTO `semesters` VALUES (1,1,2013,'2013-01-01','2013-01-01',0),(1,1,2014,'2014-01-01','2014-02-01',0),(1,1,2015,'2015-01-01','2015-02-01',0),(2,1,2013,'2013-01-01','2013-01-01',0),(2,1,2014,'2014-02-02','2014-03-01',0),(2,1,2015,'2015-03-01','2015-04-01',0),(3,1,2013,'2013-01-01','2013-01-01',0),(3,1,2014,'2014-03-02','2014-04-01',0),(3,1,2015,'2015-05-01','2015-06-01',0),(4,1,2013,'2013-01-01','2013-01-01',0),(4,1,2014,'2014-05-01','2015-03-01',0),(4,1,2015,'2015-07-01','2015-08-01',0),(5,1,2013,'2013-01-01','2013-01-01',0),(5,1,2014,'2014-01-01','2015-03-01',0),(5,1,2015,'2015-01-01','2015-08-01',0);
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
  `IsDatCoc` int(11) DEFAULT NULL,
  `NhapHoc` date DEFAULT NULL,
  `NghiHoc` date DEFAULT NULL,
  `DaiDien` varchar(256) DEFAULT NULL,
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
INSERT INTO `students` VALUES (1,1,'Nguyến Đức Sơn','2000-12-21','0988749407','Nguyễn Văn Hào',0,NULL,0,-1,'Nguyễn Thị Thúy Hướng','0998989899','0491231233','dycsoaosdf',1,NULL,'2014-07-15',NULL,'Bà Nguyễn Thị Lan'),(2,1,'Nguyễn Đức Sơn','2000-12-21','098888888888','Nguyễn Văn Hào',0,NULL,0,1,'Nguyễn THị Thúy Hường','099999999999','0343514145','ducson.bkhn.k56',1,1,'2014-07-15',NULL,'Bà Nguyễn Thị La'),(3,1,'LE ngoc','2001-01-02','','',0,NULL,0,0,'','','','',0,NULL,'2014-07-20','2014-07-20',''),(4,1,'dsf','2001-02-03','','',0,NULL,0,1,'','','','',0,NULL,'2014-06-20',NULL,'');
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
INSERT INTO `students_has_cost` VALUES (2,1,1,0),(2,3,1,0),(2,4,1,0);
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
INSERT INTO `summerweek` VALUES (1,1,'2015-1-2','2016-1-6'),(2,2,'2014-2-3','2014-2-4'),(3,3,'2014-2-3','2014-2-4'),(4,4,'2014-2-3','2014-2-4'),(5,5,'2014-2-3','2014-2-4'),(6,6,'2014-2-3','2014-2-4'),(7,7,'2014-2-3','2014-2-4'),(8,8,'2014-2-3','2014-2-4');
/*!40000 ALTER TABLE `summerweek` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-01 23:01:38
