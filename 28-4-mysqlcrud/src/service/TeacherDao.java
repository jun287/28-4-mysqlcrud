//2018. 06. 25 28기 공세준 

package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeacherDao {
	
	// 설명 : 드라이버 로딩 , DB연결 , insert 쿼리문 작성 실행해서  teacher 테이블에 교사 데이터 입력하는 메서드 선언 
	// 매개변수 : Teacher 클래스 타입으로 한명의 교사 정보를 담는 객체의 주소값을 담은 변수
	// 리턴값 : void로 없습니다.
	public void insertTeacher(Teacher tdb) {
		
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		// 프로그램 실행중 발생하는 문제적인 상황을 예외 처리 하기 위해 try를 사용합니다.
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String URL = "jdbc:mysql://localhost:3306/284db?useCode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			
			con = DriverManager.getConnection(URL, dbUser, dbPass);
			
			System.out.println("DB연결");
			
			pstmt = con.prepareStatement("INSERT INTO teacher(teacher_name, teacher_age) VALUES (?,?)");
			pstmt.setString(1, tdb.getTeacherName());
			pstmt.setInt(2, tdb.getTeacherAge());
			
			pstmt.executeUpdate();
		
		// Class 클래스 객체에 forName 메서드를 호출하여 드라이버 로딩시 나올수 있는 프로그램 실행중 발생하는 문제적 상황을 예외처리합니다.
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		/* DriverManager클래스객체에 getConnection 메서드를 호출
		Connection 클래스 타입의 con객체참조변수에 대입하고 DB연결 및 Connection클래스 객체의 prepareStatement 메서드에 쿼리문을 대입하고 호출하여
		pstmt(PreparedStatement클래스객체)에 executeUpdate 메서드로 쿼리문 실행시 나올수 있는 프로그램 실행중 발생하는 문제적 상황을 예외처리합니다.
		 */ 
		}catch(SQLException ex){
			ex.printStackTrace();
		// 드라이버로딩, DB연결, 쿼리문 작성 및 실행이 끝나거나 혹은 작동이 안되었을때 종료해주기 위해 finally를 쓰고 if조건문으로 객체참조변수의 값이 null 이 아닐시 close 메서드로 종료시킵니다.
		// 이때도 마찬가지로 예외처리를 해줍니다.
		}finally{
			if(pstmt != null)try{
				pstmt.close(); 
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			if(con != null)try{
				con.close(); 
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		
	}
}
