//2018. 07. 02 28기 공세준 

package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/* 
 * JDBC API SELECT Resultet -> ArrayList<Teacher>
 * list : 갯수가 동적, list.size(), remove(4), 
 * 배열	: 갯수가 정적, 배열.length, 배열[4],
 */

public class TeacherDao {
	
	//설명 : 마지막 페이지를 구하기 위해 메서드를 선언합니다.
	//매개변수 : 매개변수는 없습니다.
	//리턴값 : 리턴값은 totalRow로 teacher 테이블에 teacher_no의 갯수를 리턴합니다.
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
	
	// 설명 : 드라이버 로딩 , DB연결 , select 쿼리문 작성 실행해서  teacher 테이블에 교사 데이터를 조회하고 조회된 데이터를 ArrayList 클래스타입으로 객체들의 배열의 주소값들이 담긴 ArrayList객체의 주소값을 리턴하는 메서드 선언 
	// 매개변수 : int 기본타입으로 currentPage,와 pagePerRow를 받아서 select 쿼리문에 limit를 써서 조회하게합니다.
	// 리턴값 : ArrayList<Teacher> 타입으로 Teacher 객체들의 주소값이 ArrayList에 add(Teacher)메서드 호출해서 index(객체배열)에 추가 되고 주소값을 리턴합니다.
	public ArrayList<Teacher> selectTeacherByPage(int currentPage, int pagePerRow){
		
		ArrayList<Teacher> teacherlist = new ArrayList<Teacher>(); // 객체들의 주소값들을 받기위해 ArrayList 클래스를 import 하고 객체를 생성합니다.
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
			
			int startRow = (currentPage-1)*pagePerRow; //currentPage, pagePerRow 매개변수를 받아서 시작하는 데이터열을 구하고 starRow 변수에 대입합니다.
			statement = connection.prepareStatement(sql);
			statement.setInt(1, startRow);
			statement.setInt(2, pagePerRow);
			
			resultSet = statement.executeQuery();
			// 반복문을 사용해 데이터값들을 Teacher 클래스 객체를 생성후 객체내에 메서드(setTeacherNo)를 이용해 쿼리실행후 데이터들을 저장합니다.
			while(resultSet.next()){ 
				
				Teacher teacher = new Teacher();
		
				teacher.setTeacherNo(resultSet.getInt("teacher_no"));
				teacher.setTeacherName(resultSet.getString("teacher_name"));
				teacher.setTeacherAge(resultSet.getInt("teacher_age"));
				
				teacherlist.add(teacher); // 객체들의 주소값을 add메서드로 ArrayList 클래스 객체에 대입합니다. 
			}
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();	
		}finally {
			
		}
	
		return teacherlist; // ArrayList 클래스 객체의 주소값을 리턴합니다.

	}
	
	/*public Teacher[] selectTeacherAll() {
		return null;
	}*/
	
	// 설명 : 드라이버 로딩 , DB연결 , insert 쿼리문 작성 실행해서  teacher 테이블에 교사 데이터 입력하는 메서드 선언 
	// 매개변수 : Teacher 클래스 타입으로 한명의 교사 정보를 담는 객체의 주소값을 담은 변수
	// 리턴값 : void로 없습니다.
	public void insertTeacher(Teacher teacher) {
		
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
			
			statement = connection.prepareStatement("INSERT INTO teacher(teacher_name, teacher_age) VALUES (?,?)");
			statement.setString(1, teacher.getTeacherName());
			statement.setInt(2, teacher.getTeacherAge());
			
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
