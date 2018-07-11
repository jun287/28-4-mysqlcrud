package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import service.EmployeeScore;
import service.Employee;
import service.EmployeeAndScore;

public class EmployeeScoreDao {
	
	//평균점수 구하는 메소드
	public int selectScoreAvg() {
		System.out.println("selectScoreAvg");
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		
		//ip주소,포트번호,db명,사용자id,패스워드를 각각 String data type으로 선언된 변수에 담아라
		String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
		String userid="java";
		String userpw="java0000";
				
				
		//db로딩 sql문 준비
		String dbDriver="com.mysql.jdbc.Driver";
		
		//employee_score table의 안에 있는 score 칼럼에 평균 값을 구하여라
		String sql="select avg(score) from employee_score";
		
		//return받을 변수를 만들어서 초기화 한다.
		int score=0;
		
		try {
			//Driver로딩
			Class.forName(dbDriver);
			
			//db연결
			connection=DriverManager.getConnection(dbname, userid, userpw);
			
			//쿼리 실행 준비
			statement=connection.prepareStatement(sql);
			
			//쿼리실행
			resultSet=statement.executeQuery();
			
			//만약 쿼리실행 결과가 있으면 실행문을 실행 시켜라
			if(resultSet.next()) {
				
				//score의 평균값을 score의 변수에 대입하여라
				score=resultSet.getInt("avg(score)");
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			
			//객체 사용후  반납
		}finally {
			try {resultSet.close();} catch (SQLException e) {e.printStackTrace();}
			try {statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		//변수 score에 담겨있는 값을 리턴
		return score;
	}
	
	//평균점수 이상인사람을 구하는 메소드
	public ArrayList<EmployeeAndScore> selectmemberListAboveAvg(){
		
		//
		ArrayList<EmployeeAndScore> list=new ArrayList<EmployeeAndScore>();
		
		//db 연결 쿼리 실행 결과 조회 관련 변수들을 생성
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		
		//ip주소,포트번호,db명,사용자id,패스워드를 각각 String data type으로 선언된 변수에 담아라
		String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
		String userid="java";
		String userpw="java0000";
		
		
		//db로딩 sql문 준비
		String dbDriver="com.mysql.jdbc.Driver";
		
		//평균값보다 score칼럼에 들어 있는 값이 더크고 employee_score 과 employee 를 employee_no 칼럼안에 있는 값이 같을때 employee_score_no,employee_name,score 칼럼 안에 있는 값을 보여줘라
		String sql="SELECT es.employee_score_no ,e.employee_name,es.score FROM   employee_score es  INNER JOIN employee e ON es.employee_no=e.employee_no WHERE score>=(SELECT AVG(score) FROM employee_score)";
	
		try {
			//Driver로딩
			Class.forName(dbDriver);
			
			//db연결
			connection=DriverManager.getConnection(dbname,userid,userpw);
			
			//쿼리 실행준비
			statement=connection.prepareStatement(sql);
			
			//쿼리 실행
			resultSet=statement.executeQuery();
			
			//쿼리를 싱행하여 결과가 안나올 때까지 반복시킨다.
			while(resultSet.next()) {
				
					//EmployeeAndScore에 최종 저장하기위해서 객체를 선언하고 저장한다음 EmployeeAndScore에 Employee,EmployeeScore의 주소들을 저장한다.
					EmployeeAndScore employeeAndScore=new EmployeeAndScore();
					Employee employee =new Employee();
					EmployeeScore employeeScore=new EmployeeScore();
					
					employee.setEmployeeNo(resultSet.getInt("es.employee_score_no"));
					employee.setEmployeeName(resultSet.getString("e.employee_name"));
					employeeScore.setScore(resultSet.getInt("es.score"));
					
					employeeAndScore.setEmployee(employee);
					employeeAndScore.setEmployeescore(employeeScore);
					
					//list에 employeeAndScore의 주소를 저장한다.
					list.add(employeeAndScore);
					
			}
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			//객체 사용후 반납
			try {resultSet.close();} catch (SQLException e) {e.printStackTrace();}
			try {statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		//list의 주소를 리턴한다.
		return list;
	}
	
	//점수를 db에 저장하는 메소드
	//매개변수에는 employeeScore의 주소값을 대입하여 주소값을 따라가서 메소드를 겟팅하여 변수에 있는 값들을 가져와 db삽입한다.
	public void insertScore(EmployeeScore employeeScore){
		System.out.println("insertScore");
		
		//db 연결 쿼리 실행 결과 조회 관련 변수들을 생성
		Connection connection=null;
		PreparedStatement statement=null;
		
		
		
		//ip주소,포트번호,db명,사용자id,패스워드를 각각 String data type으로 선언된 변수에 담아라
		String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
		String userid="java";
		String userpw="java0000";
		
		
		//Driver로딩
		String dbDriver="com.mysql.jdbc.Driver";
		
		//employee_score의 table에 있는 employee_no,score칼럼에 각각 대입해준다
		String sql="INSERT INTO employee_score(employee_no,score) VALUES(?,?)";

		
		try {
			//db로딩
			Class.forName(dbDriver);
			
			//db연결
			connection=DriverManager.getConnection(dbname, userid, userpw);
			
			//쿼리 실행준비
			statement=connection.prepareStatement(sql);
			statement.setInt(1, employeeScore.getEmployee_no());
			statement.setInt(2, employeeScore.getScore());
			
			System.out.println(statement+"<--statement");
			//쿼리 실행
			statement.executeUpdate();
			
			System.out.println(statement+"<--statement");
		
		}catch(ClassNotFoundException e) {
			
			e.printStackTrace();
		}catch (SQLException e) {
		
			e.printStackTrace();
		}
		finally {
			//객체 사용후 반납
			try {statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();} catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	//점수를 db에서 가져와는 메소드
	public EmployeeAndScore selectEmployeeAndScore(int no) {
		System.out.println("selectEmployeeAndScore");
		
		//db 연결 쿼리 실행 결과 조회 관련 변수들을 생성
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		
		//ip주소,포트번호,db명,사용자id,패스워드를 각각 String data type으로 선언된 변수에 담아라
		String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
		String userid="java";
		String userpw="java0000";
		
		//EmployeeAndScore에 최종 저장하기위해서 객체를 선언하고 저장한다음 EmployeeAndScore에 Employee,EmployeeScore의 주소들을 저장한다.
		EmployeeAndScore employeeAndScore=new EmployeeAndScore();
		EmployeeScore employeeScore=new EmployeeScore();
		Employee employee=new Employee();
		
		
		String dbDriver="com.mysql.jdbc.Driver";
		
		try {
			//Driver로딩
			Class.forName(dbDriver);
			
			//db연결
			connection=DriverManager.getConnection(dbname, userid, userpw);
			//employee_score.employee_no가 ?일때 employee와employee_score에서 employee_no컬럼에 있는 값들이 같으면 employee_no,employee_name,employee_age,score칼럼에 있는 값들을 보여줘라
			statement=connection.prepareStatement("select employee.employee_no,employee_name,employee_age,score from employee inner join employee_score on employee.employee_no=employee_score.employee_no where employee_score.employee_no=?");
			statement.setInt(1, no);
			resultSet=statement.executeQuery();
			
			
			//만약결과가 있으면 안에 있는 실행문을 실행 결과가 없으면 employeeName에 값이 없습니다 문자열 저장
			if(resultSet.next()) {
				
				//EmployeeAndScore에 최종 저장하기위해서 객체를 선언하고 저장한다음 EmployeeAndScore에 Employee,EmployeeScore의 주소들을 저장한다.
				employee.setEmployeeNo(resultSet.getInt("employee.employee_no"));
				employee.setEmployeeName(resultSet.getString("employee_name"));
				employee.setEmployeeAge(resultSet.getInt("employee_age"));
				employeeAndScore.setEmployee(employee);
				
				employeeScore.setScore(resultSet.getInt("score"));
				employeeAndScore.setEmployeescore(employeeScore);	
			}else {
				employee.setEmployeeName("값이 없습니다.");
				employeeAndScore.setEmployee(employee);
			}
			
		}catch(ClassNotFoundException e) {
	
			e.printStackTrace();
		}catch (SQLException e) {
		
			e.printStackTrace();
		}
		finally {
			//객체 사용후 반납
			try {statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		//employeeAndScore에 담겨있는 주소값을 리턴한다.
		return employeeAndScore;
	}
	}

