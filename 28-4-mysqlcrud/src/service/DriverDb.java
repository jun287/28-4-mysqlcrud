// 28기 이원상 2018. 6. 25(금)
package service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverDb {
	public Connection driverDbcon() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");
		
		String jdbcDriver = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
		String dbUser = "java";
		String dbPass = "java0000";
		
		Connection reconn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		System.out.println(reconn + "<-- reconn");
		
		return reconn;
		
	}
}
