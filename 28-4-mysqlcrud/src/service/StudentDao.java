// 28�� �̿��� 2018. 6. 26(ȭ) StudentDao.java
package service;
import java.sql.*;			// java.sql��Ű���� Ŭ���� ����Ʈ(Connection,PreparedStatement,SQLException,DriverManager)
public class StudentDao {
	/*
	�޼ҵ� ����	
	1. �뵵 : �л� �Է��ϴ� �޼ҵ���.
	2. �Ű������� Student class data type�̰� �Ű��������� Student class�� ���� ������� ��ü���������� ��������.
	3. ���ϰ� : ����.
	*/		
	public void insertStudent(Student stu) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		// ���ܸ� ������ ����(try)
		try {
			// DB Connection
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcDriver = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			connection = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
			System.out.println(connection + "<-- connection");
			// DB Connection ��
			
			// preparedStatement������ �غ� �� ����
			preparedStatement = connection.prepareStatement("INSERT INTO student (student_name, student_age)	VALUES (?, ?)");
			// ����1. �л� �̸��� ���̸� ����ϴ� ����(no�� auto_increment�� �ڵ��ο���)
			preparedStatement.setString(1, stu.getStudentName());
			preparedStatement.setInt(2, stu.getStudentAge());
			preparedStatement.executeUpdate();
			// preparedStatement������ �غ� �� ���� ��

		// ����ó��	
		}catch (ClassNotFoundException e) {
			// ClassNotFoundException���ܰ� �߻��� �����ų �ڵ�
			e.printStackTrace();
			//printStackTrace �����޼����� �߻� �ٿ��� ã�Ƽ� �ܰ躰 ������ ����Ѵ�.
		} catch (SQLException e) {
			// SQLException���ܰ� �߻��� �����ų �ڵ�
			e.printStackTrace();
			//printStackTrace �����޼����� �߻� �ٿ��� ã�Ƽ� �ܰ躰 ������ ����Ѵ�.
			
		// �������� �ݵ�� ������Ѿ��� �ڵ�	
		} finally {
			if(preparedStatement != null)	// preparedStatement��ü���������� ���� null�� �ƴ� ��� ��ü �ݳ�(�ݳ������� rs->pstmt->conn)
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// SQLException���ܰ� �߻��� �����ų �ڵ�
				e.printStackTrace();
			}
			if(connection != null)			// connection��ü���������� ���� null�� �ƴ� ��� ��ü �ݳ�(�ݳ������� rs->pstmt->conn)
				try {
					connection.close();
				} catch (SQLException e) {
					// SQLException���ܰ� �߻��� �����ų �ڵ�
					e.printStackTrace();
				}
		}
	}
}
