//2018. 07. 09. 28기 공세준

package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherScoreDao {
	
	// 설명 : 드라이버 로딩 , DB연결 , select 쿼리문 작성 실행해서  teacher테이블과 teacher_score테이블의 데이터를 조인하여 데이터를 조회하고 조회된 데이터를 ArrayList 클래스타입으로 객체들의 배열의 주소값들이 담긴 ArrayList객체의 주소값을 리턴하는 메서드 선언 
	// 매개변수 : int 기본타입으로 teacherNo를 받아서 select 쿼리문에 teacher_no에 대입해서  조회하게합니다.
	// 리턴값 : ArrayList<TeacherAndScore> 타입으로 Teacher와 TeacherScore 객체들의 주소값이 ArrayList에 add(TeacherAndSocre)메서드 호출해서 index(객체배열)에 추가 되고 주소값을 리턴합니다.
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
			// Class 클래스 객체에 forName 메서드를 호출하여 드라이버 로딩시 나올수 있는 프로그램 실행중 발생하는 문제적 상황을 예외처리합니다.
			}catch(ClassNotFoundException ex) {
				ex.printStackTrace();
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
			return arraylist;
	}
	
	// 설명 : 드라이버 로딩 , DB연결 , select 쿼리문 작성 실행해서 결과값이 있으면 "입력완료" 없으면 "입력요망"을 리턴하는 메서드 선언 
	// 매개변수 : int 기본타입으로 teacherNo를 받아서 select 쿼리문에 teacher_no 컬럼에 대입해서 조회하게합니다.
	// 리턴값 : String 타입으로 "입력완료" 또는 "입력요망"을 리턴합니다.
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
				result = "입력완료";
			}else {
				result = "입력요망";
			}
			
			// Class 클래스 객체에 forName 메서드를 호출하여 드라이버 로딩시 나올수 있는 프로그램 실행중 발생하는 문제적 상황을 예외처리합니다.
			}catch(ClassNotFoundException ex) {
				ex.printStackTrace();
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
			return result;
	}
	
	// 설명 : 드라이버 로딩 , DB연결 , insert 쿼리문에 teacherScore 클래스객체에 받은 값들을  teacher_score 테이블에 교사 데이터를 입력하는 메서드 선언
	// 매개변수 : TeacherScore 클래스 타입으로 teacherScore 객체 주소값을 받습니다.
	// 리턴값 : void 로 없습니다.
	public void insertTeacherScore(TeacherScore teacherScore) {
			
		Connection connection = null; 
		PreparedStatement statement = null;

		// 프로그램 실행중 발생하는 문제적인 상황을 예외 처리 하기 위해 try를 사용합니다.
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String URL = "jdbc:mysql://localhost:3306/284db?useCode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			
			connection = DriverManager.getConnection(URL, dbUser, dbPass);
			
			System.out.println("DB연결");
			System.out.println(teacherScore.getTeacherNo());
			System.out.println(teacherScore.getScore());

			statement = connection.prepareStatement("INSERT INTO teacher_score(teacher_no, score) VALUES (?,?)");
			statement.setInt(1, teacherScore.getTeacherNo());
			statement.setInt(2, teacherScore.getScore());
			
			statement.executeUpdate();
		
		// Class 클래스 객체에 forName 메서드를 호출하여 드라이버 로딩시 나올수 있는 프로그램 실행중 발생하는 문제적 상황을 예외처리합니다.
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
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
