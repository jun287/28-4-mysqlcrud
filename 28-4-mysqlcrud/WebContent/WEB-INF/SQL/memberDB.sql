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


-- 테이블 284db의 구조를 덤프합니다. member
CREATE TABLE IF NOT EXISTS `member` (
  `member_no` int(10) NOT NULL AUTO_INCREMENT,
  `member_name` varchar(50) NOT NULL,
  `member_age` int(10) NOT NULL,
  PRIMARY KEY (`member_no`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=euckr;

-- Dumping data for table 284db.member: ~6 rows (대략적)
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`member_no`, `member_name`, `member_age`) VALUES
	(1, '전재현', 26),
	(2, '홍01', 22),
	(3, '홍02', 24),
	(4, '홍03', 27),
	(5, '홍04', 26),
	(6, '홍05', 25);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;


-- 테이블 284db의 구조를 덤프합니다. member_addr
CREATE TABLE IF NOT EXISTS `member_addr` (
  `memberAddr_no` int(10) NOT NULL AUTO_INCREMENT,
  `member_no` int(10) NOT NULL,
  `memberAddr_content` varchar(50) NOT NULL,
  PRIMARY KEY (`memberAddr_no`),
  KEY `member_no` (`member_no`),
  CONSTRAINT `member_no` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=euckr;

-- Dumping data for table 284db.member_addr: ~1 rows (대략적)
/*!40000 ALTER TABLE `member_addr` DISABLE KEYS */;
INSERT INTO `member_addr` (`memberAddr_no`, `member_no`, `memberAddr_content`) VALUES
	(1, 1, '전주시 덕진구');
/*!40000 ALTER TABLE `member_addr` ENABLE KEYS */;


-- 테이블 284db의 구조를 덤프합니다. member_score
CREATE TABLE IF NOT EXISTS `member_score` (
  `member_score_no` int(10) NOT NULL AUTO_INCREMENT,
  `member_no` int(10) NOT NULL,
  `score` int(10) NOT NULL,
  PRIMARY KEY (`member_score_no`),
  KEY `FK__member` (`member_no`),
  CONSTRAINT `FK__member` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=euckr;

-- Dumping data for table 284db.member_score: ~2 rows (대략적)
/*!40000 ALTER TABLE `member_score` DISABLE KEYS */;
INSERT INTO `member_score` (`member_score_no`, `member_no`, `score`) VALUES
	(1, 1, 87),
	(3, 2, 87),
	(4, 3, 95),
	(5, 4, 64),
	(6, 5, 47);
/*!40000 ALTER TABLE `member_score` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
