//2018. 07. 02 28�� ������ 

package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/* 
 * JDBC API SELECT Resultet -> ArrayList<Teacher>
 * list : ������ ����, list.size(), remove(4), 
 * �迭	: ������ ����, �迭.length, �迭[4],
 */

public class TeacherDao {
	
	//���� : ������ �������� ���ϱ� ���� �޼��带 �����մϴ�.
	//�Ű����� : �Ű������� �����ϴ�.
	//���ϰ� : ���ϰ��� totalRow�� teacher ���̺� teacher_no�� ������ �����մϴ�.
	public int count() {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		int totalRow=0;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String URL = "jdbc:mysql://localhost:3306/284db?useCode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			String sql = "SELECT COUNT(teacher_no) FROM teacher";
			
			connection = DriverManager.getConnection(URL, dbUser, dbPass);
			
			
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				totalRow=resultSet.getInt("COUNT(teacher_no)");
			}
			
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			
		}
		return totalRow;
	} 
	
	// ���� : ����̹� �ε� , DB���� , select ������ �ۼ� �����ؼ�  teacher ���̺� ���� �����͸� ��ȸ�ϰ� ��ȸ�� �����͸� ArrayList Ŭ����Ÿ������ ��ü���� �迭�� �ּҰ����� ��� ArrayList��ü�� �ּҰ��� �����ϴ� �޼��� ���� 
	// �Ű����� : int �⺻Ÿ������ currentPage,�� pagePerRow�� �޾Ƽ� select �������� limit�� �Ἥ ��ȸ�ϰ��մϴ�.
	// ���ϰ� : ArrayList<Teacher> Ÿ������ Teacher ��ü���� �ּҰ��� ArrayList�� add(Teacher)�޼��� ȣ���ؼ� index(��ü�迭)�� �߰� �ǰ� �ּҰ��� �����մϴ�.
	public ArrayList<Teacher> selectTeacherByPage(int currentPage, int pagePerRow){
		
		ArrayList<Teacher> teacherlist = new ArrayList<Teacher>(); // ��ü���� �ּҰ����� �ޱ����� ArrayList Ŭ������ import �ϰ� ��ü�� �����մϴ�.
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		String sql = "SELECT * FROM teacher ORDER BY teacher_no LIMIT ?,?";
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String URL = "jdbc:mysql://localhost:3306/284db?useCode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			
			connection = DriverManager.getConnection(URL, dbUser, dbPass);
			
			int startRow = (currentPage-1)*pagePerRow; //currentPage, pagePerRow �Ű������� �޾Ƽ� �����ϴ� �����Ϳ��� ���ϰ� starRow ������ �����մϴ�.
			statement = connection.prepareStatement(sql);
			statement.setInt(1, startRow);
			statement.setInt(2, pagePerRow);
			
			resultSet = statement.executeQuery();
			// �ݺ����� ����� �����Ͱ����� Teacher Ŭ���� ��ü�� ������ ��ü���� �޼���(setTeacherNo)�� �̿��� ���������� �����͵��� �����մϴ�.
			while(resultSet.next()){ 
				
				Teacher teacher = new Teacher();
		
				teacher.setTeacherNo(resultSet.getInt("teacher_no"));
				teacher.setTeacherName(resultSet.getString("teacher_name"));
				teacher.setTeacherAge(resultSet.getInt("teacher_age"));
				
				teacherlist.add(teacher); // ��ü���� �ּҰ��� add�޼���� ArrayList Ŭ���� ��ü�� �����մϴ�. 
			}
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();	
		}finally {
			
		}
	
		return teacherlist; // ArrayList Ŭ���� ��ü�� �ּҰ��� �����մϴ�.

	}
	
	/*public Teacher[] selectTeacherAll() {
		return null;
	}*/
	
	// ���� : ����̹� �ε� , DB���� , insert ������ �ۼ� �����ؼ�  teacher ���̺� ���� ������ �Է��ϴ� �޼��� ���� 
	// �Ű����� : Teacher Ŭ���� Ÿ������ �Ѹ��� ���� ������ ��� ��ü�� �ּҰ��� ���� ����
	// ���ϰ� : void�� �����ϴ�.
	public void insertTeacher(Teacher teacher) {
		
		Connection connection = null; 
		PreparedStatement statement = null;
		
		// ���α׷� ������ �߻��ϴ� �������� ��Ȳ�� ���� ó�� �ϱ� ���� try�� ����մϴ�.
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String URL = "jdbc:mysql://localhost:3306/284db?useCode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			
			connection = DriverManager.getConnection(URL, dbUser, dbPass);
			
			System.out.println("DB����");
			
			statement = connection.prepareStatement("INSERT INTO teacher(teacher_name, teacher_age) VALUES (?,?)");
			statement.setString(1, teacher.getTeacherName());
			statement.setInt(2, teacher.getTeacherAge());
			
			statement.executeUpdate();
		
		// Class Ŭ���� ��ü�� forName �޼��带 ȣ���Ͽ� ����̹� �ε��� ���ü� �ִ� ���α׷� ������ �߻��ϴ� ������ ��Ȳ�� ����ó���մϴ�.
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		/* DriverManagerŬ������ü�� getConnection �޼��带 ȣ��
		Connection Ŭ���� Ÿ���� connection��ü���������� �����ϰ� DB���� �� ConnectionŬ���� ��ü�� prepareStatement �޼��忡 �������� �����ϰ� ȣ���Ͽ�
		statement(PreparedStatementŬ������ü)�� executeUpdate �޼���� ������ ����� ���ü� �ִ� ���α׷� ������ �߻��ϴ� ������ ��Ȳ�� ����ó���մϴ�.
		 */ 
		}catch(SQLException ex){
			ex.printStackTrace();
		// ����̹��ε�, DB����, ������ �ۼ� �� ������ �����ų� Ȥ�� �۵��� �ȵǾ����� �������ֱ� ���� finally�� ���� if���ǹ����� ��ü���������� ���� null �� �ƴҽ� close �޼���� �����ŵ�ϴ�.
		// �̶��� ���������� ����ó���� ���ݴϴ�.
		}finally{
			if(statement != null)try{
				statement.close(); 
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			if(connection != null)try{
				connection.close(); 
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		
	}
}
