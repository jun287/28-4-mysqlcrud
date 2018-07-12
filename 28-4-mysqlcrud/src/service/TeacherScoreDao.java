//2018. 07. 10. 28�� ������

package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherScoreDao {
	
	// ���� : ����̹� �ε� , DB���� , delete ������ �ۼ� �����ؼ� teacher_score ���̺� ������ �����ϴ� �޼��� ����
	// �Ű����� : int �⺻Ÿ������ teacherNo �Ű������� �����ϰ� ��ϵ� ��ȣ�� �޽��ϴ�.
	// ���ϰ� : void�� �����ϴ�.
	public void deleteTeacherScore(int teacherNo) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			
			DBconnection dbConnection = new DBconnection();
			connection = dbConnection.getConnection();
			
			statement = connection.prepareStatement("DELETE FROM teacher_score WHERE teacher_no=?");
			statement.setInt(1, teacherNo);
			statement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
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
	
	// ���� : �����Լ�(Avg) ����Ͽ� teacher_score ���̺� score ��հ��� ���ϴ� �޼��� ����
	// �Ű����� : �Ű������� �����ϴ�.
	// ���� : ��հ��� int �⺻Ÿ������ �����մϴ�.
	public int selectScoreAvg() {
		
		// SELECT AVG(score) FROM teahcer_score;
		Connection connection = null; 
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int scoreAvg = 0;
		
		try {
			
			DBconnection dbConnection = new DBconnection();
			connection = dbConnection.getConnection();
			
			statement = connection.prepareStatement("SELECT AVG(score) FROM teacher_score");
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				scoreAvg = resultSet.getInt("AVG(score)");
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
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
			
		return scoreAvg;
	}
	
	
	// ����: join + subquery ����Ͽ� score ������ ��պ��� ���� ����� ���ϴ� �޼��� ����
	// �Ű�����: �Ű������� �����ϴ�.
	// ���ϰ�:	 ArrayList<TeacherAndScore> Ŭ���� Ÿ������ arrayList(teacher�� teacherScore Ŭ������ü�� ���� ��ȸ�� ������� ��� �ּҰ��� ��� teacherAndScore Ŭ������ü�� �ּҰ�)�ּҰ��� �����մϴ�.
	public ArrayList<TeacherAndScore> selectTeacherListAboveAvg(){
		
		Connection connection = null; 
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		ArrayList<TeacherAndScore> arrayList = new ArrayList<TeacherAndScore>();
		String sql = "SELECT ts.score, t.teacher_name, t.teacher_no FROM teacher_score ts INNER JOIN teacher t ON ts.teacher_no = t.teacher_no WHERE ts.score>=(select avg(score) from teacher_score) Order by ts.score DESC";
		
		try {
			
			DBconnection dbConnection = new DBconnection();
			connection = dbConnection.getConnection();
			
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Teacher teacher = new Teacher();
				teacher.setTeacherName(resultSet.getString("t.teacher_name"));
				teacher.setTeacherNo(resultSet.getInt("t.teacher_no"));
				
				TeacherScore teacherScore = new TeacherScore();
				teacherScore.setScore(resultSet.getInt("ts.score"));
				
				TeacherAndScore teacherAndScore = new TeacherAndScore();
				teacherAndScore.setTeacher(teacher);
				teacherAndScore.setTeacherScore(teacherScore);
				
				arrayList.add(teacherAndScore);
				
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
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
		return arrayList;
	}
	
	// ���� : ����̹� �ε� , DB���� , select ������ �ۼ� �����ؼ�  teacher���̺�� teacher_score���̺��� �����͸� �����Ͽ� �����͸� ��ȸ�ϰ� ��ȸ�� �����͸�  TeacherAndScore Ŭ����Ÿ������ Teacher�� Teacher_score��ü���� �ּҰ����� ��� ��ü�� �ּҰ��� �����ϴ� �޼��� ���� 
	// �Ű����� : int �⺻Ÿ������ teacherNo�� �޾Ƽ� select �������� teacher_no�� �����ؼ�  ��ȸ�ϰ��մϴ�.
	// ���ϰ� : TeacherAndScore Ŭ���� Ÿ������ Teacher�� TeacherScore ��ü���� �ּҰ��� ��� �ּҰ��� �����մϴ�.
	public TeacherAndScore selectTeacherAndScored(int teacherNo) {
		
		Connection connection = null; 
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		TeacherAndScore teacherAndScore = new TeacherAndScore();
		Teacher teacher = new Teacher();
		TeacherScore teacherScore = new TeacherScore();
		
		String sql = "SELECT ts.teacher_score_no,ts.teacher_no,t.teacher_name,t.teacher_age,ts.score FROM teacher_score ts INNER JOIN teacher t ON t.teacher_no = ts.teacher_no WHERE t.teacher_no=?";
		
		try {
			
			DBconnection dbConnection = new DBconnection();
			connection = dbConnection.getConnection();

			statement = connection.prepareStatement(sql);
			statement.setInt(1, teacherNo);
			
			resultSet = statement.executeQuery();
			
			
			if(resultSet.next()){
				
				teacher.setTeacherName(resultSet.getString("t.teacher_name"));
				teacher.setTeacherAge(resultSet.getInt("t.teacher_age"));
				teacherScore.setTeacherNo(resultSet.getInt("ts.teacher_no"));
				teacherScore.setScore(resultSet.getInt("ts.score"));
				
				
				teacherAndScore.setTeacher(teacher);
				teacherAndScore.setTeacherScore(teacherScore);
				
				
			}else {
				
				teacherAndScore.setTeacher(teacher);
				teacherAndScore.setTeacherScore(teacherScore);
				
			}
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
			return teacherAndScore;
	}
	// ���� : ����̹� �ε� , DB���� , insert �������� teacherScore Ŭ������ü�� ���� ������  teacher_score ���̺� ���� �����͸� �Է��ϴ� �޼��� ����
	// �Ű����� : TeacherScore Ŭ���� Ÿ������ teacherScore ��ü �ּҰ��� �޽��ϴ�.
	// ���ϰ� : void �� �����ϴ�.
	public void insertTeacherScore(TeacherScore teacherScore) {
			
		Connection connection = null; 
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		PreparedStatement statement3 = null;
		ResultSet resultSet = null;

		// ���α׷� ������ �߻��ϴ� �������� ��Ȳ�� ���� ó�� �ϱ� ���� try�� ����մϴ�.
		try {
			
			DBconnection dbConnection = new DBconnection();
			connection = dbConnection.getConnection();
			
			System.out.println(teacherScore.getTeacherNo());
			System.out.println(teacherScore.getScore());
			
			statement = connection.prepareStatement("SELECT * FROM teacher_score WHERE teacher_no=?");
			statement.setInt(1, teacherScore.getTeacherNo());
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				
				statement2 = connection.prepareStatement("UPDATE teacher_score SET score=? WHERE teacher_no=?");
				statement2.setInt(1, teacherScore.getScore());
				statement2.setInt(2, teacherScore.getTeacherNo());
				
				statement2.executeUpdate();
				
			}else {
			
				statement3 = connection.prepareStatement("INSERT INTO teacher_score(teacher_no, score) VALUES (?,?)");
				statement3.setInt(1, teacherScore.getTeacherNo());
				statement3.setInt(2, teacherScore.getScore());
				
				statement3.executeUpdate();
			}
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
