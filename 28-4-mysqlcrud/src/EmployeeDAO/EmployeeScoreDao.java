package EmployeeDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DBConnection.DBconnection;
import EmployeeDTO.Employee;
import EmployeeDTO.EmployeeAndScore;
import EmployeeDTO.EmployeeScore;

public class EmployeeScoreDao {
	
	//������� ���ϴ� �޼ҵ�
	public int selectScoreAvg() {
		System.out.println("selectScoreAvg");
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		
		//employee_score table�� �ȿ� �ִ� score Į���� ��� ���� ���Ͽ���
		String sql="select avg(score) from employee_score";
		
		//return���� ������ ���� �ʱ�ȭ �Ѵ�.
		int score=0;
		
		try {
			//db����
			DBconnection dbConnection=new DBconnection();
			connection=dbConnection.getConnection();
			
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
	public ArrayList<EmployeeAndScore> selectmemberListAboveAvg(int currentPage,int StartRow){
		
		//
		ArrayList<EmployeeAndScore> list=new ArrayList<EmployeeAndScore>();
		
		//db ���� ���� ���� ��� ��ȸ ���� �������� ����
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		
		int start=(currentPage-1)*StartRow;
		
		//��հ����� scoreĮ���� ��� �ִ� ���� ��ũ�� employee_score �� employee �� employee_no Į���ȿ� �ִ� ���� ������ employee_score_no,employee_name,score Į�� �ȿ� �ִ� ���� �������
		String sql="SELECT es.employee_score_no,e.employee_name,es.score\r\n" + 
				"FROM employee_score es\r\n" + 
				"INNER JOIN employee e ON es.employee_no=e.employee_no\r\n" + 
				"WHERE score>=(\r\n" + 
				"SELECT AVG(score)\r\n" + 
				"FROM employee_score) order by employee_score_no asc limit ?,?";
	
		try {
			//db����
			DBconnection dbConnection=new DBconnection();
			connection=dbConnection.getConnection();
			
			//���� �����غ�
			statement=connection.prepareStatement(sql);
			statement.setInt(1, start);
			statement.setInt(2, StartRow);
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
		
		//employee_score�� table�� �ִ� employee_no,scoreĮ���� ���� �������ش�
		String sql="INSERT INTO employee_score(employee_no,score) VALUES(?,?)";

		
		try {
			//db����
			DBconnection dbConnection=new DBconnection();
			connection=dbConnection.getConnection();
			
			//���� �����غ�
			statement=connection.prepareStatement(sql);
			statement.setInt(1, employeeScore.getEmployee_no());
			statement.setInt(2, employeeScore.getScore());
			
			System.out.println(statement+"<--statement");
			//���� ����
			statement.executeUpdate();
			
			System.out.println(statement+"<--statement");
		
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
		
		//EmployeeAndScore�� ���� �����ϱ����ؼ� ��ü�� �����ϰ� �����Ѵ��� EmployeeAndScore�� Employee,EmployeeScore�� �ּҵ��� �����Ѵ�.
		EmployeeAndScore employeeAndScore=new EmployeeAndScore();
		EmployeeScore employeeScore=new EmployeeScore();
		Employee employee=new Employee();
		
		
		try {
			//db����
			DBconnection dbConnection=new DBconnection();
			connection=dbConnection.getConnection();
			
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
	
	//employee_score db ���̺� �ִ°����� �������ش�
	//�Ű������� ������ ������ ������ ���� no���� �Ű������� �޾��ش�.
	public int updateEmployeeScore(int no,int score) {
			
			System.out.println("updateEmployeeScore");
			
			//db ���� ���� ���� ��� ��ȸ ���� �������� ����
			Connection connection=null;
			PreparedStatement statement=null;
			
			//employee_score�� table�� �ִ� employee_no,scoreĮ���� ���� �����Ѵ�.
			String sql="update employee_score set score=? where employee_no=?";
	
			
			try {
				//db����
				DBconnection dbConnection=new DBconnection();
				connection=dbConnection.getConnection();
				
				//���� �����غ�
				statement=connection.prepareStatement(sql);
				statement.setInt(1, score);
				statement.setInt(2, no);
				
				System.out.println(statement+"<--statement");
				//���� ����
				statement.executeUpdate();
				
				System.out.println(statement+"<--statement");
			
			}catch (SQLException e) {
			
				e.printStackTrace();
			}
			finally {
				//��ü ����� �ݳ�
				try {statement.close();} catch (SQLException e) {e.printStackTrace();}
				try {connection.close();} catch (SQLException e) {e.printStackTrace();}
			}
		return 0;
	}

	//������ư�� ������ �� ���� �־��� ���� �����������Ͽ� select������ ��ȸ�� �Ѵ�.
	//������ ���� no���� ��ȸ�ϱ����Ͽ� no���� �Ű������� �޾ƿ´�.
	public EmployeeScore updateSelectEmployee(int no) {
		System.out.println("updateSelectEmployee");
		
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		
		//employee_score table��employee_no��?�� employee_score�÷��� �ִ� ���� ��ȸ�Ͽ���
		String sql="select * from employee_score where employee_no=?";
		
		EmployeeScore employeeScore=new EmployeeScore();
		
		try {
			
			//db����
			DBconnection dbConnection=new DBconnection();
			connection=dbConnection.getConnection();
			
			//���� ���� �غ�
			statement=connection.prepareStatement(sql);
			statement.setInt(1, no);
			//��������
			resultSet=statement.executeQuery();
			
			//���� �������� ����� ������ ���๮�� ���� ���Ѷ�
			if(resultSet.next()) {
				employeeScore.setScore(resultSet.getInt("score"));
				
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
		return employeeScore;
	}

	//����¡ �۾�
	
	public int paging(int StartRow) {
			
			Connection connection=null;
			PreparedStatement statement=null;
			ResultSet resultset=null;
			
			//employee table�� �ִ� ������ ��ȸ�Ͽ���
			String sql="select count(*) from employee_score where score>=(select avg(score) from employee_score)";
			
			int total=0;
			
			try {
				
				//db����
				DBconnection dbConnection=new DBconnection();
				connection=dbConnection.getConnection();
				
				//���� ���� �غ�
				statement=connection.prepareStatement(sql);
				//���� ����
				resultset=statement.executeQuery();
				
				//�� �������������� �Ұ��� total������ �����ϰ� ���� 0���� �ʱ�ȭ�Ѵ�
				
				
				//���� �����ؼ� ��ȸ�� ��� �������� ���๮�� ����
				if(resultset.next()){
					// �Ѱ����� ����� ������ ������ 0�̸� total�� ���� ���� �����ϰ� �ƴϸ� �������� 1�� ���ؼ� �����Ѵ�
					if(resultset.getInt("count(*)")%StartRow==0){
						total=resultset.getInt("count(*)")/StartRow;
					}else{
						total=(resultset.getInt("count(*)")/StartRow)+1;
					}
				}
			
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {resultset.close();} catch (SQLException e) {e.printStackTrace();}
				try {statement.close();} catch (SQLException e) {e.printStackTrace();}
				try {connection.close();} catch (SQLException e) {e.printStackTrace();}
			}
			return total;
		}
}

