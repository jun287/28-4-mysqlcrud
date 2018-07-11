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
	
	//������� ���ϴ� �޼ҵ�
	public int selectScoreAvg() {
		System.out.println("selectScoreAvg");
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		
		//ip�ּ�,��Ʈ��ȣ,db��,�����id,�н����带 ���� String data type���� ����� ������ ��ƶ�
		String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
		String userid="java";
		String userpw="java0000";
				
				
		//db�ε� sql�� �غ�
		String dbDriver="com.mysql.jdbc.Driver";
		
		//employee_score table�� �ȿ� �ִ� score Į���� ��� ���� ���Ͽ���
		String sql="select avg(score) from employee_score";
		
		//return���� ������ ���� �ʱ�ȭ �Ѵ�.
		int score=0;
		
		try {
			//Driver�ε�
			Class.forName(dbDriver);
			
			//db����
			connection=DriverManager.getConnection(dbname, userid, userpw);
			
			//���� ���� �غ�
			statement=connection.prepareStatement(sql);
			
			//��������
			resultSet=statement.executeQuery();
			
			//���� �������� ����� ������ ���๮�� ���� ���Ѷ�
			if(resultSet.next()) {
				
				//score�� ��հ��� score�� ������ �����Ͽ���
				score=resultSet.getInt("avg(score)");
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			
			//��ü �����  �ݳ�
		}finally {
			try {resultSet.close();} catch (SQLException e) {e.printStackTrace();}
			try {statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		//���� score�� ����ִ� ���� ����
		return score;
	}
	
	//������� �̻��λ���� ���ϴ� �޼ҵ�
	public ArrayList<EmployeeAndScore> selectmemberListAboveAvg(){
		
		//
		ArrayList<EmployeeAndScore> list=new ArrayList<EmployeeAndScore>();
		
		//db ���� ���� ���� ��� ��ȸ ���� �������� ����
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		
		//ip�ּ�,��Ʈ��ȣ,db��,�����id,�н����带 ���� String data type���� ����� ������ ��ƶ�
		String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
		String userid="java";
		String userpw="java0000";
		
		
		//db�ε� sql�� �غ�
		String dbDriver="com.mysql.jdbc.Driver";
		
		//��հ����� scoreĮ���� ��� �ִ� ���� ��ũ�� employee_score �� employee �� employee_no Į���ȿ� �ִ� ���� ������ employee_score_no,employee_name,score Į�� �ȿ� �ִ� ���� �������
		String sql="SELECT es.employee_score_no ,e.employee_name,es.score FROM   employee_score es  INNER JOIN employee e ON es.employee_no=e.employee_no WHERE score>=(SELECT AVG(score) FROM employee_score)";
	
		try {
			//Driver�ε�
			Class.forName(dbDriver);
			
			//db����
			connection=DriverManager.getConnection(dbname,userid,userpw);
			
			//���� �����غ�
			statement=connection.prepareStatement(sql);
			
			//���� ����
			resultSet=statement.executeQuery();
			
			//������ �����Ͽ� ����� �ȳ��� ������ �ݺ���Ų��.
			while(resultSet.next()) {
				
					//EmployeeAndScore�� ���� �����ϱ����ؼ� ��ü�� �����ϰ� �����Ѵ��� EmployeeAndScore�� Employee,EmployeeScore�� �ּҵ��� �����Ѵ�.
					EmployeeAndScore employeeAndScore=new EmployeeAndScore();
					Employee employee =new Employee();
					EmployeeScore employeeScore=new EmployeeScore();
					
					employee.setEmployeeNo(resultSet.getInt("es.employee_score_no"));
					employee.setEmployeeName(resultSet.getString("e.employee_name"));
					employeeScore.setScore(resultSet.getInt("es.score"));
					
					employeeAndScore.setEmployee(employee);
					employeeAndScore.setEmployeescore(employeeScore);
					
					//list�� employeeAndScore�� �ּҸ� �����Ѵ�.
					list.add(employeeAndScore);
					
			}
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			//��ü ����� �ݳ�
			try {resultSet.close();} catch (SQLException e) {e.printStackTrace();}
			try {statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		//list�� �ּҸ� �����Ѵ�.
		return list;
	}
	
	//������ db�� �����ϴ� �޼ҵ�
	//�Ű��������� employeeScore�� �ּҰ��� �����Ͽ� �ּҰ��� ���󰡼� �޼ҵ带 �����Ͽ� ������ �ִ� ������ ������ db�����Ѵ�.
	public void insertScore(EmployeeScore employeeScore){
		System.out.println("insertScore");
		
		//db ���� ���� ���� ��� ��ȸ ���� �������� ����
		Connection connection=null;
		PreparedStatement statement=null;
		
		
		
		//ip�ּ�,��Ʈ��ȣ,db��,�����id,�н����带 ���� String data type���� ����� ������ ��ƶ�
		String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
		String userid="java";
		String userpw="java0000";
		
		
		//Driver�ε�
		String dbDriver="com.mysql.jdbc.Driver";
		
		//employee_score�� table�� �ִ� employee_no,scoreĮ���� ���� �������ش�
		String sql="INSERT INTO employee_score(employee_no,score) VALUES(?,?)";

		
		try {
			//db�ε�
			Class.forName(dbDriver);
			
			//db����
			connection=DriverManager.getConnection(dbname, userid, userpw);
			
			//���� �����غ�
			statement=connection.prepareStatement(sql);
			statement.setInt(1, employeeScore.getEmployee_no());
			statement.setInt(2, employeeScore.getScore());
			
			System.out.println(statement+"<--statement");
			//���� ����
			statement.executeUpdate();
			
			System.out.println(statement+"<--statement");
		
		}catch(ClassNotFoundException e) {
			
			e.printStackTrace();
		}catch (SQLException e) {
		
			e.printStackTrace();
		}
		finally {
			//��ü ����� �ݳ�
			try {statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();} catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	//������ db���� �����ʹ� �޼ҵ�
	public EmployeeAndScore selectEmployeeAndScore(int no) {
		System.out.println("selectEmployeeAndScore");
		
		//db ���� ���� ���� ��� ��ȸ ���� �������� ����
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		
		//ip�ּ�,��Ʈ��ȣ,db��,�����id,�н����带 ���� String data type���� ����� ������ ��ƶ�
		String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
		String userid="java";
		String userpw="java0000";
		
		//EmployeeAndScore�� ���� �����ϱ����ؼ� ��ü�� �����ϰ� �����Ѵ��� EmployeeAndScore�� Employee,EmployeeScore�� �ּҵ��� �����Ѵ�.
		EmployeeAndScore employeeAndScore=new EmployeeAndScore();
		EmployeeScore employeeScore=new EmployeeScore();
		Employee employee=new Employee();
		
		
		String dbDriver="com.mysql.jdbc.Driver";
		
		try {
			//Driver�ε�
			Class.forName(dbDriver);
			
			//db����
			connection=DriverManager.getConnection(dbname, userid, userpw);
			//employee_score.employee_no�� ?�϶� employee��employee_score���� employee_no�÷��� �ִ� ������ ������ employee_no,employee_name,employee_age,scoreĮ���� �ִ� ������ �������
			statement=connection.prepareStatement("select employee.employee_no,employee_name,employee_age,score from employee inner join employee_score on employee.employee_no=employee_score.employee_no where employee_score.employee_no=?");
			statement.setInt(1, no);
			resultSet=statement.executeQuery();
			
			
			//�������� ������ �ȿ� �ִ� ���๮�� ���� ����� ������ employeeName�� ���� �����ϴ� ���ڿ� ����
			if(resultSet.next()) {
				
				//EmployeeAndScore�� ���� �����ϱ����ؼ� ��ü�� �����ϰ� �����Ѵ��� EmployeeAndScore�� Employee,EmployeeScore�� �ּҵ��� �����Ѵ�.
				employee.setEmployeeNo(resultSet.getInt("employee.employee_no"));
				employee.setEmployeeName(resultSet.getString("employee_name"));
				employee.setEmployeeAge(resultSet.getInt("employee_age"));
				employeeAndScore.setEmployee(employee);
				
				employeeScore.setScore(resultSet.getInt("score"));
				employeeAndScore.setEmployeescore(employeeScore);	
			}else {
				employee.setEmployeeName("���� �����ϴ�.");
				employeeAndScore.setEmployee(employee);
			}
			
		}catch(ClassNotFoundException e) {
	
			e.printStackTrace();
		}catch (SQLException e) {
		
			e.printStackTrace();
		}
		finally {
			//��ü ����� �ݳ�
			try {statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		//employeeAndScore�� ����ִ� �ּҰ��� �����Ѵ�.
		return employeeAndScore;
	}
	}

