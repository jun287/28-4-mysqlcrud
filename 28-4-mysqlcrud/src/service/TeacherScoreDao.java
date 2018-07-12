//2018. 07. 10. 28기 공세준

package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherScoreDao {
	
	// 설명 : 드라이버 로딩 , DB연결 , delete 쿼리문 작성 실행해서 teacher_score 테이블에 데이터 삭제하는 메서드 선언
	// 매개변수 : int 기본타입으로 teacherNo 매개변수를 선언하고 등록된 번호를 받습니다.
	// 리턴값 : void로 업습니다.
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
	
	// 설명 : 집합함수(Avg) 사용하여 teacher_score 테이블에 score 평균값을 구하는 메서드 선언
	// 매개변수 : 매개변수는 없습니다.
	// 리턴 : 평균값을 int 기본타입으로 리턴합니다.
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
	
	
	// 설명: join + subquery 사용하여 score 점수가 평균보다 높은 사람을 구하는 메서드 선언
	// 매개변수: 매개변수는 없습니다.
	// 리턴값:	 ArrayList<TeacherAndScore> 클래스 타입으로 arrayList(teacher와 teacherScore 클래스객체에 쿼리 조회후 결과값이 담긴 주소값이 담긴 teacherAndScore 클래스객체의 주소값)주소값을 리턴합니다.
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
	
	// 설명 : 드라이버 로딩 , DB연결 , select 쿼리문 작성 실행해서  teacher테이블과 teacher_score테이블의 데이터를 조인하여 데이터를 조회하고 조회된 데이터를  TeacherAndScore 클래스타입으로 Teacher와 Teacher_score객체들의 주소값들이 담긴 객체의 주소값을 리턴하는 메서드 선언 
	// 매개변수 : int 기본타입으로 teacherNo를 받아서 select 쿼리문에 teacher_no에 대입해서  조회하게합니다.
	// 리턴값 : TeacherAndScore 클래스 타입으로 Teacher와 TeacherScore 객체들의 주소값이 담긴 주소값을 리턴합니다.
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
			return teacherAndScore;
	}
	// 설명 : 드라이버 로딩 , DB연결 , insert 쿼리문에 teacherScore 클래스객체에 받은 값들을  teacher_score 테이블에 교사 데이터를 입력하는 메서드 선언
	// 매개변수 : TeacherScore 클래스 타입으로 teacherScore 객체 주소값을 받습니다.
	// 리턴값 : void 로 없습니다.
	public void insertTeacherScore(TeacherScore teacherScore) {
			
		Connection connection = null; 
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		PreparedStatement statement3 = null;
		ResultSet resultSet = null;

		// 프로그램 실행중 발생하는 문제적인 상황을 예외 처리 하기 위해 try를 사용합니다.
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
