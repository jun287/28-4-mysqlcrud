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
CREATE DATABASE IF NOT EXISTS `284db` /*!40100 DEFAULT CHARACTER SET euckr */;
USE `284db`;


-- 테이블 284db의 구조를 덤프합니다. teacher
CREATE TABLE IF NOT EXISTS `teacher` (
  `teacher_no` int(10) NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(50) NOT NULL,
  `teacher_age` int(10) NOT NULL,
  PRIMARY KEY (`teacher_no`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=euckr;

-- Dumping data for table 284db.teacher: ~12 rows (대략적)
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` (`teacher_no`, `teacher_name`, `teacher_age`) VALUES
	(1, '홍길동', 20),
	(2, '홍01', 10),
	(3, '홍02', 20),
	(4, '홍03', 30),
	(5, '홍04', 40),
	(6, '홍05', 50),
	(7, '홍06', 60),
	(8, '홍07', 70),
	(9, '홍08', 80),
	(10, '홍09', 90),
	(16, '공세준', 32),
	(17, '정민수', 25),
	(18, '이원상', 28),
	(19, '이경선', 26),
	(20, '현희문', 26),
	(21, '최지수', 25);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;


-- 테이블 284db의 구조를 덤프합니다. teacheraddr
CREATE TABLE IF NOT EXISTS `teacheraddr` (
  `teacher_addr_no` int(10) NOT NULL AUTO_INCREMENT,
  `teacher_no` int(10) DEFAULT NULL,
  `teacher_addr_content` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`teacher_addr_no`),
  KEY `FK_teacheraddr_teacher` (`teacher_no`),
  CONSTRAINT `FK_teacheraddr_teacher` FOREIGN KEY (`teacher_no`) REFERENCES `teacher` (`teacher_no`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=euckr;

-- Dumping data for table 284db.teacheraddr: ~12 rows (대략적)
/*!40000 ALTER TABLE `teacheraddr` DISABLE KEYS */;
INSERT INTO `teacheraddr` (`teacher_addr_no`, `teacher_no`, `teacher_addr_content`) VALUES
	(1, 1, '금암동'),
	(2, 2, '효자동'),
	(3, 3, '서신동'),
	(4, 4, '삼천동'),
	(5, 5, '팔복동'),
	(6, 6, '효자동'),
	(7, 7, '금암동'),
	(8, 8, '서신동'),
	(9, 9, '서서학동'),
	(10, 10, '우아동'),
	(15, 16, '효자동'),
	(16, 17, '팔복동'),
	(17, 18, '평화동'),
	(18, 19, '서신동'),
	(19, 20, '팔복동'),
	(20, 21, '군산');
/*!40000 ALTER TABLE `teacheraddr` ENABLE KEYS */;


-- 테이블 284db의 구조를 덤프합니다. teacher_score
CREATE TABLE IF NOT EXISTS `teacher_score` (
  `teacher_score_no` int(10) NOT NULL AUTO_INCREMENT,
  `teacher_no` int(10) NOT NULL,
  `score` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`teacher_score_no`),
  KEY `FK1` (`teacher_no`),
  CONSTRAINT `FK1` FOREIGN KEY (`teacher_no`) REFERENCES `teacher` (`teacher_no`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=euckr;

-- Dumping data for table 284db.teacher_score: ~12 rows (대략적)
/*!40000 ALTER TABLE `teacher_score` DISABLE KEYS */;
INSERT INTO `teacher_score` (`teacher_score_no`, `teacher_no`, `score`) VALUES
	(1, 1, 90),
	(2, 2, 100),
	(18, 3, 90),
	(19, 4, 70),
	(20, 5, 80),
	(21, 6, 90),
	(22, 8, 100),
	(23, 7, 70),
	(24, 9, 90),
	(25, 10, 90),
	(26, 16, 100),
	(27, 17, 100),
	(28, 18, 100),
	(29, 19, 95),
	(30, 20, 98),
	(31, 21, 100);
/*!40000 ALTER TABLE `teacher_score` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
