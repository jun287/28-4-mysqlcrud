//2018. 06. 25 28�� ������ 

package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeacherDao {
	
	// ���� : ����̹� �ε� , DB���� , insert ������ �ۼ� �����ؼ�  teacher ���̺� ���� ������ �Է��ϴ� �޼��� ���� 
	// �Ű����� : Teacher Ŭ���� Ÿ������ �Ѹ��� ���� ������ ��� ��ü�� �ּҰ��� ���� ����
	// ���ϰ� : void�� �����ϴ�.
	public int insertTeacher(Teacher tdb) throws ClassNotFoundException, SQLException {
		
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		Class.forName("com.mysql.jdbc.Driver");
		 
		String URL = "jdbc:mysql://localhost:3306/284db?useCode=true&characterEncoding=euckr";
		String dbUser = "java";
		String dbPass = "java0000";
		
		con = DriverManager.getConnection(URL, dbUser, dbPass);
		
		System.out.println("DB����");
		
		pstmt = con.prepareStatement("INSERT INTO teacher(teacher_name, teacher_age) VALUES (?,?)");
		pstmt.setString(1, tdb.getTeacherName());
		pstmt.setInt(2, tdb.getTeacherAge());
		
		pstmt.executeUpdate();
		
		return 0;

	}
}
