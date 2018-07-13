package TeacherDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBConnection.DBconnection;
import TeacherDTO.TeacherAddr;

public class TeacherAddrDao {
	
	// ���� : ����̹� �ε� , DB���� , select ������ �ۼ� �����ؼ� teacherAddr ���̺� �����͸� TeacherAddr ��ü�� �޾Ƽ� ��ü�� �ּҰ��� �����ϴ� �޼��� ����
	// �Ű����� : int teacherNo �Ű������� ��ȣ�� �޽��ϴ�.
	// ���ϰ� : TeacherAddr Ŭ����Ÿ������ ��ü�� �ּҰ��� �����մϴ�.
	public TeacherAddr selectTeacherAddr(int teacherNo) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		TeacherAddr teacherAddr = new TeacherAddr();
		
		try {
			
			DBconnection dbConnection = new DBconnection();
			connection = dbConnection.getConnection();
			
			statement = connection.prepareStatement("SELECT * FROM teacheraddr WHERE teacher_no=?");
			statement.setInt(1, teacherNo);
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				teacherAddr.setTeacherNo(resultSet.getInt("teacher_no"));
				teacherAddr.setTeacherAddrContent(resultSet.getString("teacher_addr_content"));
			}
			
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
		return teacherAddr;
		
	}
	
	// ���� : ����̹� �ε� , DB���� , update ������ �ۼ� �����ؼ� teacherAddr ���̺� �ּҰ��� �����ϴ� �޼��� ����
	// �Ű����� : TeacherAddr Ŭ����Ÿ������ teacherAddr �Ű������� �����ϰ� �ּҿ� ��ȣ�� ��� ��ü�� �ּҰ��� �޽��ϴ�.
	// ���ϰ� : void�� �����ϴ�.
	public void updateTeacherAddr(TeacherAddr teacherAddr) {
			
		Connection connection = null;
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		PreparedStatement statement3 = null;
		ResultSet resultSet = null;
		
		try {
			
			DBconnection dbConnection = new DBconnection();
			connection = dbConnection.getConnection();
			
			statement= connection.prepareStatement("SELECT * FROM teacherAddr WHERE teacher_no=?");
			statement.setInt(1, teacherAddr.getTeacherNo());
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				
				statement2 = connection.prepareStatement("UPDATE teacherAddr SET teacher_addr_content=? WHERE teacher_no=?");
				statement2.setString(1, teacherAddr.getTeacherAddrContent());
				statement2.setInt(2, teacherAddr.getTeacherNo());
				
				statement2.executeUpdate();
				
			}else {
				
				statement3 = connection.prepareStatement("INSERT INTO teacheraddr(teacher_no, teacher_addr_content) VALUES (?,?)");
				statement3.setInt(1, teacherAddr.getTeacherNo());
				statement3.setString(2, teacherAddr.getTeacherAddrContent());
				
				statement3.executeUpdate();
			}
			
			

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
	
	
	// ���� : ����̹� �ε� , DB���� , select ������ �ۼ� �����ؼ� teacher ���̺� �����͸� ��ȸ�ϰ� �ּҰ��� ���� ��ü�� �ּҰ� �����ϴ� �޼��� ����
	// �Ű����� : int �⺻Ÿ������ teacherNo �Ű������� �����ϰ� ��ϵ� ��ȣ�� �޽��ϴ�.
	// ���ϰ� : TeacherAddr Ŭ���� Ÿ������ ��ü�� �ּҰ��� �����մϴ�.
	public TeacherAddr updateSelectTeacherAddr(int teacherNo) {
			
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		TeacherAddr teacherAddr = new TeacherAddr();
		
		try {
			
			DBconnection dbConnection = new DBconnection();
			connection = dbConnection.getConnection();
			
			statement = connection.prepareStatement("SELECT * FROM teacheraddr WHERE teacher_no=?");
			statement.setInt(1, teacherNo);
			
			resultSet = statement.executeQuery();

			if(resultSet.next()) {
				
				teacherAddr.setTeacherAddrContent(resultSet.getString("teacher_addr_content"));
			}
			
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
		
		return teacherAddr;
	}
	
	// ���� : ����̹� �ε� , DB���� , delete ������ �ۼ� �����ؼ� teacherAddr ���̺� ������ �����ϴ� �޼��� ����
	// �Ű����� : int �⺻Ÿ������ teacherNo �Ű������� �����ϰ� ��ϵ� ��ȣ�� �޽��ϴ�.
	// ���ϰ� : void�� �����ϴ�.
	public void deleteTeacherAddr(int teacherNo) {
			
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			
			DBconnection dbConnection = new DBconnection();
			connection = dbConnection.getConnection();
			
			statement = connection.prepareStatement("DELETE FROM teacheraddr WHERE teacher_no=?");
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

	// ���� : ����̹� �ε� , DB���� , insert ������ �ۼ� �����ؼ�  teacherAddr ���̺� �ּ� ������ �Է��ϴ� �޼��� ���� 
	// �Ű����� : TeacherAddr Ŭ����Ÿ������ teacherAddr ��ü�� �ּҰ��� �޽��ϴ�.
	// ���ϰ� : void�� �����ϴ�.
	public void insertTeacherAddr(TeacherAddr teacherAddr) {
		
		Connection connection = null; 
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		PreparedStatement statement3 = null;
		ResultSet resultSet = null;

		// ���α׷� ������ �߻��ϴ� �������� ��Ȳ�� ���� ó�� �ϱ� ���� try�� ����մϴ�.
		try {
			
			DBconnection dbConnection = new DBconnection();
			connection = dbConnection.getConnection();
			
			System.out.println("DB����");
			System.out.println(teacherAddr.getTeacherNo());
			System.out.println(teacherAddr.getTeacherAddrContent());
			
			statement = connection.prepareStatement("SELECT teacher_no, teacher_addr_content FROM teacheraddr WHERE teacher_no=?");
			statement.setInt(1, teacherAddr.getTeacherNo());
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				
				statement2 = connection.prepareStatement("UPDATE teacherAddr SET teacher_addr_content=? WHERE teacher_no=?");
				statement2.setString(1, teacherAddr.getTeacherAddrContent());
				statement2.setInt(2, teacherAddr.getTeacherNo());
				
				statement2.executeUpdate();
				
			}else {
				
				statement3 = connection.prepareStatement("INSERT INTO teacheraddr(teacher_no, teacher_addr_content) VALUES (?,?)");
				statement3.setInt(1, teacherAddr.getTeacherNo());
				statement3.setString(2, teacherAddr.getTeacherAddrContent());
				
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
