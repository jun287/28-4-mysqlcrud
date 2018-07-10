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
				
				
		//Driver로딩
		String dbDriver="com.mysql.jdbc.Driver";
		String sql="select avg(score) from employee_score";
		
		int score=0;
		try {
			Class.forName(dbDriver);
			connection=DriverManager.getConnection(dbname, userid, userpw);
			statement=connection.prepareStatement(sql);
			resultSet=statement.executeQuery();
			
			if(resultSet.next()) {
				score=resultSet.getInt("avg(score)");
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {resultSet.close();} catch (SQLException e) {e.printStackTrace();}
			try {statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return score;
	}
	//평균점수 이상인사람을 구하는 메소드
	public ArrayList<EmployeeAndScore> selectmemberListAboveAvg(){
		
		ArrayList<EmployeeAndScore> list=new ArrayList<EmployeeAndScore>();
		
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		
		//ip주소,포트번호,db명,사용자id,패스워드를 각각 String data type으로 선언된 변수에 담아라
		String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
		String userid="java";
		String userpw="java0000";
		
		
		//Driver로딩
		String dbDriver="com.mysql.jdbc.Driver";
		String sql="SELECT es.employee_score_no ,e.employee_name,es.score FROM   employee_score es  INNER JOIN employee e ON es.employee_no=e.employee_no WHERE score>=(SELECT AVG(score) FROM employee_score)";
	
		try {
			Class.forName(dbDriver);
			connection=DriverManager.getConnection(dbname,userid,userpw);
			statement=connection.prepareStatement(sql);
			resultSet=statement.executeQuery();
			
			while(resultSet.next()) {
				EmployeeAndScore employeeAndScore=new EmployeeAndScore();
				Employee employee =new Employee();
				EmployeeScore employeeScore=new EmployeeScore();
				
				employee.setEmployeeNo(resultSet.getInt("es.employee_score_no"));
				employee.setEmployeeName(resultSet.getString("e.employee_name"));
				employeeScore.setScore(resultSet.getInt("es.score"));
				
				employeeAndScore.setEmployee(employee);
				employeeAndScore.setEmployeescore(employeeScore);
				
				list.add(employeeAndScore);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {resultSet.close();} catch (SQLException e) {e.printStackTrace();}
			try {statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		return list;
	}
	//점수를 db에 저장하는 메소드
	public void insertScore(EmployeeScore employeeScore){
		System.out.println("insertScore");
		
		Connection connection=null;
		PreparedStatement statement=null;
		PreparedStatement statement1=null;
		ResultSet resultset=null;
		
		//ip주소,포트번호,db명,사용자id,패스워드를 각각 String data type으로 선언된 변수에 담아라
		String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
		String userid="java";
		String userpw="java0000";
		
		
		//Driver로딩
		String dbDriver="com.mysql.jdbc.Driver";
		
		String sql="select * from employee_score where employee_no=?";
		String sql1="INSERT INTO employee_score(employee_no,score) VALUES(?,?)";
		//Employee table을 오름차순으로 ?부터 ?개를 employee_no,employee_name,employee_age 조회하여라

		
		try {
				Class.forName(dbDriver);
			
			connection=DriverManager.getConnection(dbname, userid, userpw);
			statement=connection.prepareStatement(sql);
			statement.setInt(1,employeeScore.getEmployee_no());
			resultset=statement.executeQuery();
			
			if(resultset.next()) {
			
			}else {
				statement1=connection.prepareStatement(sql1);
				statement1.setInt(1, employeeScore.getEmployee_no());
				statement1.setInt(2, employeeScore.getScore());
				statement1.executeUpdate();
			}
		
		}catch(ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();} catch (SQLException e) {e.printStackTrace();}
		}
	}
	//점수를 db에서 가져와는 메소드
	public EmployeeAndScore selectEmployeeAndScore(int no) {
		System.out.println("selectEmployeeAndScore");
		
		Connection connection=null;
		PreparedStatement statement=null;
		PreparedStatement statement1=null;
		ResultSet resultSet=null;
		
		//ip주소,포트번호,db명,사용자id,패스워드를 각각 String data type으로 선언된 변수에 담아라
		String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
		String userid="java";
		String userpw="java0000";
		EmployeeAndScore employeeAndScore=new EmployeeAndScore();
		EmployeeScore employeeScore=new EmployeeScore();
		Employee employee=new Employee();
		
		//Driver로딩
		String dbDriver="com.mysql.jdbc.Driver";
		
		try {
			Class.forName(dbDriver);
			
			connection=DriverManager.getConnection(dbname, userid, userpw);
			
			statement=connection.prepareStatement("select employee.employee_no,employee_name,employee_age,score from employee inner join employee_score on employee.employee_no=employee_score.employee_no where employee_score.employee_no=?");
			statement.setInt(1, no);
			resultSet=statement.executeQuery();
			
			
			
			if(resultSet.next()) {
				
				employee.setEmployeeNo(resultSet.getInt("employee.employee_no"));
				employee.setEmployeeName(resultSet.getString("employee_name"));
				employee.setEmployeeAge(resultSet.getInt("employee_age"));
				employeeAndScore.setEmployee(employee);
				
				employeeScore.setScore(resultSet.getInt("score"));
				employeeAndScore.setEmployeescore(employeeScore);	
			}
		}catch(ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		return employeeAndScore;
	}
	}

