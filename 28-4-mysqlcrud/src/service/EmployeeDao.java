// 2018. 06. 25 28�� ���μ�
package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;;

public class EmployeeDao {

	
	//db�� �ִ� ȸ�� ������ ������������ �޽��� 
	//�Ű���������  ����  �������� ��� ����Ұ�������Ÿ���� �Ű������� �����Ѵ�
	public ArrayList<Employee> selectEmployeeAll(int currentPage,int StartRow,String word){
		
		System.out.println("selectEmployeeAll()");
		
		ArrayList<Employee> list=new ArrayList<Employee>();
	
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultset=null;
		
		//ip�ּ�,��Ʈ��ȣ,db��,�����id,�н����带 ���� String data type���� ����� ������ ��ƶ�
		String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
		String userid="java";
		String userpw="java0000";
	
		//Driver�ε�
		String dbDriver="com.mysql.jdbc.Driver";
		
		//Employee table�� ������������ ?���� ?���� employee_no,employee_name,employee_age ��ȸ�Ͽ���
	
		
		int start=(currentPage-1)*StartRow;
		
		try {
			//driver �ε�
			Class.forName(dbDriver);
			
			//db����
			connection=DriverManager.getConnection(dbname, userid, userpw);
			
			//���� �����غ�
			if(word==null) {
				statement=connection.prepareStatement("SELECT employee_no,employee_name,employee_age FROM Employee ORDER BY employee_no asc LIMIT ?,?");
				statement.setInt(1, start);
				statement.setInt(2, StartRow);
			}else {
				statement=connection.prepareStatement("SELECT employee_no,employee_name,employee_age FROM Employee where employee_name like ? order by employee_no asc LIMIT ?,?");
				statement.setString(1, "%"+word+"%");
				statement.setInt(2, start);	
				statement.setInt(3, StartRow);	
			}
			//��������
			resultset=statement.executeQuery();
			
			//ȸ�� ������ ������ dto�� �����͸� �����ϰ� dto�� �ּҸ� list�� �����Ѵ�
			while(resultset.next()) {
				Employee employee=new Employee();
				
				employee.setEmployeeNo(resultset.getInt("employee_no"));
				employee.setEmployeeName(resultset.getString("employee_name"));
				employee.setEmployeeAge(resultset.getInt("employee_age"));
				
				list.add(employee);
			}
			
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {resultset.close();} catch (SQLException e) {e.printStackTrace();}
			try {statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();} catch (SQLException e) {e.printStackTrace();}
		}
		/*
		*List: ������ ����,list.size()<--list�� ����
		*�迭: ������ ����,�迭.length<--�迭�� ����,
		resultset���� return ���ټ� ������ ���⼭ ���� ������ �Ѱ������ ���ӵ��� �ʴ´�.
		*/	
		return list;
	}
	
	//����¡ �۾�
	
