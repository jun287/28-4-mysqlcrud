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
	1. 용도 : 학생의 전체의 번호, 이름, 나이, 점수번호, 점수를 조회하는 메소드(Database내 Student테이블,Student_score테이블 inner 조인 결과 조회).
	2. 매개변수 : 없음
	3. 리턴값 : StudentAndScore클래스로 생성된 객체참조변수 참조값을 담은 리스트.
		- StudentAndScore클래스 프로퍼티 : private Student student, private StudentScore studentScore;
	*/
	public ArrayList<StudentAndScore> selectStudentAndScore(int studentNo){
		ArrayList<StudentAndScore> studentAndScoreList = new ArrayList<StudentAndScore>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = new DBconnection().getConnection();
			//DBconnection클래스의 기본 생성자를 호출하여 객체생성 후 getConnection메소드 호출하여 리턴값을 connection에 대입.
			preparedStatement = connection.prepareStatement("select student.student_no, student.student_name, student.student_age, studentscore.student_score_no, studentscore.score from student student inner join student_score studentscore on student.student_no = studentscore.student_no where student.student_no=?");
			preparedStatement.setInt(1, studentNo);
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
			connection = new DBconnection().getConnection();
			//DBconnection클래스의 기본 생성자를 호출하여 객체생성 후 getConnection메소드 호출하여 리턴값을 connection에 대입.
			preparedStatement = connection.prepareStatement("INSERT INTO student_score (student_no, score) VALUES (?, ?)");
			preparedStatement.setInt(1, studentNo);
			preparedStatement.setInt(2, score);
			preparedStatement.executeUpdate();
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
			connection = new DBconnection().getConnection();
			//DBconnection클래스의 기본 생성자를 호출하여 객체생성 후 getConnection메소드 호출하여 리턴값을 connection에 대입.
			preparedStatement = connection.prepareStatement("select count(student_no) as count from student_score where student_no = ?");
			preparedStatement.setInt(1, studentNo);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				count=resultSet.getInt(1);
			}
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
			connection = new DBconnection().getConnection();
			//DBconnection클래스의 기본 생성자를 호출하여 객체생성 후 getConnection메소드 호출하여 리턴값을 connection에 대입.
			preparedStatement = connection.prepareStatement("UPDATE student_score SET score=? WHERE student_no=?");
			preparedStatement.setInt(1, score);
			preparedStatement.setInt(2, studentNo);
			preparedStatement.executeUpdate();
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
	
	/*
	메소드 설명	
	1. 용도 : 학생 전체의 점수 평균을 구하는 메소드임(Database내 Student_score테이블의 score 컬럼의 데이터 값의 평균값을 구하는 메소드).
	2. 매개변수 : 없음.
	3. 리턴값 : Student_score테이블의 score 컬럼의 데이터 값의 평균값
	4. StudentScore Class 프로퍼티
		- 접근지정자는 모두 private임. int studentScoreNumber,int studentNumber,int score
	*/		
	public double selectStudentScoreAverage() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		double studentScoreAverage=0;
		try {
			connection = new DBconnection().getConnection();
			//DBconnection클래스의 기본 생성자를 호출하여 객체생성 후 getConnection메소드 호출하여 리턴값을 connection에 대입.
			preparedStatement = connection.prepareStatement("select avg(score) as averageStudentScore from student_score");
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				studentScoreAverage = resultSet.getDouble("averageStudentScore");
			}
		} catch (SQLException e) {

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
		return studentScoreAverage;
	}
	
	/*
	메소드 설명	
	1. 용도 : 평균점수 이상의 학생의 전체의 번호, 이름, 나이, 점수번호, 점수를 내림차순으로 조회하는 메소드(Database내 Student테이블,Student_score테이블 inner 조인 결과 조회).
	2. 매개변수 : int currentPage(시작페이지), int pagePerRow(페이지당 볼 행의 갯수)
	3. 리턴값 : StudentAndScore클래스로 생성된 객체참조변수 참조값을 담은 리스트.(평균점수 이상의 학생리스트)
	*/
	public ArrayList<StudentAndScore> selectStudentAndScoreAboveAverage(int currentPage, int pagePerRow){
		ArrayList<StudentAndScore> StudentAndScoreAboveAverageList = new ArrayList<StudentAndScore>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = new DBconnection().getConnection();
			//DBconnection클래스의 기본 생성자를 호출하여 객체생성 후 getConnection메소드 호출하여 리턴값을 connection에 대입.
			preparedStatement = connection.prepareStatement("select student.student_no, student.student_name, student.student_age, studentscore.student_score_no, studentscore.score "
					+ "from student student inner join student_score studentscore on student.student_no = studentscore.student_no "
					+ "where score >= (select avg(score) from student_score) order by score desc limit ?,?");
			int startRow = (currentPage-1)*pagePerRow;
			preparedStatement.setInt(1, startRow);
			preparedStatement.setInt(2, pagePerRow);
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
				StudentAndScoreAboveAverageList.add(studentAndScore);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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

		return StudentAndScoreAboveAverageList;
		
	}
	
	/*
	메소드 설명	
	1. 용도 : 평균점수이상의 학생의 총 명수를 조회하는 메소드임
	(Database내 Student과 student_score테이블(Inner 조인)하여 student_score컬럼의 평균값 이상을 데이터를 가진 행의 갯수를 조회하는 메소드).
	2. 매개변수 : pagePerRow = jsp페이지에 출력할 총 db테이블 행의 수
	3. 리턴값 : lastPage = 1번에서 설명한 테이블 조회결과(전체행의 갯수)를 2번 매개변수(페이지당 볼 행으 수)로 나눠 %값이 0이 아닐경우 1증가, 0일경우 나눈 값 사용
	*/		
	public int countStudentAndScoreAboveAverage(int pagePerRow) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int lastPage = 0;
		try {
			connection = new DBconnection().getConnection();
			//DBconnection클래스의 기본 생성자를 호출하여 객체생성 후 getConnection메소드 호출하여 리턴값을 connection에 대입.
			preparedStatement = connection.prepareStatement("select count(student.student_no) as count from student student "
					+ "inner join student_score studentscore on student.student_no = studentscore.student_no "
					+ "where score >= (select avg(score) from student_score)");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int totalRow = resultSet.getInt("count");
			System.out.println(totalRow+"<--totalRow");
			lastPage = totalRow/pagePerRow;
				if(totalRow%pagePerRow!=0){
					lastPage++;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
		return lastPage;
		
	}
	
	/*
	메소드 설명	
	1. 용도 : 학생의 1명의 점수를 삭제하는 메소드임(Database내 Student_Score테이블의 특정 student_no컬럼을 삭제하는 메소드).
	2. 매개변수 : int studentNo(list에서 get방식으로 넘겨받은 값을 int data로 변환 받은 후 대입)
	3. 리턴값 : void
	4. StudentScore Class 프로퍼티
		- 접근지정자는 모두 private임. int studentScoreNumber,int studentNumber,int score
	*/			
	public void deleteStudentScore(int studentNo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = new DBconnection().getConnection();
			//DBconnection클래스의 기본 생성자를 호출하여 객체생성 후 getConnection메소드 호출하여 리턴값을 connection에 대입.
			preparedStatement = connection.prepareStatement("DELETE FROM student_score WHERE student_no=?");
			preparedStatement.setInt(1, studentNo);
			preparedStatement.executeUpdate();
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
}
