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
	public int insertTeacher(Teacher tdb) throws ClassNotFoundException, SQLException {
		
		Connection con = null; 
		PreparedStatement pstmt = null;
		
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
		
		return 0;

	}
}
