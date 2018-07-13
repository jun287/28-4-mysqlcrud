package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
	
	public Connection getConnection() {
		
		String jdbcDriver = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
		String dbUser = "java";
		String dbPass = "java0000";
		
		Connection conn = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcDriver,dbUser,dbPass);
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return conn;
	}
}
