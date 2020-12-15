-- MySQL dump 10.13  Distrib 8.0.22, for Linux (x86_64)
--
-- Host: localhost    Database: testw3
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `alarmas`
--

DROP TABLE IF EXISTS `alarmas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alarmas` (
  `id_alarma` bigint NOT NULL AUTO_INCREMENT,
  `fecha_aceptacion` datetime(6) DEFAULT NULL,
  `motivo_alarma` varchar(255) DEFAULT NULL,
  `orden_id` bigint DEFAULT NULL,
  `usuario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id_alarma`),
  KEY `FKojh9ffig25c7p30g6y19uo06d` (`orden_id`),
  KEY `FK5wqaory2lci2saexu9qorlmwe` (`usuario_id`),
  CONSTRAINT `FK5wqaory2lci2saexu9qorlmwe` FOREIGN KEY (`usuario_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKojh9ffig25c7p30g6y19uo06d` FOREIGN KEY (`orden_id`) REFERENCES `ordenes` (`nro_orden`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarmas`
--

LOCK TABLES `alarmas` WRITE;
/*!40000 ALTER TABLE `alarmas` DISABLE KEYS */;
INSERT INTO `alarmas` VALUES (1,'2020-12-14 23:11:52.667000','La temperatura de carga de la orden 3 ha excedido el limite, su temperatura actual es de 82.0°C',3,1),(2,'2020-12-14 23:20:55.077000','La temperatura de carga de la orden 3 ha excedido el limite, su temperatura actual es de 88.0°C',3,1),(3,'2020-12-15 15:01:13.948000','La temperatura de carga de la orden 4 ha excedido el limite, su temperatura actual es de 88.0°C',4,1),(4,'2020-12-15 15:48:40.524000','La temperatura de carga de la orden 4 ha excedido el limite, su temperatura actual es de 88.0°C',4,1);
/*!40000 ALTER TABLE `alarmas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_token`
--

DROP TABLE IF EXISTS `auth_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_token` (
  `series` varchar(255) NOT NULL,
  `desde` datetime DEFAULT NULL,
  `last_used` datetime DEFAULT NULL,
  `request_count` int NOT NULL,
  `request_limit` int NOT NULL,
  `hasta` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `validity_seconds` int NOT NULL,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_token`
--

LOCK TABLES `auth_token` WRITE;
/*!40000 ALTER TABLE `auth_token` DISABLE KEYS */;
INSERT INTO `auth_token` VALUES ('bnF8/mXVr7xTDHnk3eBzBg==',NULL,'2020-12-15 14:13:36',10,0,NULL,'Khi0UbKZBN+2vyWLhf1YOQ==','DEFAULT','alanalberino',360),('lOqvEfvTOXxPUqm6XrM8iA==',NULL,'2020-12-15 13:53:49',6,0,NULL,'O/qWQzdqVcOvthTHK99z/w==','DEFAULT','alanalberino',360),('Mlmz/hYsnb4Smpx7HeMfLw==',NULL,'2020-12-15 16:22:33',3,0,NULL,'v2deJ4PqJKZI4CPRqtW3jw==','DEFAULT','alanalberino',360);
/*!40000 ALTER TABLE `auth_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `camiones`
--

DROP TABLE IF EXISTS `camiones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `camiones` (
  `id_camion` bigint NOT NULL AUTO_INCREMENT,
  `codigo_externo` varchar(100) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `patente` varchar(10) NOT NULL,
  PRIMARY KEY (`id_camion`),
  UNIQUE KEY `UK_mn6qy6m1njo5rv6s96t0mnl5r` (`patente`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `camiones`
--

LOCK TABLES `camiones` WRITE;
/*!40000 ALTER TABLE `camiones` DISABLE KEYS */;
INSERT INTO `camiones` VALUES (3,'c3svZYkLJF','Camion Ford','FLQ324');
/*!40000 ALTER TABLE `camiones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `camiones_cisternas`
--

DROP TABLE IF EXISTS `camiones_cisternas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `camiones_cisternas` (
  `id_camion` bigint NOT NULL,
  `id_cisterna` bigint NOT NULL,
  KEY `FKrs6gyam71ca1dyuj2bygy9q8o` (`id_cisterna`),
  KEY `FKhkhbib6lybwch605wkmd96gjk` (`id_camion`),
  CONSTRAINT `FKhkhbib6lybwch605wkmd96gjk` FOREIGN KEY (`id_camion`) REFERENCES `camiones` (`id_camion`),
  CONSTRAINT `FKrs6gyam71ca1dyuj2bygy9q8o` FOREIGN KEY (`id_cisterna`) REFERENCES `cisternas` (`id_cisterna`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `camiones_cisternas`
--

LOCK TABLES `camiones_cisternas` WRITE;
/*!40000 ALTER TABLE `camiones_cisternas` DISABLE KEYS */;
INSERT INTO `camiones_cisternas` VALUES (3,1),(3,2);
/*!40000 ALTER TABLE `camiones_cisternas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `choferes`
--

DROP TABLE IF EXISTS `choferes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `choferes` (
  `id_chofer` bigint NOT NULL AUTO_INCREMENT,
  `apellido` varchar(100) NOT NULL,
  `codigo_externo` varchar(100) DEFAULT NULL,
  `dni` bigint NOT NULL,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id_chofer`),
  UNIQUE KEY `UK_6mp1nuvjn3diet9tp596qvpi5` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `choferes`
--

LOCK TABLES `choferes` WRITE;
/*!40000 ALTER TABLE `choferes` DISABLE KEYS */;
INSERT INTO `choferes` VALUES (1,'Martinez','zdgy2xpHlZ',26874369,'Ernesto');
/*!40000 ALTER TABLE `choferes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cisternas`
--

DROP TABLE IF EXISTS `cisternas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cisternas` (
  `id_cisterna` bigint NOT NULL AUTO_INCREMENT,
  `capacidad` double DEFAULT NULL,
  `codigo_externo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_cisterna`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cisternas`
--

LOCK TABLES `cisternas` WRITE;
/*!40000 ALTER TABLE `cisternas` DISABLE KEYS */;
INSERT INTO `cisternas` VALUES (1,500,'fyHGuO7XBh'),(2,400,'LgsSbx1UJd');
/*!40000 ALTER TABLE `cisternas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id_cliente` bigint NOT NULL AUTO_INCREMENT,
  `codigo_externo` varchar(100) DEFAULT NULL,
  `contacto` varchar(100) NOT NULL,
  `num_telefono` bigint DEFAULT NULL,
  `razon_social` varchar(60) NOT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'rSDQMmUYaL','tito@titosrl.com.ar',NULL,'Tito SRL');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_ordenes`
--

DROP TABLE IF EXISTS `detalle_ordenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_ordenes` (
  `id_detalle` bigint NOT NULL AUTO_INCREMENT,
  `caudal_producto` double DEFAULT NULL,
  `densidad_producto` double DEFAULT NULL,
  `fecha_detalle` datetime(6) DEFAULT NULL,
  `masa_acumulada` double DEFAULT NULL,
  `temperatura_producto` double DEFAULT NULL,
  `orden_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id_detalle`),
  KEY `FKn305ta8p3nsyqaykrakcqulep` (`orden_id`),
  CONSTRAINT `FKn305ta8p3nsyqaykrakcqulep` FOREIGN KEY (`orden_id`) REFERENCES `ordenes` (`nro_orden`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_ordenes`
--

LOCK TABLES `detalle_ordenes` WRITE;
/*!40000 ALTER TABLE `detalle_ordenes` DISABLE KEYS */;
INSERT INTO `detalle_ordenes` VALUES (1,25,0.8,'2020-11-17 15:27:54.096000',270,38,1),(2,25,0.8,'2020-12-08 19:34:10.853000',270,50,2),(3,25,0.8,'2020-12-08 19:58:47.019000',290,83,2),(4,25,0.8,'2020-12-08 20:00:33.760000',300,83,2),(5,25,0.8,'2020-12-08 20:16:29.329000',310,83,2),(6,25,0.8,'2020-12-08 20:19:30.685000',320,83,2),(7,25,0.8,'2020-12-08 20:20:34.405000',325,83,2),(8,25,0.8,'2020-12-08 20:21:11.884000',335,83,2),(9,25,0.8,'2020-12-08 20:22:06.428000',345,83,2),(10,25,0.8,'2020-12-08 20:22:24.830000',355,83,2),(11,25,0.8,'2020-12-08 20:23:07.700000',365,83,2),(12,25,0.8,'2020-12-08 20:23:24.619000',375,83,2),(13,25,0.8,'2020-12-08 20:24:02.905000',385,83,2),(14,25,0.8,'2020-12-08 20:26:30.896000',395,83,2),(15,25,0.8,'2020-12-08 20:35:16.138000',400,83,2),(16,25,0.8,'2020-12-08 20:36:27.621000',401,83,2),(17,25,0.8,'2020-12-08 20:48:19.012000',402,83,2),(18,25,0.8,'2020-12-08 20:50:28.972000',403,83,2),(19,25,0.8,'2020-12-08 20:53:03.365000',404,83,2),(20,25,0.8,'2020-12-08 22:49:12.612000',420,88,2),(21,25,0.8,'2020-12-08 22:49:59.666000',421,88,2),(22,25,0.8,'2020-12-08 22:53:42.005000',422,88,2),(23,25,0.8,'2020-12-08 22:55:34.105000',423,88,2),(24,25,0.8,'2020-12-08 22:59:10.392000',424,88,2),(25,25,0.8,'2020-12-10 18:42:39.210000',425,88,2),(26,25,0.8,'2020-12-10 18:43:26.877000',500,88,2),(27,25,0.8,'2020-12-10 18:43:46.111000',500,88,2),(28,25,0.8,'2020-12-10 18:44:31.158000',501,75,2),(29,25,0.8,'2020-12-10 18:46:01.978000',502,75,2),(30,25,0.8,'2020-12-10 18:49:08.311000',503,75,2),(31,25,0.8,'2020-12-10 18:49:23.958000',504,75,2),(32,25,0.8,'2020-12-10 18:49:50.345000',558,75,2),(33,25,0.8,'2020-12-11 23:09:01.147000',580,38,2),(34,25,0.8,'2020-12-11 23:09:22.088000',582,38,2),(35,25,0.8,'2020-12-11 23:11:23.943000',583,38,2),(36,25,0.8,'2020-12-11 23:13:14.680000',585,38,2),(37,25,0.8,'2020-12-11 23:14:10.385000',586,38,2),(38,25,0.8,'2020-12-11 23:14:42.312000',587,38,2),(39,25,0.8,'2020-12-11 23:15:41.395000',588,38,2),(40,25,0.8,'2020-12-11 23:16:48.957000',589,38,2),(41,25,0.8,'2020-12-11 23:19:12.656000',590,38,2),(42,25,0.8,'2020-12-11 23:29:26.732000',100,38,3),(43,25,0.8,'2020-12-14 23:09:50.370000',120,38,3),(44,25,0.5,'2020-12-14 23:10:21.766000',120,82,3),(45,25,0.5,'2020-12-14 23:11:57.528000',125,82,3),(46,25,0.5,'2020-12-14 23:13:52.130000',130,82,3),(47,25,0.5,'2020-12-14 23:14:20.745000',136,82,3),(48,25,0.5,'2020-12-14 23:14:43.236000',138,82,3),(49,25,0.5,'2020-12-14 23:15:08.292000',150,88,3),(50,25,0.5,'2020-12-14 23:18:05.000000',151,88,3),(51,25,0.5,'2020-12-14 23:18:39.395000',152,88,3),(52,25,0.5,'2020-12-14 23:19:51.880000',152,88,3),(53,25,0.5,'2020-12-15 15:00:52.211000',120,88,4),(54,25,0.5,'2020-12-15 15:48:34.313000',120,88,4);
/*!40000 ALTER TABLE `detalle_ordenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordenes`
--

DROP TABLE IF EXISTS `ordenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordenes` (
  `nro_orden` bigint NOT NULL AUTO_INCREMENT,
  `caudal` double DEFAULT NULL,
  `codigo_externo` varchar(100) DEFAULT NULL,
  `densidad` double DEFAULT NULL,
  `estado` int NOT NULL,
  `fechafproceso_carga` datetime(6) DEFAULT NULL,
  `fechaiproceso_carga` datetime(6) DEFAULT NULL,
  `fecha_recepcion` datetime(6) DEFAULT NULL,
  `fecha_recepcion_pesajef` datetime(6) DEFAULT NULL,
  `fecha_recepcion_pesajei` datetime(6) DEFAULT NULL,
  `fecha_ultimo_almacenamiento` datetime(6) DEFAULT NULL,
  `masa_acumulada` double DEFAULT NULL,
  `pesaje_final` double DEFAULT NULL,
  `pesaje_inicial` double DEFAULT NULL,
  `preset` double DEFAULT NULL,
  `psswd` varchar(255) DEFAULT NULL,
  `temperatura` double DEFAULT NULL,
  `camion_id` bigint DEFAULT NULL,
  `chofer_id` bigint DEFAULT NULL,
  `cliente_id` bigint DEFAULT NULL,
  `producto_id` bigint DEFAULT NULL,
  `anulado` tinyint(1) NOT NULL DEFAULT '0',
  `tiene_alarma_encendida` bit(1) NOT NULL,
  PRIMARY KEY (`nro_orden`),
  KEY `FKoor180pstu8sjohc6b2ke83p3` (`camion_id`),
  KEY `FK2f78gmjo0j9fuc1pg501qps0h` (`chofer_id`),
  KEY `FKmftalwehlpu8ntrikv005falt` (`cliente_id`),
  KEY `FKghtsna0co218vud8ic1b6scix` (`producto_id`),
  CONSTRAINT `FK2f78gmjo0j9fuc1pg501qps0h` FOREIGN KEY (`chofer_id`) REFERENCES `choferes` (`id_chofer`),
  CONSTRAINT `FKghtsna0co218vud8ic1b6scix` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id_producto`),
  CONSTRAINT `FKmftalwehlpu8ntrikv005falt` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id_cliente`),
  CONSTRAINT `FKoor180pstu8sjohc6b2ke83p3` FOREIGN KEY (`camion_id`) REFERENCES `camiones` (`id_camion`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordenes`
--

LOCK TABLES `ordenes` WRITE;
/*!40000 ALTER TABLE `ordenes` DISABLE KEYS */;
INSERT INTO `ordenes` VALUES (1,25,'d6PbHPmKtg',0.8,4,'2020-11-17 15:27:57.420000','2020-11-17 15:27:54.096000','2020-11-17 15:27:46.759000','2020-11-17 15:27:59.904000','2020-11-13 18:56:56.447000','2020-11-17 15:27:54.096000',270,1500,1200,450,'91481',38,3,1,1,1,0,_binary '\0'),(2,25,'d6PbHPmKtF',0.8,4,'2020-12-11 23:19:42.001000','2020-12-08 19:34:10.853000','2020-11-17 19:05:45.275000','2020-12-11 23:22:57.744000','2020-11-13 18:56:56.447000','2020-12-11 23:19:12.652000',590,1500,1200,550,'61178',38,3,1,1,1,0,_binary '\0'),(3,25,'d6PbHPmKt6',0.5,2,NULL,'2020-12-11 23:29:26.732000','2020-11-17 20:14:03.978000',NULL,'2020-11-13 18:56:56.447000','2020-12-14 23:19:51.875000',152,NULL,1400,550,'40009',88,3,1,1,1,1,_binary '\0'),(4,25,'d6PbHPmKtA',0.5,2,NULL,'2020-12-15 15:00:52.211000','2020-12-15 15:00:31.930000',NULL,'2020-11-13 18:56:56.447000','2020-12-15 15:48:34.306000',120,NULL,1200,550,'19430',88,3,1,1,1,0,_binary '\0'),(5,0,'d6PbHPmKt1',0,2,NULL,NULL,'2020-12-15 15:03:59.203000',NULL,'2020-12-15 11:56:56.447000',NULL,0,NULL,1200,550,'44402',0,3,1,1,1,0,_binary '\0'),(6,0,'d6PbHPmKt4',0,1,NULL,NULL,'2020-12-15 15:04:07.372000',NULL,'2020-12-15 11:56:56.447000',NULL,0,NULL,NULL,550,NULL,0,3,1,1,1,0,_binary '\0');
/*!40000 ALTER TABLE `ordenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `id_producto` bigint NOT NULL AUTO_INCREMENT,
  `codigo_externo` varchar(100) DEFAULT NULL,
  `descripcion` varchar(140) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `precio` bigint DEFAULT NULL,
  PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'6A7iBCfLQE','Gas Butano para llenado de tanques a domicilio.','Gas Butano',1000);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(250) DEFAULT NULL,
  `rol` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g00thobnv3twutok8x6furkx1` (`rol`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'admin','admin');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_non_expired` tinyint DEFAULT '1',
  `account_non_locked` tinyint DEFAULT '1',
  `apellido` varchar(100) DEFAULT NULL,
  `credentials_non_expired` tinyint DEFAULT '1',
  `email` varchar(100) NOT NULL,
  `enabled` tinyint DEFAULT '1',
  `nombre` varchar(100) DEFAULT NULL,
  `password` varchar(70) NOT NULL,
  `session_timeout` int DEFAULT '360',
  `username` varchar(50) NOT NULL,
  `id_rol_principal` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  KEY `FKagx3xbxthdpi4gc0hyfggwxn0` (`id_rol_principal`),
  CONSTRAINT `FKagx3xbxthdpi4gc0hyfggwxn0` FOREIGN KEY (`id_rol_principal`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,1,'alberino',1,'alanalberino@gmail.com',1,'alan','$2a$10$m9dBHnY/W9/VawId7cr8L.OLy/wiaw124rC6EOKKYiOOZdaqN6Idy',360,'alanalberino',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `id_user` bigint NOT NULL,
  `id_rol` int NOT NULL,
  PRIMARY KEY (`id_user`,`id_rol`),
  KEY `FKaulyi2lejh5cckb2y8e2mlpud` (`id_rol`),
  CONSTRAINT `FK6ywr92flw5416dup8uc2egb83` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  CONSTRAINT `FKaulyi2lejh5cckb2y8e2mlpud` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,1);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-15 13:57:46
