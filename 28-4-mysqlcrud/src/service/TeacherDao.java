package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDao {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public Connection getCon() throws SQLException, ClassNotFoundException {
		
		
		
		Class.forName("com.mysql.jdbc.Driver");
		 
		String URL = "jdbc:mysql://localhost:3306/284db?useCode=true&characterEncoding=euckr";
		String dbUser = "java";
		String dbPass = "java0000";
		
		con = DriverManager.getConnection(URL, dbUser, dbPass);
		
		System.out.println("DB¿¬°á");
		return con;
		
	}
	
	public int insertTeacher(Teacher t) throws SQLException {
		
		pstmt = con.prepareStatement("INSERT INTO teacher(teacher_name, teacher_age) VALUES (?,?)");
		pstmt.setString(1, t.getTeacherName());
		pstmt.setInt(2, t.getTeacherAge());
		
		pstmt.executeUpdate();

		return 0;
		
	}
	
	
}
