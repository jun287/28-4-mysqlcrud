package TeacherDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBConnection.DBconnection;
import TeacherDTO.TeacherAddr;

public class TeacherAddrDao {
	
	// 설명 : 드라이버 로딩 , DB연결 , select 쿼리문 작성 실행해서 teacherAddr 테이블에 데이터를 TeacherAddr 객체로 받아서 객체의 주소값을 리턴하는 메서드 선언
	// 매개변수 : int teacherNo 매개변수로 번호를 받습니다.
	// 리턴값 : TeacherAddr 클래스타입으로 객체의 주소값을 리턴합니다.
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
	
	// 설명 : 드라이버 로딩 , DB연결 , update 쿼리문 작성 실행해서 teacherAddr 테이블에 주소값을 수정하는 메서드 선언
	// 매개변수 : TeacherAddr 클래스타입으로 teacherAddr 매개변수를 선언하고 주소와 번호가 담긴 객체의 주소값을 받습니다.
	// 리턴값 : void로 없습니다.
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
	
	
	// 설명 : 드라이버 로딩 , DB연결 , select 쿼리문 작성 실행해서 teacher 테이블에 데이터를 조회하고 주소값을 담은 객체의 주소값 리턴하는 메서드 선언
	// 매개변수 : int 기본타입으로 teacherNo 매개변수를 선언하고 등록된 번호를 받습니다.
	// 리턴값 : TeacherAddr 클래스 타입으로 객체의 주소값을 리턴합니다.
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
	
	// 설명 : 드라이버 로딩 , DB연결 , delete 쿼리문 작성 실행해서 teacherAddr 테이블에 데이터 삭제하는 메서드 선언
	// 매개변수 : int 기본타입으로 teacherNo 매개변수를 선언하고 등록된 번호를 받습니다.
	// 리턴값 : void로 업습니다.
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

	// 설명 : 드라이버 로딩 , DB연결 , insert 쿼리문 작성 실행해서  teacherAddr 테이블에 주소 데이터 입력하는 메서드 선언 
	// 매개변수 : TeacherAddr 클래스타입으로 teacherAddr 객체의 주소값을 받습니다.
	// 리턴값 : void로 없습니다.
	public void insertTeacherAddr(TeacherAddr teacherAddr) {
		
		Connection connection = null; 
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		PreparedStatement statement3 = null;
		ResultSet resultSet = null;

		// 프로그램 실행중 발생하는 문제적인 상황을 예외 처리 하기 위해 try를 사용합니다.
		try {
			
			DBconnection dbConnection = new DBconnection();
			connection = dbConnection.getConnection();
			
			System.out.println("DB연결");
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
		/* DriverManager클래스객체에 getConnection 메서드를 호출
		Connection 클래스 타입의 connection객체참조변수에 대입하고 DB연결 및 Connection클래스 객체의 prepareStatement 메서드에 쿼리문을 대입하고 호출하여
		statement(PreparedStatement클래스객체)에 executeUpdate 메서드로 쿼리문 실행시 나올수 있는 프로그램 실행중 발생하는 문제적 상황을 예외처리합니다.
		 */ 
		}catch(SQLException ex){
			ex.printStackTrace();
		// 드라이버로딩, DB연결, 쿼리문 작성 및 실행이 끝나거나 혹은 작동이 안되었을때 종료해주기 위해 finally를 쓰고 if조건문으로 객체참조변수의 값이 null 이 아닐시 close 메서드로 종료시킵니다.
		// 이때도 마찬가지로 예외처리를 해줍니다.
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