	public int paging(int StartRow) {
		
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultset=null;
		//ip�ּ�,��Ʈ��ȣ,db��,�����id,�н����带 ���� String data type���� ����� ������ ��ƶ�
		String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
		String userid="java";
		String userpw="java0000";
	
		//Driver�ε�
		String dbDriver="com.mysql.jdbc.Driver";
		
		//employee table�� �ִ� ������ ��ȸ�Ͽ���
		String sql="select count(*) from employee";
		
		int total=0;
		
		try {
			//����̹� �ε�
			Class.forName(dbDriver);
			
			//db����
			connection=DriverManager.getConnection(dbname, userid, userpw);
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
		
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {resultset.close();} catch (SQLException e) {e.printStackTrace();}
			try {statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return total;
	}
	
	//ȸ�� ������ db���ٰ� �����ϱ����� �޼���
	public void insertEmployee(Employee employee) {

		
		
		//class data type���� ������ �����ϰ� null�� �ʱ�ȭ �Ͽ���
		Connection conn=null;
		PreparedStatement pstmt=null;
		
	
		
		//try�̿��ִ� ����鿡�� ���ܰ� �߻��ϸ� catch�� �Ѿ��
		try{
			//ip�ּ�,��Ʈ��ȣ,db��,�����id,�н����带 ���� String data type���� ����� ������ ��ƶ�
			String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
			String userid="java";
			String userpw="java0000";
		
			//Driver�ε�
			String dbDriver="com.mysql.jdbc.Driver";
			Class.forName(dbDriver);
			//db����
			//Connection ��ü�� ����,��ü�ּҸ� conn ������ �Ҵ��Ѵ�.
			conn=DriverManager.getConnection(dbname,userid,userpw);
			
			//���� �����غ�
			//conn�ּҸ� ã�ư��� prepareStatement�޼��忡  �Ű������� �������� ������ PreparedStatement��ü������ �ּҸ� pstmt�� �����Ѵ�. 
			pstmt=conn.prepareStatement("insert into employee(employee_name,employee_age) values(?,?)");
			
			//����ǥ�� ������ ����ִ� ������ �����Ѵ�
			pstmt.setString(1, employee.getEmployeeName());
			pstmt.setInt(2, employee.getEmployeeAge());
			
			System.out.println(pstmt+"<--pstmt");
			
			//���� ����
			pstmt.executeUpdate();
			
			System.out.println(pstmt+"<--pstmt");
		
		 //sql�� ���ܰ� �߻��Ͽ� catch�� �ִ� ������� �����Ѵ�.
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		
		 //���ܰ� �߻��ص� �ݵ�� �����Ѵ�
		}catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}finally{
			//��ü ����
			try {pstmt.close();	} catch (SQLException e) {e.printStackTrace();}
			try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
			
		}
	}
	
	//ȸ������ ����
	//ȸ����ȣ�� �˾ƾ� ������ �� �� �ֱ� ������ ȸ����ȣ�� �Ű������� �����Ѵ�
	public void deleteEmployee(String no) {
		Connection connection=null;
		PreparedStatement statement=null;
		PreparedStatement statement1=null;
		
		//ip�ּ�,��Ʈ��ȣ,db��,�����id,�н����带 ���� String data type���� ����� ������ ��ƶ�
		String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
		String userid="java";
		String userpw="java0000";
	
		//Driver�ε�
		String dbDriver="com.mysql.jdbc.Driver";
		
		//employee table���� employee_no��?�� ���� �����Ͽ���
		String sql="delete from employee where employee_no=?";
		
		//employee table���� employee_addr��?�� ���� �����Ͽ���
		String sql1="delete from employee_addr where employee_no=?";
		
		try {
			Class.forName(dbDriver);
			
			connection=DriverManager.getConnection(dbname, userid, userpw);
			
			statement1=connection.prepareStatement(sql1);
			statement1.setString(1, no);
			
			statement1.executeUpdate();
			
			statement=connection.prepareStatement(sql);
			statement.setString(1, no);
		
			statement.executeUpdate();
			
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		
		 //���ܰ� �߻��ص� �ݵ�� �����Ѵ�
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();} catch (SQLException e) {e.printStackTrace();}
		}
	
	}
	
