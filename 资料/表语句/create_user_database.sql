-- MySQL dump 10.13  Distrib 8.0.19, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: server_user
-- ------------------------------------------------------
-- Server version	5.7.29

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
-- Dumping data for table `oauth_access_token`
--

LOCK TABLES `oauth_access_token` WRITE;
/*!40000 ALTER TABLE `oauth_access_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_access_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `oauth_approvals`
--

LOCK TABLES `oauth_approvals` WRITE;
/*!40000 ALTER TABLE `oauth_approvals` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_approvals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `oauth_client_details`
--

LOCK TABLES `oauth_client_details` WRITE;
/*!40000 ALTER TABLE `oauth_client_details` DISABLE KEYS */;
INSERT INTO `oauth_client_details` VALUES ('sever_video',NULL,'$2a$10$myRSxdIA3n6iuiPXy4hQVe6M33.dAK6RGA6827nBcZJUTHs.D4Eam','all','password','www.baidu.com','',NULL,NULL,NULL,'false');
/*!40000 ALTER TABLE `oauth_client_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `oauth_client_token`
--

LOCK TABLES `oauth_client_token` WRITE;
/*!40000 ALTER TABLE `oauth_client_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_client_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `oauth_code`
--

LOCK TABLES `oauth_code` WRITE;
/*!40000 ALTER TABLE `oauth_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `oauth_refresh_token`
--

LOCK TABLES `oauth_refresh_token` WRITE;
/*!40000 ALTER TABLE `oauth_refresh_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `server_authority`
--

LOCK TABLES `server_authority` WRITE;
/*!40000 ALTER TABLE `server_authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `server_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `server_role`
--

LOCK TABLES `server_role` WRITE;
/*!40000 ALTER TABLE `server_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `server_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `server_role_authorises`
--

LOCK TABLES `server_role_authorises` WRITE;
/*!40000 ALTER TABLE `server_role_authorises` DISABLE KEYS */;
/*!40000 ALTER TABLE `server_role_authorises` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `server_token`
--

LOCK TABLES `server_token` WRITE;
/*!40000 ALTER TABLE `server_token` DISABLE KEYS */;
INSERT INTO `server_token` VALUES ('309933779718307840',NULL,_binary '\0',0,'309933779663781888d41d8cd98f00b204e9800998ecf8427e',NULL,NULL),('309935820956041216',NULL,_binary '\0',0,'309935820914098176d41d8cd98f00b204e9800998ecf8427e',NULL,NULL),('309950864041246720',NULL,_binary '\0',0,'309950864032858112d41d8cd98f00b204e9800998ecf8427e',NULL,NULL),('310191102999199744',NULL,_binary '\0',0,'310191102936285184d41d8cd98f00b204e9800998ecf8427e',NULL,NULL),('310217871311568896',NULL,_binary '\0',0,'310217871269625856d41d8cd98f00b204e9800998ecf8427e',NULL,NULL),('310258031642345472',NULL,_binary '\0',0,'310258031579430912d41d8cd98f00b204e9800998ecf8427e',NULL,NULL),('310302122711252992',NULL,_binary '\0',0,'310302122665115648d41d8cd98f00b204e9800998ecf8427e',NULL,NULL),('310544188741582848',NULL,_binary '\0',0,'310544188645113856d41d8cd98f00b204e9800998ecf8427e',NULL,NULL),('310598753818050560',NULL,_binary '\0',0,'310598753771913216d41d8cd98f00b204e9800998ecf8427e',NULL,NULL);
/*!40000 ALTER TABLE `server_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `server_user`
--

LOCK TABLES `server_user` WRITE;
/*!40000 ALTER TABLE `server_user` DISABLE KEYS */;
INSERT INTO `server_user` VALUES ('309933779663781888',NULL,_binary '\0',1,NULL,NULL,'http://47.105.188.234:8080/FlutterDemo_war//image/avatar_holder.png',NULL,NULL,_binary '\0','fUAgEFfu',NULL,NULL,NULL,NULL,NULL,'309933779718307840'),('309935820914098176',NULL,_binary '\0',1,NULL,NULL,'http://47.105.188.234:8080/FlutterDemo_war//image/avatar_holder.png',NULL,NULL,_binary '\0','wLWtks0p',NULL,NULL,NULL,NULL,NULL,'309935820956041216'),('309950864032858112',NULL,_binary '\0',1,NULL,NULL,'http://47.105.188.234:8080/FlutterDemo_war//image/avatar_holder.png',NULL,NULL,_binary '\0','zs1KB5eW',NULL,NULL,NULL,NULL,NULL,'309950864041246720'),('310191102936285184',NULL,_binary '\0',1,NULL,NULL,'http://47.105.188.234:8080/FlutterDemo_war//image/avatar_holder.png',NULL,NULL,_binary '\0','H2UjsgiQ',NULL,NULL,NULL,NULL,NULL,'310191102999199744'),('310217871269625856',NULL,_binary '\0',1,NULL,NULL,'http://47.105.188.234:8080/FlutterDemo_war//image/avatar_holder.png',NULL,NULL,_binary '\0','vT2OI2r0',NULL,NULL,NULL,NULL,NULL,'310217871311568896'),('310258031579430912',NULL,_binary '\0',1,NULL,NULL,'http://47.105.188.234:8080/FlutterDemo_war//image/avatar_holder.png',NULL,NULL,_binary '\0','JlpRnLQn','{bcrypt}$2a$10$myRSxdIA3n6iuiPXy4hQVe6M33.dAK6RGA6827nBcZJUTHs.D4Eam','131',NULL,NULL,NULL,'310258031642345472'),('310302122665115648',NULL,_binary '\0',1,NULL,NULL,'http://47.105.188.234:8080/FlutterDemo_war//image/avatar_holder.png',NULL,NULL,_binary '\0','zQSCvG44',NULL,NULL,NULL,NULL,NULL,'310302122711252992'),('310544188645113856',NULL,_binary '\0',1,NULL,NULL,'http://47.105.188.234:8080/FlutterDemo_war//image/avatar_holder.png',NULL,NULL,_binary '\0','53LgSP8j',NULL,NULL,NULL,NULL,NULL,'310544188741582848'),('310598753771913216',NULL,_binary '\0',1,NULL,NULL,'http://47.105.188.234:8080/FlutterDemo_war//image/avatar_holder.png',NULL,NULL,_binary '\0','KVHS98ys',NULL,NULL,NULL,NULL,NULL,'310598753818050560');
/*!40000 ALTER TABLE `server_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `server_user_role`
--

LOCK TABLES `server_user_role` WRITE;
/*!40000 ALTER TABLE `server_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `server_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `server_user_roles`
--

LOCK TABLES `server_user_roles` WRITE;
/*!40000 ALTER TABLE `server_user_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `server_user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `server_video_user`
--

LOCK TABLES `server_video_user` WRITE;
/*!40000 ALTER TABLE `server_video_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `server_video_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-04 20:38:52
