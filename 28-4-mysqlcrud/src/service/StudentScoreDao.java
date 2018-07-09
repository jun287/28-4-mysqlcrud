// 28기 2018. 7. 9(월) 이원상 StudentScoreDao.java
package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import service.Student;
import service.StudentScore;
import service.StudentAndScore;

public class StudentScoreDao {
	/*
	메소드 설명	
	1. 용도 : 학생의 전체의 번호, 이름, 나이, 점수번호, 점수를 조회하는 메소드(Database내 Student테이블,Student테이블 innet 조인 결과 조회).
	2. 매개변수 : 없음
	3. 리턴값 : StudentAndScore클래스로 생성된 객체참조변수 참조값을 담은 리스트.
		- StudentAndScore클래스 프로퍼티 : private Student student, private StudentScore studentScore;
	*/
	public ArrayList<StudentAndScore> selectStudentAndScore(){
		ArrayList<StudentAndScore> studentAndScoreList = new ArrayList<StudentAndScore>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcDriver = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			connection = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
			System.out.println(connection + "<-- connection");
			preparedStatement = connection.prepareStatement("select student.student_no, student.student_name, student.student_age, studentscore.student_score_no, studentscore.score from student student inner join student_score studentscore on student.student_no = studentscore.student_no");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Student student = new Student();
				StudentScore studentScore = new StudentScore();
				StudentAndScore studentAndScore = new StudentAndScore();
				student.setStudentNo(resultSet.getInt("student_no"));
				student.setStudentName(resultSet.getString("student_name"));
				student.setStudentAge(resultSet.getInt("student_age"));
				studentScore.setStudentNumber(resultSet.getInt("student_no"));
				studentScore.setStudentScoreNumber(resultSet.getInt("student_score_no"));
				studentScore.setScore(resultSet.getInt("score"));
				studentAndScore.setStudent(student);
				studentAndScore.setStudentScore(studentScore);
				studentAndScoreList.add(studentAndScore);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}

		return studentAndScoreList;
		
	}
	
	/*
	메소드 설명	
	1. 용도 : 학생 1명의 점수를 입력하는 메소드임(Database내 Student_score테이블의 1개 행을 입력하는 메소드).
	2. 매개변수 : studentNo(Student테이블내 studentNo컬럼을 참조받아 Student_score테이블내 사용하는 외래키 값), score(Student_score테이블 컬럼에 담길 값)
	3. 리턴값 : 없음.
	4. StudentScore Class 프로퍼티
		- 접근지정자는 모두 private임. int studentScoreNumber,int studentNumber,int score
	*/	
	public void insertStudentScore(int studentNo, int score) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcDriver = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			connection = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
			System.out.println(connection + "<-- connection");
			preparedStatement = connection.prepareStatement("INSERT INTO student_score (student_no, score) VALUES (?, ?)");
			preparedStatement.setInt(1, studentNo);
			preparedStatement.setInt(2, score);
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	
	/*
	메소드 설명	
	1. 용도 : 학생 1명의 점수가 몇개가 입력되어있는지 체크하는 메소드임(Database내 Student_score테이블의 특정 student_no를 가진 행을 조회하는 메소드).
	2. 매개변수 : studentNo(Student테이블내 studentNo컬럼을 참조받아 Student_score테이블내 사용하는 외래키 값)
	3. 리턴값 : int(Student_score테이블의 특정 student_no를 가진 행의 수를 세어 정수형태로 리턴)
	4. StudentScore Class 프로퍼티
		- 접근지정자는 모두 private임. int studentScoreNumber,int studentNumber,int score
	*/	
	public int countStudentScore(int studentNo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcDriver = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			connection = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
			System.out.println(connection + "<-- connection");
			preparedStatement = connection.prepareStatement("select count(student_no) as count from student_score where student_no = ?");
			preparedStatement.setInt(1, studentNo);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				count=resultSet.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return count;
	}
	
	/*
	메소드 설명	
	1. 용도 : 학생 1명의 점수를 수정하는 메소드임(Database내 Student_score테이블의 1개 행을 수정하는 메소드).
	2. 매개변수 : studentNo(Student테이블내 studentNo컬럼을 참조받아 Student_score테이블내 사용하는 외래키 값), score(Student_score테이블 컬럼에 담길 값)
	3. 리턴값 : 없음.
	4. StudentScore Class 프로퍼티
		- 접근지정자는 모두 private임. int studentScoreNumber,int studentNumber,int score
	*/		
	public void updateStudentScore(int studentNo, int score) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcDriver = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			connection = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
			System.out.println(connection + "<-- connection");
			preparedStatement = connection.prepareStatement("UPDATE student_score SET score=? WHERE student_no=?");
			preparedStatement.setInt(1, score);
			preparedStatement.setInt(2, studentNo);
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	
}
