CREATE DATABASE  IF NOT EXISTS `db_tesi` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_tesi`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db_tesi
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `assessmentcontrol`
--

DROP TABLE IF EXISTS `assessmentcontrol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assessmentcontrol` (
  `id` int NOT NULL,
  `idAssessmentPlan` int NOT NULL,
  `idControl` int NOT NULL,
  `stato` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idAssessmentPlan` (`idAssessmentPlan`),
  KEY `idControl` (`idControl`),
  CONSTRAINT `assessmentcontrol_ibfk_1` FOREIGN KEY (`idAssessmentPlan`) REFERENCES `assessmentplan` (`id`),
  CONSTRAINT `assessmentcontrol_ibfk_2` FOREIGN KEY (`idControl`) REFERENCES `control` (`idCONTROL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assessmentplan`
--

DROP TABLE IF EXISTS `assessmentplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assessmentplan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `benchmark`
--

DROP TABLE IF EXISTS `benchmark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `benchmark` (
  `idBENCHMARK` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idBENCHMARK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `benchmark_includes`
--

DROP TABLE IF EXISTS `benchmark_includes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `benchmark_includes` (
  `BENCHMARK_idBENCHMARK` int NOT NULL,
  `PROFILE_idPROFILE` int NOT NULL,
  PRIMARY KEY (`BENCHMARK_idBENCHMARK`,`PROFILE_idPROFILE`),
  KEY `fk_benchmark_includes_PROFILE1_idx` (`PROFILE_idPROFILE`),
  CONSTRAINT `fk_benchmark_includes_BENCHMARK1` FOREIGN KEY (`BENCHMARK_idBENCHMARK`) REFERENCES `benchmark` (`idBENCHMARK`),
  CONSTRAINT `fk_benchmark_includes_PROFILE1` FOREIGN KEY (`PROFILE_idPROFILE`) REFERENCES `profile` (`idPROFILE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `idCATEGORY` int NOT NULL AUTO_INCREMENT,
  `FUNCTION_idFUNCTIONS` int DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `source` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCATEGORY`),
  KEY `fk_CATEGORY_FUNCTION_idx` (`FUNCTION_idFUNCTIONS`),
  CONSTRAINT `fk_CATEGORY_FUNCTION` FOREIGN KEY (`FUNCTION_idFUNCTIONS`) REFERENCES `function` (`idFUNCTIONS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `context`
--

DROP TABLE IF EXISTS `context`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `context` (
  `idCONTEXT` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCONTEXT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contextualization`
--

DROP TABLE IF EXISTS `contextualization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contextualization` (
  `CONTEXT_idCONTEXT` int NOT NULL,
  `SUBCATEGORY_idSUBCATEGORY` int NOT NULL,
  PRIMARY KEY (`CONTEXT_idCONTEXT`,`SUBCATEGORY_idSUBCATEGORY`),
  KEY `fk_CONTEXTUALIZATION_SUBCATEGORY1_idx` (`SUBCATEGORY_idSUBCATEGORY`),
  CONSTRAINT `fk_CONTEXTUALIZATION_CONTEXT1` FOREIGN KEY (`CONTEXT_idCONTEXT`) REFERENCES `context` (`idCONTEXT`),
  CONSTRAINT `fk_CONTEXTUALIZATION_SUBCATEGORY1` FOREIGN KEY (`SUBCATEGORY_idSUBCATEGORY`) REFERENCES `subcategory` (`idSUBCATEGORY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contextualization_has_maturity`
--

DROP TABLE IF EXISTS `contextualization_has_maturity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contextualization_has_maturity` (
  `CONTEXTUALIZATION_CONTEXT_idCONTEXT` int NOT NULL,
  `maturity_level_idmaturity_level` int NOT NULL,
  PRIMARY KEY (`CONTEXTUALIZATION_CONTEXT_idCONTEXT`,`maturity_level_idmaturity_level`),
  KEY `fk_contextualization_has_maturity_maturity_level1_idx` (`maturity_level_idmaturity_level`),
  CONSTRAINT `fk_contextualization_has_maturity_CONTEXTUALIZATION1` FOREIGN KEY (`CONTEXTUALIZATION_CONTEXT_idCONTEXT`) REFERENCES `contextualization` (`CONTEXT_idCONTEXT`),
  CONSTRAINT `fk_contextualization_has_maturity_maturity_level1` FOREIGN KEY (`maturity_level_idmaturity_level`) REFERENCES `maturity_level` (`idmaturity_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `control`
--

DROP TABLE IF EXISTS `control`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `control` (
  `idCONTROL` int NOT NULL AUTO_INCREMENT,
  `name` varchar(500) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `maturity_level` varchar(45) DEFAULT NULL,
  `FAMILY_idFAMILY` int DEFAULT NULL,
  PRIMARY KEY (`idCONTROL`),
  KEY `fk_CONTROL_FAMILY1_idx` (`FAMILY_idFAMILY`),
  CONSTRAINT `fk_CONTROL_FAMILY1` FOREIGN KEY (`FAMILY_idFAMILY`) REFERENCES `family` (`idFAMILY`)
) ENGINE=InnoDB AUTO_INCREMENT=1079 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `control_has_related`
--

DROP TABLE IF EXISTS `control_has_related`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `control_has_related` (
  `CONTROL_idCONTROL` int NOT NULL,
  `CONTROL_idCONTROL1` int NOT NULL,
  `is_subcontrol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`CONTROL_idCONTROL`,`CONTROL_idCONTROL1`),
  KEY `fk_control_has_related_CONTROL2_idx` (`CONTROL_idCONTROL1`),
  CONSTRAINT `fk_control_has_related_CONTROL1` FOREIGN KEY (`CONTROL_idCONTROL`) REFERENCES `control` (`idCONTROL`),
  CONSTRAINT `fk_control_has_related_CONTROL2` FOREIGN KEY (`CONTROL_idCONTROL1`) REFERENCES `control` (`idCONTROL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `family`
--

DROP TABLE IF EXISTS `family`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `family` (
  `idFAMILY` int NOT NULL AUTO_INCREMENT,
  `name` varchar(2000) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `FRAMEWORK_idFRAMEWORK` int DEFAULT NULL,
  PRIMARY KEY (`idFAMILY`),
  KEY `fk_FAMILY_FRAMEWORK1_idx` (`FRAMEWORK_idFRAMEWORK`),
  CONSTRAINT `fk_FAMILY_FRAMEWORK1` FOREIGN KEY (`FRAMEWORK_idFRAMEWORK`) REFERENCES `framework` (`idFRAMEWORK`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `framework`
--

DROP TABLE IF EXISTS `framework`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `framework` (
  `idFRAMEWORK` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `version` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idFRAMEWORK`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `function`
--

DROP TABLE IF EXISTS `function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `function` (
  `idFUNCTIONS` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idFUNCTIONS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fusion_has_context`
--

DROP TABLE IF EXISTS `fusion_has_context`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fusion_has_context` (
  `idfusion_has_context` int NOT NULL AUTO_INCREMENT,
  `CONTEXT_idCONTEXT` int DEFAULT NULL,
  PRIMARY KEY (`idfusion_has_context`),
  KEY `fk_fusion_has_context_CONTEXT1_idx` (`CONTEXT_idCONTEXT`),
  CONSTRAINT `fk_fusion_has_context_CONTEXT1` FOREIGN KEY (`CONTEXT_idCONTEXT`) REFERENCES `context` (`idCONTEXT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `maturity_level`
--

DROP TABLE IF EXISTS `maturity_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maturity_level` (
  `idmaturity_level` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `level` varchar(45) DEFAULT NULL,
  `CONTEXT_idCONTEXT` int DEFAULT NULL,
  PRIMARY KEY (`idmaturity_level`),
  KEY `fk_maturity_level_CONTEXT1_idx` (`CONTEXT_idCONTEXT`),
  CONSTRAINT `fk_maturity_level_CONTEXT1` FOREIGN KEY (`CONTEXT_idCONTEXT`) REFERENCES `context` (`idCONTEXT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile` (
  `idPROFILE` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `level` varchar(45) DEFAULT NULL,
  `CONTEXT_idCONTEXT` int DEFAULT NULL,
  `FRAMEWORK_idFRAMEWORK` int DEFAULT NULL,
  PRIMARY KEY (`idPROFILE`),
  KEY `fk_PROFILE_CONTEXT1_idx` (`CONTEXT_idCONTEXT`),
  KEY `fk_PROFILE_FRAMEWORK1_idx` (`FRAMEWORK_idFRAMEWORK`),
  CONSTRAINT `fk_PROFILE_CONTEXT1` FOREIGN KEY (`CONTEXT_idCONTEXT`) REFERENCES `context` (`idCONTEXT`),
  CONSTRAINT `fk_PROFILE_FRAMEWORK1` FOREIGN KEY (`FRAMEWORK_idFRAMEWORK`) REFERENCES `framework` (`idFRAMEWORK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `profile_has_sub`
--

DROP TABLE IF EXISTS `profile_has_sub`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile_has_sub` (
  `PROFILE_idPROFILE` int NOT NULL,
  `SUBCATEGORY_idSUBCATEGORY` int NOT NULL,
  `maturity_level_idmaturity_level` int DEFAULT NULL,
  `priority` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`PROFILE_idPROFILE`,`SUBCATEGORY_idSUBCATEGORY`),
  KEY `fk_profile_has_sub_SUBCATEGORY1_idx` (`SUBCATEGORY_idSUBCATEGORY`),
  KEY `fk_profile_has_sub_maturity_level1_idx` (`maturity_level_idmaturity_level`),
  CONSTRAINT `fk_profile_has_sub_maturity_level1` FOREIGN KEY (`maturity_level_idmaturity_level`) REFERENCES `maturity_level` (`idmaturity_level`),
  CONSTRAINT `fk_profile_has_sub_PROFILE1` FOREIGN KEY (`PROFILE_idPROFILE`) REFERENCES `profile` (`idPROFILE`),
  CONSTRAINT `fk_profile_has_sub_SUBCATEGORY1` FOREIGN KEY (`SUBCATEGORY_idSUBCATEGORY`) REFERENCES `subcategory` (`idSUBCATEGORY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `profile_maturity_control`
--

DROP TABLE IF EXISTS `profile_maturity_control`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile_maturity_control` (
  `profile_has_sub_PROFILE_idPROFILE` int NOT NULL,
  `profile_has_sub_SUBCATEGORY_idSUBCATEGORY` int NOT NULL,
  `implementation` varchar(45) DEFAULT NULL,
  `CONTROL_idCONTROL` int DEFAULT NULL,
  PRIMARY KEY (`profile_has_sub_PROFILE_idPROFILE`,`profile_has_sub_SUBCATEGORY_idSUBCATEGORY`),
  KEY `fk_profile_maturity_control_CONTROL1_idx` (`CONTROL_idCONTROL`),
  CONSTRAINT `fk_profile_maturity_control_CONTROL1` FOREIGN KEY (`CONTROL_idCONTROL`) REFERENCES `control` (`idCONTROL`),
  CONSTRAINT `fk_profile_maturity_control_profile_has_sub1` FOREIGN KEY (`profile_has_sub_PROFILE_idPROFILE`, `profile_has_sub_SUBCATEGORY_idSUBCATEGORY`) REFERENCES `profile_has_sub` (`PROFILE_idPROFILE`, `SUBCATEGORY_idSUBCATEGORY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `subcategory`
--

DROP TABLE IF EXISTS `subcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subcategory` (
  `idSUBCATEGORY` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `CATEGORY_idCATEGORY` int DEFAULT NULL,
  PRIMARY KEY (`idSUBCATEGORY`),
  KEY `fk_SUBCATEGORY_CATEGORY1_idx` (`CATEGORY_idCATEGORY`),
  CONSTRAINT `fk_SUBCATEGORY_CATEGORY1` FOREIGN KEY (`CATEGORY_idCATEGORY`) REFERENCES `category` (`idCATEGORY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `subcategory_is_implemented`
--

DROP TABLE IF EXISTS `subcategory_is_implemented`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subcategory_is_implemented` (
  `SUBCATEGORY_idSUBCATEGORY` int NOT NULL,
  `CONTROL_idCONTROL` int NOT NULL,
  PRIMARY KEY (`SUBCATEGORY_idSUBCATEGORY`,`CONTROL_idCONTROL`),
  KEY `fk_subcategory_is_implemented_CONTROL1_idx` (`CONTROL_idCONTROL`),
  CONSTRAINT `fk_subcategory_is_implemented_CONTROL1` FOREIGN KEY (`CONTROL_idCONTROL`) REFERENCES `control` (`idCONTROL`),
  CONSTRAINT `fk_subcategory_is_implemented_SUBCATEGORY1` FOREIGN KEY (`SUBCATEGORY_idSUBCATEGORY`) REFERENCES `subcategory` (`idSUBCATEGORY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-22 16:07:44
