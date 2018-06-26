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
	public void insertTeacher(Teacher tdb) {
		
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		// ���α׷� ������ �߻��ϴ� �������� ��Ȳ�� ���� ó�� �ϱ� ���� try�� ����մϴ�.
		try {
			
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
		
		// Class Ŭ���� ��ü�� forName �޼��带 ȣ���Ͽ� ����̹� �ε��� ���ü� �ִ� ���α׷� ������ �߻��ϴ� ������ ��Ȳ�� ����ó���մϴ�.
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		/* DriverManagerŬ������ü�� getConnection �޼��带 ȣ��
		Connection Ŭ���� Ÿ���� con��ü���������� �����ϰ� DB���� �� ConnectionŬ���� ��ü�� prepareStatement �޼��忡 �������� �����ϰ� ȣ���Ͽ�
		pstmt(PreparedStatementŬ������ü)�� executeUpdate �޼���� ������ ����� ���ü� �ִ� ���α׷� ������ �߻��ϴ� ������ ��Ȳ�� ����ó���մϴ�.
		 */ 
		}catch(SQLException ex){
			ex.printStackTrace();
		// ����̹��ε�, DB����, ������ �ۼ� �� ������ �����ų� Ȥ�� �۵��� �ȵǾ����� �������ֱ� ���� finally�� ���� if���ǹ����� ��ü���������� ���� null �� �ƴҽ� close �޼���� �����ŵ�ϴ�.
		// �̶��� ���������� ����ó���� ���ݴϴ�.
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
