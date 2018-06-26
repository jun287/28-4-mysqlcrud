// 28기 이원상 2018. 6. 26(화) StudentDao.java
package service;
import java.sql.*;			// java.sql패키지내 클래스 임포트(Connection,PreparedStatement,SQLException,DriverManager)
public class StudentDao {
	/*
	메소드 설명	
	1. 용도 : 학생 입력하는 메소드임.
	2. 매개변수는 Student class data type이고 매개변수명은 Student class를 통해 만들어진 객체참조변수의 참조값임.
	3. 리턴값 : 없음.
	*/		
	public void insertStudent(Student stu) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		// 예외를 조사할 문장(try)
		try {
			// DB Connection
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcDriver = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			connection = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
			System.out.println(connection + "<-- connection");
			// DB Connection 끝
			
			// preparedStatement쿼리문 준비 및 실행
			preparedStatement = connection.prepareStatement("INSERT INTO student (student_name, student_age)	VALUES (?, ?)");
			// 쿼리1. 학생 이름과 나이를 등록하는 쿼리(no는 auto_increment로 자동부여됨)
			preparedStatement.setString(1, stu.getStudentName());
			preparedStatement.setInt(2, stu.getStudentAge());
			preparedStatement.executeUpdate();
			// preparedStatement쿼리문 준비 및 실행 끝

		// 예외처리	
		}catch (ClassNotFoundException e) {
			// ClassNotFoundException예외가 발생시 실행시킬 코드
			e.printStackTrace();
			//printStackTrace 에러메세지의 발생 근원츨 찾아서 단계별 에러를 출력한다.
		} catch (SQLException e) {
			// SQLException예외가 발생시 실행시킬 코드
			e.printStackTrace();
			//printStackTrace 에러메세지의 발생 근원츨 찾아서 단계별 에러를 출력한다.
			
		// 마지막에 반드시 실행시켜야할 코드	
		} finally {
			if(preparedStatement != null)	// preparedStatement객체참조변수의 값이 null이 아닐 경우 객체 반납(반납순서는 rs->pstmt->conn)
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// SQLException예외가 발생시 실행시킬 코드
				e.printStackTrace();
			}
			if(connection != null)			// connection객체참조변수의 값이 null이 아닐 경우 객체 반납(반납순서는 rs->pstmt->conn)
				try {
					connection.close();
				} catch (SQLException e) {
					// SQLException예외가 발생시 실행시킬 코드
					e.printStackTrace();
				}
		}
	}
}
