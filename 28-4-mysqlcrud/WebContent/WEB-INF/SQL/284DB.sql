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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=euckr;

-- Dumping data for table 284db.employee_addr: ~5 rows (대략적)
/*!40000 ALTER TABLE `employee_addr` DISABLE KEYS */;
INSERT INTO `employee_addr` (`employee_addr_no`, `employee_no`, `employee_addr_content`) VALUES
	(6, 9, '금암동'),
	(7, 11, '군산'),
	(8, 12, '서울'),
	(9, 13, '송천동'),
	(10, 14, '삼천동');
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

-- Dumping data for table 284db.employee_score: ~4 rows (대략적)
/*!40000 ALTER TABLE `employee_score` DISABLE KEYS */;
INSERT INTO `employee_score` (`employee_score_no`, `employee_no`, `score`) VALUES
	(1, 9, 90),
	(3, 9, 2),
	(6, 14, 100),
	(7, 11, 50);
/*!40000 ALTER TABLE `employee_score` ENABLE KEYS */;


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
	`memberAddr_content` VARCHAR(50) NOT NULL,
	`memberAddr_date` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`memberAddr_no`),
	INDEX `member_no` (`member_no`),
	CONSTRAINT `member_no` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
)
COLLATE='euckr_korean_ci'
ENGINE=InnoDB
AUTO_INCREMENT=4;
	(1, 1, '전주시 덕진구'),
	(2, 2, '금암동'),
	(3, 3, '서신동'),
	(4, 4, '효자동'),
	(5, 5, '송천동');
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

-- Dumping data for table 284db.member_score: ~5 rows (대략적)
/*!40000 ALTER TABLE `member_score` DISABLE KEYS */;
INSERT INTO `member_score` (`member_score_no`, `member_no`, `score`) VALUES
	(1, 1, 87),
	(3, 2, 87),
	(4, 3, 95),
	(5, 4, 64),
	(6, 5, 47);
/*!40000 ALTER TABLE `member_score` ENABLE KEYS */;


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


-- 테이블 284db의 구조를 덤프합니다. teacher
CREATE TABLE IF NOT EXISTS `teacher` (
  `teacher_no` int(10) NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(50) NOT NULL,
  `teacher_age` int(10) NOT NULL,
  PRIMARY KEY (`teacher_no`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=euckr;

-- Dumping data for table 284db.teacher: ~16 rows (대략적)
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=euckr;

-- Dumping data for table 284db.teacheraddr: ~16 rows (대략적)
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=euckr;

-- Dumping data for table 284db.teacher_score: ~16 rows (대략적)
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
