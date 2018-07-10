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
CREATE DATABASE IF NOT EXISTS `284db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `284db`;


-- 테이블 284db의 구조를 덤프합니다. student
CREATE TABLE IF NOT EXISTS `student` (
  `student_no` int(10) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(50) NOT NULL,
  `student_age` int(10) NOT NULL,
  PRIMARY KEY (`student_no`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=euckr;

-- Dumping data for table 284db.student: ~23 rows (대략적)
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`student_no`, `student_name`, `student_age`) VALUES
	(1, '송원민', 25),
	(2, '탁재은', 23),
	(3, '최윤석', 25),
	(4, '구해성', 20),
	(5, '이광재', 31),
	(6, '김준영', 25),
	(7, '현희문', 26),
	(8, '김진우', 25),
	(9, '송유빈', 22),
	(10, '김호순', 25),
	(11, '이응빈', 24),
	(12, '박원우', 26),
	(13, '김정연', 17),
	(14, '김지완', 26),
	(15, '서연문', 26),
	(16, '김소희', 26),
	(17, '최지수', 25),
	(18, '이경선', 26),
	(19, '정규룡', 26),
	(20, '전재현', 26),
	(21, '이원상', 28),
	(22, '공세준', 32),
	(23, '정민수', 25);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;


-- 테이블 284db의 구조를 덤프합니다. student_addr
CREATE TABLE IF NOT EXISTS `student_addr` (
  `student_addr_no` int(10) NOT NULL AUTO_INCREMENT,
  `student_no` int(10) NOT NULL,
  `student_addr_content` varchar(50) NOT NULL,
  PRIMARY KEY (`student_addr_no`),
  KEY `FK__student` (`student_no`),
  CONSTRAINT `FK__student` FOREIGN KEY (`student_no`) REFERENCES `student` (`student_no`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=euckr;

-- Dumping data for table 284db.student_addr: ~23 rows (대략적)
/*!40000 ALTER TABLE `student_addr` DISABLE KEYS */;
INSERT INTO `student_addr` (`student_addr_no`, `student_no`, `student_addr_content`) VALUES
	(1, 1, '전북 전주시'),
	(2, 2, '전북 전주'),
	(3, 3, '한옥마을'),
	(4, 4, '팔복동'),
	(5, 5, '금암동'),
	(6, 6, '삼례'),
	(7, 7, '송천동'),
	(8, 8, '금암동'),
	(9, 9, '삼천동'),
	(10, 10, '금암동'),
	(11, 11, '효자동'),
	(12, 12, '서신동'),
	(13, 13, '진북동'),
	(14, 14, '중앙동'),
	(15, 15, '우아동'),
	(16, 16, '익산'),
	(17, 17, '군산'),
	(18, 18, '서신동'),
	(19, 19, '삼천동'),
	(20, 20, '호성동'),
	(21, 21, '평화동'),
	(22, 22, '효자동'),
	(23, 23, '팔복동');
/*!40000 ALTER TABLE `student_addr` ENABLE KEYS */;


-- 테이블 284db의 구조를 덤프합니다. student_score
CREATE TABLE IF NOT EXISTS `student_score` (
  `student_score_no` int(10) NOT NULL AUTO_INCREMENT,
  `student_no` int(10) NOT NULL DEFAULT '0',
  `score` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`student_score_no`),
  KEY `FK_student_scroe_student` (`student_no`),
  CONSTRAINT `FK_student_scroe_student` FOREIGN KEY (`student_no`) REFERENCES `student` (`student_no`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=euckr;

-- Dumping data for table 284db.student_score: ~23 rows (대략적)
/*!40000 ALTER TABLE `student_score` DISABLE KEYS */;
INSERT INTO `student_score` (`student_score_no`, `student_no`, `score`) VALUES
	(1, 1, 99),
	(2, 2, 100),
	(3, 3, 77),
	(4, 4, 99),
	(5, 5, 55),
	(6, 6, 77),
	(7, 7, 88),
	(8, 8, 99),
	(9, 9, 32),
	(10, 10, 25),
	(11, 11, 12),
	(12, 12, 66),
	(13, 13, 78),
	(14, 14, 88),
	(15, 15, 34),
	(16, 16, 55),
	(17, 17, 100),
	(18, 18, 42),
	(19, 19, 88),
	(20, 20, 75),
	(21, 21, 80),
	(22, 22, 94),
	(23, 23, 62);
/*!40000 ALTER TABLE `student_score` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
