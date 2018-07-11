// 2018. 07. 09 28�� ������

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
	
	// ���� : ����̹� �ε� , DB���� , select ������ �ۼ� �����ؼ� teacherAddr ���̺� �����͸� TeacherAddr ��ü�� �޾Ƽ� ��ü�� �ּҰ��� �����ϴ� �޼��� ����
	// �Ű����� : int teacherNo �Ű������� ��ȣ�� �޽��ϴ�.
	// ���ϰ� : TeacherAddr Ŭ����Ÿ������ ��ü�� �ּҰ��� �����մϴ�.
	public TeacherAddr selectTeacherAddr(int teacherNo) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		TeacherAddr teacherAddr = new TeacherAddr();
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String URL = "jdbc:mysql://localhost:3306/284db?useCode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			
			connection = DriverManager.getConnection(URL, dbUser, dbPass);
			
			statement = connection.prepareStatement("SELECT * FROM teacheraddr WHERE teacher_no=?");
			statement.setInt(1, teacherNo);
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				teacherAddr.setTeacherNo(resultSet.getInt("teacher_no"));
				teacherAddr.setTeacherAddrContent(resultSet.getString("teacher_addr_content"));
			}
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
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
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String URL = "jdbc:mysql://localhost:3306/284db?useCode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			
			connection = DriverManager.getConnection(URL, dbUser, dbPass);
			
			statement = connection.prepareStatement("UPDATE teacherAddr SET teacher_addr_content=? WHERE teacher_no=?");
			statement.setString(1, teacherAddr.getTeacherAddrContent());
			statement.setInt(2, teacherAddr.getTeacherNo());
			
			statement.executeUpdate();

			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
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
	
	// ���� : ����̹� �ε� , DB���� , update ������ �ۼ� �����ؼ� teacher ���̺� �ּҰ��� �����ϴ� �޼��� ����
	// �Ű����� : Teacher Ŭ���� Ÿ������ teacher �Ű������� �����ϰ� �����̸��� ���̰� ��� ��ü�� �ּҰ��� �޽��ϴ�.
	// ���ϰ� : void�� �����ϴ�.
	public void updateTeacher(Teacher teacher) {
			
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String URL = "jdbc:mysql://localhost:3306/284db?useCode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			
			connection = DriverManager.getConnection(URL, dbUser, dbPass);
			
			statement = connection.prepareStatement("UPDATE teacher SET teacher_name=?, teacher_age=? WHERE teacher_no=?");
			statement.setString(1, teacher.getTeacherName());
			statement.setInt(2, teacher.getTeacherAge());
			statement.setInt(3, teacher.getTeacherNo());
			
			statement.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
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
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String URL = "jdbc:mysql://localhost:3306/284db?useCode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			
			connection = DriverManager.getConnection(URL, dbUser, dbPass);
			
			statement = connection.prepareStatement("SELECT * FROM teacheraddr WHERE teacher_no=?");
			statement.setInt(1, teacherNo);
			
			resultSet = statement.executeQuery();

			if(resultSet.next()) {
				
				teacherAddr.setTeacherAddrContent(resultSet.getString("teacher_addr_content"));
			}
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
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


	// ���� : ����̹� �ε� , DB���� , select ������ �ۼ� �����ؼ� teacher ���̺� �����͸� ��ȸ�ϰ� ���������� ���� ��ü�� �ּҰ��� �����ϴ� �޼��� ����
	// �Ű����� : int �⺻Ÿ������ teacherNo �Ű������� �����ϰ� ��ϵ� ��ȣ�� �޽��ϴ�.
	// ���ϰ� : Teacher Ŭ���� Ÿ������ ��ü�� �ּҰ��� �����մϴ�.
	public Teacher updateSelectTeacher(int teacherNo) {
			
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Teacher teacher = new Teacher();
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String URL = "jdbc:mysql://localhost:3306/284db?useCode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			
			connection = DriverManager.getConnection(URL, dbUser, dbPass);
			
			statement = connection.prepareStatement("SELECT * FROM teacher WHERE teacher_no=?");
			statement.setInt(1, teacherNo);
			
			resultSet = statement.executeQuery();

			if(resultSet.next()) {
				
				teacher.setTeacherNo(resultSet.getInt("teacher_no"));
				teacher.setTeacherName(resultSet.getString("teacher_name"));
				teacher.setTeacherAge(resultSet.getInt("teacher_age"));
			}
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
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
		
		return teacher;
	}
	
	
	// ���� : ����̹� �ε� , DB���� , delete ������ �ۼ� �����ؼ� teacher_score ���̺� ������ �����ϴ� �޼��� ����
	// �Ű����� : int �⺻Ÿ������ teacherNo �Ű������� �����ϰ� ��ϵ� ��ȣ�� �޽��ϴ�.
	// ���ϰ� : void�� �����ϴ�.
	public void deleteTeacherScore(int teacherNo) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String URL = "jdbc:mysql://localhost:3306/284db?useCode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			
			connection = DriverManager.getConnection(URL, dbUser, dbPass);
			
			statement = connection.prepareStatement("DELETE FROM teacher_score WHERE teacher_no=?");
			statement.setInt(1, teacherNo);
			statement.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
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
	// ���� : ����̹� �ε� , DB���� , delete ������ �ۼ� �����ؼ� teacher ���̺� ������ �����ϴ� �޼��� ����
	// �Ű����� : int �⺻Ÿ������ teacherNo �Ű������� �����ϰ� ��ϵ� ��ȣ�� �޽��ϴ�.
	// ���ϰ� : void�� �����ϴ�.
	public void deleteTeacher(int teacherNo) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String URL = "jdbc:mysql://localhost:3306/284db?useCode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			
			connection = DriverManager.getConnection(URL, dbUser, dbPass);
			
			statement = connection.prepareStatement("DELETE FROM teacher WHERE teacher_no=?");
			statement.setInt(1, teacherNo);
			statement.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
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
	
	// ���� : ����̹� �ε� , DB���� , delete ������ �ۼ� �����ؼ� teacherAddr ���̺� ������ �����ϴ� �޼��� ����
	// �Ű����� : int �⺻Ÿ������ teacherNo �Ű������� �����ϰ� ��ϵ� ��ȣ�� �޽��ϴ�.
	// ���ϰ� : void�� �����ϴ�.
	public void deleteTeacherAddr(int teacherNo) {
			
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String URL = "jdbc:mysql://localhost:3306/284db?useCode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			
			connection = DriverManager.getConnection(URL, dbUser, dbPass);
			
			statement = connection.prepareStatement("DELETE FROM teacheraddr WHERE teacher_no=?");
			statement.setInt(1, teacherNo);
			statement.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
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

		// ���α׷� ������ �߻��ϴ� �������� ��Ȳ�� ���� ó�� �ϱ� ���� try�� ����մϴ�.
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String URL = "jdbc:mysql://localhost:3306/284db?useCode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			
			connection = DriverManager.getConnection(URL, dbUser, dbPass);
			
			System.out.println("DB����");
			System.out.println(teacherAddr.getTeacherNo());
			System.out.println(teacherAddr.getTeacherAddrContent());

			statement = connection.prepareStatement("INSERT INTO teacheraddr(teacher_no, teacher_addr_content) VALUES (?,?)");
			statement.setInt(1, teacherAddr.getTeacherNo());
			statement.setString(2, teacherAddr.getTeacherAddrContent());
			
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
	
	//���� : ������ �������� ���ϱ� ���� �޼��带 �����մϴ�.
	//�Ű����� : int �⺻Ÿ������ rowPerPage, String ����Ÿ������ searchWord �Ű������� ������ �������� ������ �˻� Ű���带 �޽��ϴ�.
	//���ϰ� : ���ϰ��� totalRow�� teacher ���̺� teacher_no�� ������ �����մϴ�.
	public int lastPageTeacher(int rowPerPage , String searchWord) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		int totalRow=0;
		int lastPage=0;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String URL = "jdbc:mysql://localhost:3306/284db?useCode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			String sql = "SELECT COUNT(teacher_no) FROM teacher";
			
			connection = DriverManager.getConnection(URL, dbUser, dbPass);
			
			// �˻� Ű���尡 ������ ��ü teacher_no�� ���� ��ȸ�ϰ� Ű���尡 ������ Ű���尡 �� ��ȸ�� ����� teacher_no ���� ��ȸ�մϴ�.
			if(searchWord.equals("")) {
				statement = connection.prepareStatement(sql);
			}else{
				sql = "SELECT COUNT(teacher_no) FROM teacher WHERE teacher_name like ? ORDER BY teacher_no";
				statement = connection.prepareStatement(sql);
				statement.setString(1, "%"+searchWord+"%");

			}
			
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				totalRow=resultSet.getInt("COUNT(teacher_no)");
			}
			lastPage = (totalRow-1) / rowPerPage;
			// if ���ǹ��� ����� �� �����Ͱ���(COUNT(teacher_no))-1 �� rowPerPage�� �������� 0�� �ƴҶ� ������ �������� 1�� ���� ��ŵ�ϴ�.
			if((totalRow-1) % rowPerPage != 0) {
				lastPage++;
			}
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
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
		return lastPage;
	} 
	
	// ���� : ����̹� �ε� , DB���� , select ������ �ۼ� �����ؼ�  teacher ���̺� ���� �����͸� ��ȸ�ϰ� ��ȸ�� �����͸� ArrayList Ŭ����Ÿ������ ��ü���� �迭�� �ּҰ����� ��� ArrayList��ü�� �ּҰ��� �����ϴ� �޼��� ���� 
	// �Ű����� : int �⺻Ÿ������ currentPage,�� pagePerRow�� �޾Ƽ� select �������� limit�� �Ἥ ��ȸ�ϰ��մϴ�.
	// ���ϰ� : ArrayList<Teacher> Ÿ������ Teacher ��ü���� �ּҰ��� ArrayList�� add(Teacher)�޼��� ȣ���ؼ� index(��ü�迭)�� �߰� �ǰ� �ּҰ��� �����մϴ�.
	public ArrayList<Teacher> selectTeacherByPage(int currentPage, int pagePerRow, String searchWord){
		
		ArrayList<Teacher> teacherlist = new ArrayList<Teacher>(); // ��ü���� �ּҰ����� �ޱ����� ArrayList Ŭ������ import �ϰ� ��ü�� �����մϴ�.
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		int startRow = (currentPage-1)*pagePerRow; //currentPage, pagePerRow �Ű������� �޾Ƽ� �����ϴ� �����Ϳ��� ���ϰ� starRow ������ �����մϴ�.
		String sql = "SELECT * FROM teacher ORDER BY teacher_no LIMIT ?,?";
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String URL = "jdbc:mysql://localhost:3306/284db?useCode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			
			connection = DriverManager.getConnection(URL, dbUser, dbPass);
			
			if(searchWord.equals("")) {
				sql = "SELECT * FROM teacher ORDER BY teacher_no LIMIT ?,?";
				statement = connection.prepareStatement(sql);
				statement.setInt(1, startRow);
				statement.setInt(2, pagePerRow);
			}else {
				sql = "SELECT * FROM teacher WHERE teacher_name like ? ORDER BY teacher_no LIMIT ?,?";
				statement = connection.prepareStatement(sql);
				statement.setString(1, "%"+searchWord+"%");
				statement.setInt(2, startRow);
				statement.setInt(3, pagePerRow);
			}
			
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
