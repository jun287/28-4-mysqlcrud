// 28기 이원상 StudentAddrDao.java
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentAddrDao {
	/*
	메소드 설명	
	1. 용도 : 학생의 1명의 주소를 입력하는 메소드임(Database내 Student_addr테이블 중 1개행을 입력하는 메소드).
	2. 매개변수 : StudentAddr studentAddr(StudentAddr클래스를 통해 생성된 studentAddr객체의 참조값)
	3. 리턴값 : void
	4. StudentAddr Class 프로퍼티
		- 접근지정자는 모두 private임. int studentNO, int studentNO, String studentAddrContent;
	*/	
	public void insertStudentAddress(StudentAddr studentAddr) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		// 예외를 조사할 문장(try)
		try {
			connection = new DBconnection().getConnection();
			//DBconnection클래스의 기본 생성자를 호출하여 객체생성 후 getConnection메소드 호출하여 리턴값을 connection에 대입.
			preparedStatement = connection.prepareStatement("INSERT INTO student_addr (student_no,student_addr_content)	VALUES (?, ?)");
			preparedStatement.setInt(1, studentAddr.getStudentNO());
			preparedStatement.setString(2, studentAddr.getStudentAddrContent());
			preparedStatement.executeUpdate();
			
		// 예외처리	
		}catch (SQLException e) {
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
	
	/*
	메소드 설명	
	1. 용도 : 학생의 1명의 주소들을 조회하는 메소드임(Database내 Student_addr테이블의 특정 student_no컬럼을 조회하는 메소드).
	2. 매개변수 : int studentNo(list에서 get방식으로 넘겨받은 값을 int data로 변환 받은 후 대입)
	3. 리턴값 : studentAddrList(StudentAddr클래스를 통해 생성된 객체의 참조값을 담은 리스트를 리턴)
	4. StudentAddr Class 프로퍼티
		- 접근지정자는 모두 private임. int studentNO, int studentNO, String studentAddrContent;
	*/	
	public ArrayList<StudentAddr> selectStudentAddrList(int studentNo){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<StudentAddr> studentAddrList = new ArrayList<>();
		try {
			connection = new DBconnection().getConnection();
			//DBconnection클래스의 기본 생성자를 호출하여 객체생성 후 getConnection메소드 호출하여 리턴값을 connection에 대입.
			preparedStatement = connection.prepareStatement("select student_addr_no, student_no, student_addr_content from student_addr where student_no=?");
			preparedStatement.setInt(1, studentNo);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				StudentAddr studentAddr = new StudentAddr();
				studentAddr.setStudentAddrNo(resultSet.getInt("student_addr_no"));
				studentAddr.setStudentNO(resultSet.getInt("student_no"));
				studentAddr.setStudentAddrContent(resultSet.getString("student_addr_content"));
				studentAddrList.add(studentAddr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentAddrList;
	}
	
	/*
	메소드 설명	
	1. 용도 : 학생의 1명의 주소들을 삭제하는 메소드임(Database내 Student_addr테이블의 특정 student_no컬럼을 삭제하는 메소드).
	2. 매개변수 : int studentNo(list에서 get방식으로 넘겨받은 값을 int data로 변환 받은 후 대입)
	3. 리턴값 : void
	4. StudentAddr Class 프로퍼티
		- 접근지정자는 모두 private임. int studentNO, int studentNO, String studentAddrContent;
	*/	
	public void deleteStudentAddr(int studentNo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = new DBconnection().getConnection();
			//DBconnection클래스의 기본 생성자를 호출하여 객체생성 후 getConnection메소드 호출하여 리턴값을 connection에 대입.
			preparedStatement = connection.prepareStatement("delete from student_addr where student_no=?");
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