	//ȸ���������� �ּ� �߰�
	//dto�� ������ ����ִ� ���� ����ϱ����ؼ� �Ű������� dto�� �ּҸ� ��´�
	public void insertEmployeeAddr(EmployeeAddr employeeAddr) {
		
		Connection connection=null;
		PreparedStatement statement=null;
		
		//ip�ּ�,��Ʈ��ȣ,db��,�����id,�н����带 ���� String data type���� ����� ������ ��ƶ�
		String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
		String userid="java";
		String userpw="java0000";
	
		//Driver�ε�
		String dbDriver="com.mysql.jdbc.Driver";
		
		//employee_addr table�� Į������ employee_no,employee_addr_content�� ���� ?�� �����Ͽ���
		String sql="insert into employee_addr(employee_no,employee_addr_content) values(?,?);";
	
		try {
			Class.forName(dbDriver);
			
			connection=DriverManager.getConnection(dbname, userid, userpw);
			
			statement=connection.prepareStatement(sql);
			statement.setString(1, employeeAddr.getEmployee_no());
			statement.setString(2, employeeAddr.getEmployee_addr_content());
			
			statement.executeUpdate();
			
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		
		 //���ܰ� �߻��ص� �ݵ�� �����Ѵ�
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();} catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	//ȸ������ �˻�
	//ȸ����ȣ�� �˾ƾ� ȸ�� ������ �˻��ϱ� ������ �Ű������� ȸ�� ��ȣ�� �����Ѵ�.
	public Employee updateSelectEmployee(int no) {
		System.out.println("updateEmployee");
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		Employee employee=new Employee();
		//ip�ּ�,��Ʈ��ȣ,db��,�����id,�н����带 ���� String data type���� ����� ������ ��ƶ�
		String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
		String userid="java";
		String userpw="java0000";
	
		//Driver�ε�
		String dbDriver="com.mysql.jdbc.Driver";
		
		//employee table�� employee_name,employee_age�÷��� employee_no��?�ΰͿ� ���� ��ȸ�Ͽ���
		String sql="select employee_name,employee_age from employee where employee_no=?";
	
		try {
			Class.forName(dbDriver);
			
			connection=DriverManager.getConnection(dbname, userid, userpw);
			
		
			
			statement=connection.prepareStatement(sql);
			statement.setInt(1, no);
			
			resultSet=statement.executeQuery();
			
			while(resultSet.next()) {
				employee.setEmployeeName(resultSet.getString("employee_name"));
				employee.setEmployeeAge(resultSet.getInt("employee_age"));
			}
			
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		
		 //���ܰ� �߻��ص� �ݵ�� �����Ѵ�
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		return employee;
	}
	
	//ȸ������ ����
	//dto�� ������ ����մ� ������ �����ͼ� ������ �ؾ߱� ������ dto �ּҸ� �Ű������� �����Ѵ�.
	public Employee updateEmployee(Employee employee) {

		
		//class data type���� ������ �����ϰ� null�� �ʱ�ȭ �Ͽ���
		Connection connection=null;
		PreparedStatement statement=null;
		
	
		
		//try�̿��ִ� ����鿡�� ���ܰ� �߻��ϸ� catch�� �Ѿ��
		try{
			//ip�ּ�,��Ʈ��ȣ,db��,�����id,�н����带 ���� String data type���� ����� ������ ��ƶ�
			String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
			String userid="java";
			String userpw="java0000";
		
			//Driver�ε�
			String dbDriver="com.mysql.jdbc.Driver";
			String sql="update employee set employee_name=?,employee_age=?  where employee_no=? ";
			Class.forName(dbDriver);
			//db����
			//Connection ��ü�� ����,��ü�ּҸ� conn ������ �Ҵ��Ѵ�.
			connection=DriverManager.getConnection(dbname,userid,userpw);
			
			//���� �����غ�
			//conn�ּҸ� ã�ư��� prepareStatement�޼��忡  �Ű������� �������� ������ PreparedStatement��ü������ �ּҸ� pstmt�� �����Ѵ�. 
			//employee table�� employee_no��?����� employee_name,employee_age�� ���� ���� �����Ѵ�
			statement=connection.prepareStatement(sql);
			
			//����ǥ�� ������ ����ִ� ������ �����Ѵ�
			statement.setString(1, employee.getEmployeeName());
			statement.setInt(2, employee.getEmployeeAge());
			statement.setInt(3, employee.getEmployeeNo());
			
			System.out.println(statement+"<--pstmt");
			
			//���� ����
			statement.executeUpdate();
			
			System.out.println(statement+"<--pstmt");
		
		 //sql�� ���ܰ� �߻��Ͽ� catch�� �ִ� ������� �����Ѵ�.
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		
		 //���ܰ� �߻��ص� �ݵ�� �����Ѵ�
		}catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}finally{
			//��ü ����
			try {statement.close();	} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();	} catch (SQLException e) {e.printStackTrace();}
			
		}
		
		return employee;
	}
}