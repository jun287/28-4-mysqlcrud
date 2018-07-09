//2018. 07. 09. 28�� ������

package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherScoreDao {
	
	// ���� : ����̹� �ε� , DB���� , select ������ �ۼ� �����ؼ�  teacher���̺�� teacher_score���̺��� �����͸� �����Ͽ� �����͸� ��ȸ�ϰ� ��ȸ�� �����͸� ArrayList Ŭ����Ÿ������ ��ü���� �迭�� �ּҰ����� ��� ArrayList��ü�� �ּҰ��� �����ϴ� �޼��� ���� 
	// �Ű����� : int �⺻Ÿ������ teacherNo�� �޾Ƽ� select �������� teacher_no�� �����ؼ�  ��ȸ�ϰ��մϴ�.
	// ���ϰ� : ArrayList<TeacherAndScore> Ÿ������ Teacher�� TeacherScore ��ü���� �ּҰ��� ArrayList�� add(TeacherAndSocre)�޼��� ȣ���ؼ� index(��ü�迭)�� �߰� �ǰ� �ּҰ��� �����մϴ�.
	public ArrayList<TeacherAndScore> selectTeacherAndScored(int teacherNo) {
		
		Connection connection = null; 
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		ArrayList<TeacherAndScore> arraylist = new ArrayList<TeacherAndScore>();
		String sql = "SELECT ts.teacher_score_no,ts.teacher_no,t.teacher_name,t.teacher_age,ts.score FROM teacher_score ts INNER JOIN teacher t ON ts.teacher_no=? = t.teacher_no";
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String URL = "jdbc:mysql://localhost:3306/284db?useCode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			
			connection = DriverManager.getConnection(URL, dbUser, dbPass);

			statement = connection.prepareStatement(sql);
			statement.setInt(1, teacherNo);
			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				
				Teacher teacher = new Teacher();
				teacher.setTeacherName(resultSet.getString("t.teacher_name"));
				teacher.setTeacherAge(resultSet.getInt("t.teacher_age"));
				
				TeacherScore teacherScore = new TeacherScore();
				teacherScore.setTeacherNo(resultSet.getInt("ts.teacher_no"));
				teacherScore.setScore(resultSet.getInt("ts.score"));
				
				TeacherAndScore teacherAndScore = new TeacherAndScore();
				teacherAndScore.setTeacher(teacher);
				teacherAndScore.setTeacherScore(teacherScore);
				
				arraylist.add(teacherAndScore);
				
			}
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
			return arraylist;
	}
	
	// ���� : ����̹� �ε� , DB���� , select ������ �ۼ� �����ؼ� ������� ������ "�Է¿Ϸ�" ������ "�Է¿��"�� �����ϴ� �޼��� ���� 
	// �Ű����� : int �⺻Ÿ������ teacherNo�� �޾Ƽ� select �������� teacher_no �÷��� �����ؼ� ��ȸ�ϰ��մϴ�.
	// ���ϰ� : String Ÿ������ "�Է¿Ϸ�" �Ǵ� "�Է¿��"�� �����մϴ�.
	public String selectTeacherScore(int teacherNo) {
		
		Connection connection = null; 
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String result = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String URL = "jdbc:mysql://localhost:3306/284db?useCode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			
			connection = DriverManager.getConnection(URL, dbUser, dbPass);

			statement = connection.prepareStatement("SELECT * FROM teacher_score WHERE teacher_no=?");
			statement.setInt(1, teacherNo);
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()){
				result = "�Է¿Ϸ�";
			}else {
				result = "�Է¿��";
			}
			
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
			return result;
	}
	
	// ���� : ����̹� �ε� , DB���� , insert �������� teacherScore Ŭ������ü�� ���� ������  teacher_score ���̺� ���� �����͸� �Է��ϴ� �޼��� ����
	// �Ű����� : TeacherScore Ŭ���� Ÿ������ teacherScore ��ü �ּҰ��� �޽��ϴ�.
	// ���ϰ� : void �� �����ϴ�.
	public void insertTeacherScore(TeacherScore teacherScore) {
			
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
			System.out.println(teacherScore.getTeacherNo());
			System.out.println(teacherScore.getScore());

			statement = connection.prepareStatement("INSERT INTO teacher_score(teacher_no, score) VALUES (?,?)");
			statement.setInt(1, teacherScore.getTeacherNo());
			statement.setInt(2, teacherScore.getScore());
			
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
