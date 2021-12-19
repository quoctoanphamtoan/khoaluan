-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: khoaluan
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `bangdiemtongket`
--

DROP TABLE IF EXISTS `bangdiemtongket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bangdiemtongket` (
  `id` int NOT NULL AUTO_INCREMENT,
  `diemTbTL` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bangdiemtongket`
--

LOCK TABLES `bangdiemtongket` WRITE;
/*!40000 ALTER TABLE `bangdiemtongket` DISABLE KEYS */;
INSERT INTO `bangdiemtongket` VALUES (11,1.8),(12,7.69),(13,7.51),(14,7.05),(15,5.9033),(16,6.07),(17,5.5),(18,7.72333333333),(19,5.03),(20,0);
/*!40000 ALTER TABLE `bangdiemtongket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `canhbao`
--

DROP TABLE IF EXISTS `canhbao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `canhbao` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tieuDe` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `noiDung` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ngaySua` date DEFAULT NULL,
  `ngayTao` date DEFAULT NULL,
  `idGiangVien` int NOT NULL,
  `idSinhVien` int NOT NULL,
  `trangThai` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`,`idGiangVien`,`idSinhVien`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_CanhBao_GiangVien1` (`idGiangVien`),
  KEY `fk_CanhBao_sinhvien1` (`idSinhVien`),
  CONSTRAINT `fk_CanhBao_GiangVien1` FOREIGN KEY (`idGiangVien`) REFERENCES `giangvien` (`id`),
  CONSTRAINT `fk_CanhBao_sinhvien1` FOREIGN KEY (`idSinhVien`) REFERENCES `sinhvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `canhbao`
--

LOCK TABLES `canhbao` WRITE;
/*!40000 ALTER TABLE `canhbao` DISABLE KEYS */;
INSERT INTO `canhbao` VALUES (18,'Cảnh báo về vụ việc cấm thi','em bị cấm thi vì nghỉ học nhiều','2021-12-19','2021-12-19',1,2,0);
/*!40000 ALTER TABLE `canhbao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diem_sinhvien_monhoc`
--

DROP TABLE IF EXISTS `diem_sinhvien_monhoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diem_sinhvien_monhoc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `diemCK` double DEFAULT NULL,
  `diemGK` double DEFAULT NULL,
  `diemTBC` double DEFAULT NULL,
  `diemTK1` double DEFAULT NULL,
  `diemTK2` double DEFAULT NULL,
  `diemTK3` double DEFAULT NULL,
  `idMonHoc` int NOT NULL,
  `idBangDiemTongKet` int NOT NULL,
  PRIMARY KEY (`id`,`idBangDiemTongKet`,`idMonHoc`),
  KEY `fk_Diem_SinhVien_MonHoc_MonHoc1` (`idMonHoc`),
  KEY `fk_Diem_SinhVien_MonHoc_BangDiemTongKet1` (`idBangDiemTongKet`),
  CONSTRAINT `fk_Diem_SinhVien_MonHoc_BangDiemTongKet1` FOREIGN KEY (`idBangDiemTongKet`) REFERENCES `bangdiemtongket` (`id`),
  CONSTRAINT `fk_Diem_SinhVien_MonHoc_MonHoc1` FOREIGN KEY (`idMonHoc`) REFERENCES `monhoc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diem_sinhvien_monhoc`
--

LOCK TABLES `diem_sinhvien_monhoc` WRITE;
/*!40000 ALTER TABLE `diem_sinhvien_monhoc` DISABLE KEYS */;
INSERT INTO `diem_sinhvien_monhoc` VALUES (16,7,10,7.37,2,1,10,1,11),(17,0,0,0,10,10,10,2,11),(18,9,8,7.87,6.5,2,6,1,12),(19,9,8,7.87,6.5,2,6,2,12),(20,6,8,7.4,10,10,10,1,13),(21,6,8,7.4,10,10,10,2,13),(22,10,9,7.97,1,2,1,1,14),(23,8,4,6,1,2,9,2,14),(24,3,7.2,5.29,9,8,7.5,1,15),(25,3,7.2,5.29,9,8,7.5,2,15),(26,5,9,5.47,1,1,2,1,16),(27,5,9,5.47,1,1,2,2,16),(28,5,5,5.27,8,5,6,1,17),(29,5,5,5.27,8,5,6,2,17),(30,8,8,8.07,9,8,8,1,18),(31,8,8,8.07,9,8,8,2,18),(32,3,2,3.43,9,6,5,1,19),(33,3,2,3.43,9,6,5,2,19),(34,0,0,0,5,2,0,3,11),(35,0,0,0,8,9,5,4,11),(36,8,6,7.33,10,8,5,3,12),(37,-1,6,0,8,8,8,4,12),(38,8,8,7.73,9,6,5,3,13),(39,-1,5,0,9,9,8,4,13),(40,7,7,7.2,8,8,8,3,14),(41,-1,6,0,6,6,6,4,14),(42,8,6,7.13,9,6,5,3,15),(43,-1,7,0,7,7,7,4,15),(44,6,8,7.27,10,10,8,3,16),(45,-1,8,0,8,8,8,4,16),(46,8,2,5.93,9,5,6,3,17),(47,-1,8,0,4,4,4,4,17),(48,8,5,7.03,9,6,8,3,18),(49,-1,5,0,5,5,5,4,18),(50,9,8,8.23,9,8,3,3,19),(51,-1,5,0,6,8,5,4,19);
/*!40000 ALTER TABLE `diem_sinhvien_monhoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donxinnghihoc`
--

DROP TABLE IF EXISTS `donxinnghihoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donxinnghihoc` (
  `ngayTao` date DEFAULT NULL,
  `ngaySua` date DEFAULT NULL,
  `noiDung` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `ngayNghi` date DEFAULT NULL,
  `idLopHocPhan` int DEFAULT NULL,
  `idSinhVien` int DEFAULT NULL,
  `trangThai` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_DonXinNghiHoc_LopHocPhan1` (`idLopHocPhan`),
  KEY `fk_DonXinNghiHoc_sinhvien1` (`idSinhVien`),
  CONSTRAINT `fk_DonXinNghiHoc_LopHocPhan1` FOREIGN KEY (`idLopHocPhan`) REFERENCES `lophocphan` (`id`),
  CONSTRAINT `fk_DonXinNghiHoc_sinhvien1` FOREIGN KEY (`idSinhVien`) REFERENCES `sinhvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donxinnghihoc`
--

LOCK TABLES `donxinnghihoc` WRITE;
/*!40000 ALTER TABLE `donxinnghihoc` DISABLE KEYS */;
INSERT INTO `donxinnghihoc` VALUES ('2021-12-19','2021-12-19','Thưa cô em xin nghỉ học vì hôm đấy em sẽ về quê khám nghĩa vụ',10,'2021-12-22',24,2,1);
/*!40000 ALTER TABLE `donxinnghihoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giangvien`
--

DROP TABLE IF EXISTS `giangvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giangvien` (
  `id` int NOT NULL AUTO_INCREMENT,
  `maGiangVien` varchar(10) NOT NULL,
  `hoTen` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `diaChi` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gioiTinh` tinyint DEFAULT NULL,
  `soDienThoai` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `chuyenNganh` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ngaySua` date DEFAULT NULL,
  `ngayTao` date DEFAULT NULL,
  `idTaiKhoan` int NOT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `imgUrl` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`,`idTaiKhoan`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `maGiangVien_UNIQUE` (`maGiangVien`),
  UNIQUE KEY `taikhoan_id_UNIQUE` (`idTaiKhoan`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  CONSTRAINT `fk_GiangVien_taikhoan1` FOREIGN KEY (`idTaiKhoan`) REFERENCES `taikhoan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giangvien`
--

LOCK TABLES `giangvien` WRITE;
/*!40000 ALTER TABLE `giangvien` DISABLE KEYS */;
INSERT INTO `giangvien` VALUES (1,'gv5e02','Lê Nguyễn Trung Đan','Gò vấp',0,'+8425252525','binz@gmail.com','Âm Nhạc','2021-11-28','2021-11-28',3,'CONG_TAC',NULL),(11,'gv02bc','Vũ Đức Thiện','59 Cao Văn',1,'+847777774444','thienvu@gmail.com','Âm Nhạc','2021-12-18','2021-12-18',99,'CONG_TAC',NULL),(12,'gv5707','Phạm Anh Khoa','59 Thống Nhất',1,'+840202585858','anhkhoapham@gmail.com',NULL,'2021-12-18','2021-12-18',100,'CONG_TAC',NULL),(13,'gv779f','Tạ Thị Bích Trâm','59 Đống Đô',0,'+847897897895','tramta@gmail.com',NULL,'2021-12-18','2021-12-18',101,'CONG_TAC',NULL);
/*!40000 ALTER TABLE `giangvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lop`
--

DROP TABLE IF EXISTS `lop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lop` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tenLop` varchar(45) DEFAULT NULL,
  `chuyenNganh` varchar(45) DEFAULT NULL,
  `idGiangVien` int NOT NULL,
  `khoaHoc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`idGiangVien`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_Lop_GiangVien1` (`idGiangVien`),
  CONSTRAINT `fk_Lop_GiangVien1` FOREIGN KEY (`idGiangVien`) REFERENCES `giangvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lop`
--

LOCK TABLES `lop` WRITE;
/*!40000 ALTER TABLE `lop` DISABLE KEYS */;
INSERT INTO `lop` VALUES (21,'Đại Học Viện Âm Nhạc','Âm Nhạc',11,'2021/2024');
/*!40000 ALTER TABLE `lop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lophocphan`
--

DROP TABLE IF EXISTS `lophocphan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lophocphan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tenLopHocPhan` varchar(45) DEFAULT NULL,
  `idMonHoc` int NOT NULL,
  `idGiangVien` int NOT NULL,
  `thu` varchar(45) DEFAULT NULL,
  `tiet` varchar(45) DEFAULT NULL,
  `hocki` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`idMonHoc`,`idGiangVien`),
  KEY `fk_LopHocPhan_MonHoc1` (`idMonHoc`),
  KEY `fk_LopHocPhan_GiangVien1` (`idGiangVien`),
  CONSTRAINT `fk_LopHocPhan_GiangVien1` FOREIGN KEY (`idGiangVien`) REFERENCES `giangvien` (`id`),
  CONSTRAINT `fk_LopHocPhan_MonHoc1` FOREIGN KEY (`idMonHoc`) REFERENCES `monhoc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lophocphan`
--

LOCK TABLES `lophocphan` WRITE;
/*!40000 ALTER TABLE `lophocphan` DISABLE KEYS */;
INSERT INTO `lophocphan` VALUES (21,'Đại Học Hòa Âm 2',1,1,'2','3-4','2/2021-2022'),(22,'Đại Học Phối Khi',2,12,'3','8-12','2/2021-2022'),(23,'Đại Học Văn Nhạc 1',3,13,'7','10-12','2/2021-2022'),(24,'Đại Học Âm Nhạc Dân Tộc',4,1,'6','4-6','2/2021-2022');
/*!40000 ALTER TABLE `lophocphan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monhoc`
--

DROP TABLE IF EXISTS `monhoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `monhoc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tenMonHoc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `tenChuyenNganh` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monhoc`
--

LOCK TABLES `monhoc` WRITE;
/*!40000 ALTER TABLE `monhoc` DISABLE KEYS */;
INSERT INTO `monhoc` VALUES (1,'Phối Khí','Âm Nhạc'),(2,'Thanh Nhạc','Âm Nhạc'),(3,'Âm Nhạc 2','Âm Nhạc'),(4,'Đàn Tranh','Âm Nhạc'),(5,'Tiếng Anh 1','Ngoại Ngữ'),(6,'Tiếng Anh 2','Ngoại Ngữ'),(7,'Chi tiết máy','Cơ Khí'),(8,'Máy Nén Thủy Lực','Cơ Khí'),(9,'Văn Hóa Anh','Ngoại Ngữ'),(10,'Công nghệ phần mềm','CNTT'),(11,'Chủ nghĩ Mac-Lenin','Chính trị'),(12,'Toán A1','Cơ Bản'),(13,'Cơ học lý thuyết','Cơ Khí'),(14,'Sức bền vật liệu','Cơ Khí'),(15,'Kỹ thuật vật liệu và composite','Cơ Khí'),(16,'Văn hóa - xã hội Anh - Mỹ ','Ngoại Ngữ'),(17,'Thư tín thương mại','Ngoại Ngữ'),(18,'Kỹ năng thi tiếng Anh quốc tế ','Ngoại Ngữ'),(19,'Lập trình hướng đối tượng','CNTT'),(20,'Lập trình WWW','CNTT');
/*!40000 ALTER TABLE `monhoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ngaynghihoc`
--

DROP TABLE IF EXISTS `ngaynghihoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ngaynghihoc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ngayNghi` date DEFAULT NULL,
  `coPhep` tinyint DEFAULT NULL,
  `idSinhVien_LopHocPhan` int NOT NULL,
  PRIMARY KEY (`id`,`idSinhVien_LopHocPhan`),
  KEY `fk_NgayNghiHoc_SinhVien_LopHocPhan1` (`idSinhVien_LopHocPhan`),
  CONSTRAINT `fk_NgayNghiHoc_SinhVien_LopHocPhan1` FOREIGN KEY (`idSinhVien_LopHocPhan`) REFERENCES `sinhvien_lophocphan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ngaynghihoc`
--

LOCK TABLES `ngaynghihoc` WRITE;
/*!40000 ALTER TABLE `ngaynghihoc` DISABLE KEYS */;
INSERT INTO `ngaynghihoc` VALUES (79,'2021-12-19',1,79),(80,'2021-12-19',1,79),(81,'2021-12-19',1,79),(83,'2021-12-19',1,79),(84,'2021-12-19',1,79);
/*!40000 ALTER TABLE `ngaynghihoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phuhuynh`
--

DROP TABLE IF EXISTS `phuhuynh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phuhuynh` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hoTen` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `diaChi` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gioiTinh` tinyint DEFAULT NULL,
  `soDienThoai` varchar(15) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `idTaiKhoan` int NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`idTaiKhoan`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `soDienThoai_UNIQUE` (`soDienThoai`),
  UNIQUE KEY `idTaiKhoan_UNIQUE` (`idTaiKhoan`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  CONSTRAINT `fk_phuhuynh_taikhoan1` FOREIGN KEY (`idTaiKhoan`) REFERENCES `taikhoan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phuhuynh`
--

LOCK TABLES `phuhuynh` WRITE;
/*!40000 ALTER TABLE `phuhuynh` DISABLE KEYS */;
INSERT INTO `phuhuynh` VALUES (2,'Nguyễn Thị Lắng','string',1,'+840393264509','string',22,'2021-12-07 10:59:40','2021-12-07 10:59:40','HOAT_DONG');
/*!40000 ALTER TABLE `phuhuynh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sinhvien`
--

DROP TABLE IF EXISTS `sinhvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sinhvien` (
  `id` int NOT NULL AUTO_INCREMENT,
  `maSinhVien` varchar(10) NOT NULL,
  `hoTen` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `diaChi` varchar(100) DEFAULT NULL,
  `gioiTinh` tinyint NOT NULL,
  `soDienThoai` varchar(15) NOT NULL,
  `email` varchar(100) NOT NULL,
  `idLop` int DEFAULT NULL,
  `chuyenNganh` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ngaySua` date DEFAULT NULL,
  `ngayTao` date DEFAULT NULL,
  `idPhuHuynh` int DEFAULT NULL,
  `idTaiKhoan` int NOT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `imgUrl` varchar(1000) DEFAULT NULL,
  `idBangDiemTongKet` int NOT NULL,
  PRIMARY KEY (`id`,`idTaiKhoan`,`idBangDiemTongKet`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `taikhoan_id_UNIQUE` (`idTaiKhoan`),
  UNIQUE KEY `soDienThoai_UNIQUE` (`soDienThoai`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_SinhVien_PhuHuynh1` (`idPhuHuynh`),
  KEY `FK3b7wr08416aalvr51xnfoemhj` (`idLop`),
  KEY `fk_sinhvien_bangdiemtongket1_idx` (`idBangDiemTongKet`),
  CONSTRAINT `FK3b7wr08416aalvr51xnfoemhj` FOREIGN KEY (`idLop`) REFERENCES `lop` (`id`),
  CONSTRAINT `fk_sinhvien_bangdiemtongket1` FOREIGN KEY (`idBangDiemTongKet`) REFERENCES `bangdiemtongket` (`id`),
  CONSTRAINT `fk_SinhVien_PhuHuynh1` FOREIGN KEY (`idPhuHuynh`) REFERENCES `phuhuynh` (`id`),
  CONSTRAINT `fk_sinhvien_taikhoan1` FOREIGN KEY (`idTaiKhoan`) REFERENCES `taikhoan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sinhvien`
--

LOCK TABLES `sinhvien` WRITE;
/*!40000 ALTER TABLE `sinhvien` DISABLE KEYS */;
INSERT INTO `sinhvien` VALUES (2,'sv68a0','Phạm Quốc Toàn','369 Nguyễn Thái Bình Phường 12 Quận Tân Bình TPHCM',1,'+840364583782','phamquoctoan11081999@gmail.com',21,'Âm Nhạc','2021-12-19','2021-11-28',2,2,'DANG_HOC','https://s3.ap-southeast-1.amazonaws.com/khoaluantotnghiep/1639918657143-avatar.jpg',11),(78,'sv9171','Phạm Tuán','35 Nguyễn Trãi',1,'+842526525655','phamtuan@gmail.com',21,'Âm Nhạc','2021-12-18','2021-12-18',NULL,91,'DANG_HOC','https://s3.ap-southeast-1.amazonaws.com/khoaluantotnghiep/1639918657143-avatar.jpg',12),(79,'sv2a40','Phạm Thanh','35 Phan văn trị',1,'+842526525611','phamthanh@gmail.com',21,'Âm Nhạc','2021-12-18','2021-12-18',NULL,92,'DANG_HOC','https://s3.ap-southeast-1.amazonaws.com/khoaluantotnghiep/1639918657143-avatar.jpg',13),(80,'sv8a1d','Phạm Học','89 Hoàng Châu',1,'+842526525741','phamhoc@gmail.com',21,'Âm Nhạc','2021-12-18','2021-12-18',NULL,93,'DANG_HOC','https://s3.ap-southeast-1.amazonaws.com/khoaluantotnghiep/1639918657143-avatar.jpg',14),(81,'sva1d9','Ngọc Trinh','89 Thảo Điền',0,'+849999999999','ngoctrinh@gmail.com',21,'Âm Nhạc','2021-12-18','2021-12-18',NULL,94,'DANG_HOC','https://s3.ap-southeast-1.amazonaws.com/khoaluantotnghiep/1639918657143-avatar.jpg',15),(82,'sv97a2','Ninh Dương Lan Ngọc','89 Phú Mỹ Hưng',0,'+848888888888','ninhduonglanngoc@gmail.com',21,'Âm Nhạc','2021-12-18','2021-12-18',NULL,95,'DANG_HOC','https://s3.ap-southeast-1.amazonaws.com/khoaluantotnghiep/1639918657143-avatar.jpg',16),(83,'sv310d','Hồ Ngọc Hà','21 Nguyễn Kiệm',0,'+84777777777','haho@gmail.com',21,'Âm Nhạc','2021-12-18','2021-12-18',NULL,96,'DANG_HOC','https://s3.ap-southeast-1.amazonaws.com/khoaluantotnghiep/1639918657143-avatar.jpg',17),(84,'sv100b','Angela Phương Trinh','21 Đồng Nai',0,'+840000000000','angelaphuongtrinh@gmail.com',21,'Âm Nhạc','2021-12-18','2021-12-18',NULL,97,'DANG_HOC','https://s3.ap-southeast-1.amazonaws.com/khoaluantotnghiep/1639918657143-avatar.jpg',18),(85,'sva541','Châu Hoàng Mỹ My','59 Đồng Nai',0,'+848989898989','tuhy@gmail.com',21,'Âm Nhạc','2021-12-18','2021-12-18',NULL,98,'DANG_HOC','https://s3.ap-southeast-1.amazonaws.com/khoaluantotnghiep/1639918657143-avatar.jpg',19),(86,'sv059e','Hoàng Khang','58 Lê Lai',1,'+844888555555','hoangkhang1@gmail.com',21,'Âm Nhạc','2021-12-19','2021-12-19',NULL,104,'DANG_HOC','https://s3.ap-southeast-1.amazonaws.com/khoaluantotnghiep/1639918657143-avatar.jpg',20);
/*!40000 ALTER TABLE `sinhvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sinhvien_lophocphan`
--

DROP TABLE IF EXISTS `sinhvien_lophocphan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sinhvien_lophocphan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `trangThai` tinyint DEFAULT NULL,
  `idSinhVien` int NOT NULL,
  `idLopHocPhan` int NOT NULL,
  PRIMARY KEY (`id`,`idSinhVien`,`idLopHocPhan`),
  KEY `fk_SinhVien_LopHocPhan_sinhvien1` (`idSinhVien`),
  KEY `fk_SinhVien_LopHocPhan_LopHocPhan1` (`idLopHocPhan`),
  CONSTRAINT `fk_SinhVien_LopHocPhan_LopHocPhan1` FOREIGN KEY (`idLopHocPhan`) REFERENCES `lophocphan` (`id`),
  CONSTRAINT `fk_SinhVien_LopHocPhan_sinhvien1` FOREIGN KEY (`idSinhVien`) REFERENCES `sinhvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sinhvien_lophocphan`
--

LOCK TABLES `sinhvien_lophocphan` WRITE;
/*!40000 ALTER TABLE `sinhvien_lophocphan` DISABLE KEYS */;
INSERT INTO `sinhvien_lophocphan` VALUES (52,1,2,21),(53,1,78,21),(54,1,79,21),(55,1,80,21),(56,1,81,21),(57,1,82,21),(58,1,83,21),(59,1,84,21),(60,1,85,21),(61,1,78,22),(62,1,79,22),(63,1,80,22),(64,1,81,22),(65,1,82,22),(66,1,83,22),(67,1,84,22),(68,1,85,22),(69,0,2,22),(70,1,78,23),(71,1,79,23),(72,1,80,23),(73,1,81,23),(74,1,82,23),(75,1,83,23),(76,1,84,23),(77,1,85,23),(78,0,2,23),(79,1,78,24),(80,1,79,24),(81,1,80,24),(82,1,81,24),(83,1,82,24),(84,1,83,24),(85,1,84,24),(86,1,85,24),(87,0,2,24);
/*!40000 ALTER TABLE `sinhvien_lophocphan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tenDangNhap` varchar(45) NOT NULL,
  `matKhau` varchar(150) NOT NULL,
  `role` varchar(10) NOT NULL,
  `ngayTao` date NOT NULL,
  `ngaySua` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `tenDangNhap_UNIQUE` (`tenDangNhap`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES (2,'sv68a0','$2a$10$f47a29zW.tGzu9Z0nhRxaOkQL4HL//cC1hdRjJtBuI26QoeNYmwJ2','SINH_VIEN','2021-11-28','2021-12-19'),(3,'gv5e02','$2a$10$EGYK//X/47ZS6qDsvXKibuR0YzS0KktcoVVGCGhoSjqqgXLxhItCK','GIANG_VIEN','2021-11-28','2021-12-17'),(22,'+840393264509','$2a$10$kTI4Yl7gh2SpAaeUEf07Vuv4.yypY8iG7awIzqXrz5PdHZHQGr7Cm','PHU_HUYNH','2021-12-07','2021-12-07'),(91,'sv9171','$2a$10$3ztfU9AVY0Fc/kJRjzkwtewsfvbZREMUFkUogeYfv0Q6HM7bLbTLS','SINH_VIEN','2021-12-18','2021-12-18'),(92,'sv2a40','$2a$10$X8h1hi2joGVx4.0iViQmn.efUM5YmY4kC1XvKdr6opN9SHdCUm/Zu','SINH_VIEN','2021-12-18','2021-12-18'),(93,'sv8a1d','$2a$10$RlxneAiYsO8IFcJHKZvfueMkziu53zcS7hWi7Zf3euQGCBvsfQWX.','SINH_VIEN','2021-12-18','2021-12-18'),(94,'sva1d9','$2a$10$XFVi6fVAb29sWCAgWwagS.c5knJxKDo25k8InxnGPcy5AiWRAVCHu','SINH_VIEN','2021-12-18','2021-12-18'),(95,'sv97a2','$2a$10$XqTgVLvPujgPYG4MLD/2MOuPPinD6lXBdWLUk6Ob5kXoWzU134ytq','SINH_VIEN','2021-12-18','2021-12-18'),(96,'sv310d','$2a$10$juP7nrtS6DmkLnJp9FWo0.19jJ56sunsYRjf3DbUdYPtc3/fC2FIe','SINH_VIEN','2021-12-18','2021-12-18'),(97,'sv100b','$2a$10$c8DFeFzUtFCEb.LsxcBNMeXEcHg3VhyfGldoasenwvUZAPxWAD8wa','SINH_VIEN','2021-12-18','2021-12-18'),(98,'sva541','$2a$10$0DMqPmsQ14YpzABAv/jAoOsFD3ZGgv2uiA/RI2vDNKu4iqsfOtzii','SINH_VIEN','2021-12-18','2021-12-18'),(99,'gv02bc','$2a$10$ATdtz2Lu6CbZLU5ImRirBO.qGxFN23YZDu2DmGLIgRk4WGrwBmv.2','GIANG_VIEN','2021-12-18','2021-12-18'),(100,'gv5707','$2a$10$D.riYaJxtZZ.qJU/0u/XS.3DC/F8Cx72Ec0DRtlJvrdUvPL0Vw/VK','GIANG_VIEN','2021-12-18','2021-12-18'),(101,'gv779f','$2a$10$4dDaQNA.XOPAeCEhivbKkO9ElbwYx8Tri3jOuzvJgSG9cB/PTXUv2','GIANG_VIEN','2021-12-18','2021-12-18'),(104,'sv059e','$2a$10$zEz307iwcSVF6AUo1edFwujHB6I0l.KLXD2m5Gf7HC/sTKmlt/69i','SINH_VIEN','2021-12-19','2021-12-19');
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thongbao`
--

DROP TABLE IF EXISTS `thongbao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thongbao` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ngayTao` date DEFAULT NULL,
  `noiDung` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `tieuDe` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `idGiangVien` int NOT NULL,
  `trangThai` tinyint DEFAULT NULL,
  `ngaySua` date DEFAULT NULL,
  `hienThi` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`,`idGiangVien`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_ThongBao_GiangVien1` (`idGiangVien`),
  CONSTRAINT `fk_ThongBao_GiangVien1` FOREIGN KEY (`idGiangVien`) REFERENCES `giangvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thongbao`
--

LOCK TABLES `thongbao` WRITE;
/*!40000 ALTER TABLE `thongbao` DISABLE KEYS */;
INSERT INTO `thongbao` VALUES (23,'2021-12-19','Do tuần này cô sẽ bận họp với khoa nên sẽ cho cả lớp nghỉ và sẽ bắt đầu kiểm vào tuần sau !','Thông báo về thời gian kiểm tra',1,0,'2021-12-19',1),(24,'2021-12-19','Lịch thi cuối kì sẽ được dời lại vào cuối tháng 2/2022','Thông báo về việc dời lịch thi',1,0,'2021-12-19',1);
/*!40000 ALTER TABLE `thongbao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thongbao_lop`
--

DROP TABLE IF EXISTS `thongbao_lop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thongbao_lop` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idLop` int NOT NULL,
  `idThongBao` int NOT NULL,
  PRIMARY KEY (`id`,`idLop`,`idThongBao`),
  KEY `fk_ThongBao_Lop_Lop1` (`idLop`),
  KEY `fk_ThongBao_Lop_ThongBao1` (`idThongBao`),
  CONSTRAINT `fk_ThongBao_Lop_Lop1` FOREIGN KEY (`idLop`) REFERENCES `lop` (`id`),
  CONSTRAINT `fk_ThongBao_Lop_ThongBao1` FOREIGN KEY (`idThongBao`) REFERENCES `thongbao` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thongbao_lop`
--

LOCK TABLES `thongbao_lop` WRITE;
/*!40000 ALTER TABLE `thongbao_lop` DISABLE KEYS */;
/*!40000 ALTER TABLE `thongbao_lop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thongbao_lophocphan`
--

DROP TABLE IF EXISTS `thongbao_lophocphan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thongbao_lophocphan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idThongBao` int NOT NULL,
  `idLopHocPhan` int NOT NULL,
  PRIMARY KEY (`id`,`idThongBao`,`idLopHocPhan`),
  KEY `fk_ThongBao_LopHocPhan_ThongBao2` (`idThongBao`),
  KEY `fk_ThongBao_LopHocPhan_LopHocPhan2` (`idLopHocPhan`),
  CONSTRAINT `fk_ThongBao_LopHocPhan_LopHocPhan2` FOREIGN KEY (`idLopHocPhan`) REFERENCES `lophocphan` (`id`),
  CONSTRAINT `fk_ThongBao_LopHocPhan_ThongBao2` FOREIGN KEY (`idThongBao`) REFERENCES `thongbao` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thongbao_lophocphan`
--

LOCK TABLES `thongbao_lophocphan` WRITE;
/*!40000 ALTER TABLE `thongbao_lophocphan` DISABLE KEYS */;
INSERT INTO `thongbao_lophocphan` VALUES (16,23,21),(17,24,24);
/*!40000 ALTER TABLE `thongbao_lophocphan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-19 22:19:21
