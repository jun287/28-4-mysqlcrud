-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        5.5.32 - MySQL Community Server (GPL)
-- 서버 OS:                        Win32
-- HeidiSQL 버전:                  8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 284db 의 데이터베이스 구조 덤핑
CREATE DATABASE IF NOT EXISTS `284db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `284db`;


-- 테이블 284db의 구조를 덤프합니다. employee
CREATE TABLE IF NOT EXISTS `employee` (
  `employee_no` int(10) NOT NULL AUTO_INCREMENT,
  `employee_name` varchar(50) DEFAULT NULL,
  `employee_age` int(11) DEFAULT NULL,
  PRIMARY KEY (`employee_no`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=euckr;

-- Dumping data for table 284db.employee: ~5 rows (대략적)
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`employee_no`, `employee_name`, `employee_age`) VALUES
	(9, '김호순', 25),
	(11, '최지수', 25),
	(12, '김길동', 50),
	(13, '현희문', 26),
	(14, '정길룡', 27);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;


-- 테이블 284db의 구조를 덤프합니다. employee_addr
CREATE TABLE IF NOT EXISTS `employee_addr` (
  `employee_addr_no` int(10) NOT NULL AUTO_INCREMENT,
  `employee_no` int(10) NOT NULL,
  `employee_addr_content` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`employee_addr_no`),
  KEY `FK_employee_addr_employee` (`employee_no`),
  CONSTRAINT `FK_employee_addr_employee` FOREIGN KEY (`employee_no`) REFERENCES `employee` (`employee_no`)
) ENGINE=InnoDB DEFAULT CHARSET=euckr;

-- Dumping data for table 284db.employee_addr: ~0 rows (대략적)
/*!40000 ALTER TABLE `employee_addr` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_addr` ENABLE KEYS */;


-- 테이블 284db의 구조를 덤프합니다. employee_score
CREATE TABLE IF NOT EXISTS `employee_score` (
  `employee_score_no` int(10) NOT NULL AUTO_INCREMENT,
  `employee_no` int(10) DEFAULT NULL,
  `score` int(10) DEFAULT NULL,
  PRIMARY KEY (`employee_score_no`),
  KEY `FK_member_score_employee` (`employee_no`),
  CONSTRAINT `FK_member_score_employee` FOREIGN KEY (`employee_no`) REFERENCES `employee` (`employee_no`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=euckr;

-- Dumping data for table 284db.employee_score: ~3 rows (대략적)
/*!40000 ALTER TABLE `employee_score` DISABLE KEYS */;
INSERT INTO `employee_score` (`employee_score_no`, `employee_no`, `score`) VALUES
	(1, 9, 90),
	(3, 9, 2),
	(6, 14, 100),
	(7, 11, 50);
/*!40000 ALTER TABLE `employee_score` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
