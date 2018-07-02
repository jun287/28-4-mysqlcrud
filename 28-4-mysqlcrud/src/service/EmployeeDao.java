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
	public ArrayList<Employee> selectEmployeeAll(int currentPage,int StartRow){
		
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
		String sql="SELECT employee_no,employee_name,employee_age FROM Employee ORDER BY employee_no asc LIMIT ?,?";
		
		int start=(currentPage-1)*StartRow;
		
		try {
			//driver �ε�
			Class.forName(dbDriver);
			
			//db����
			connection=DriverManager.getConnection(dbname, userid, userpw);
			
			//���� �����غ�
			statement=connection.prepareStatement(sql);
			statement.setInt(1, start);
			statement.setInt(2, StartRow);
			
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
			System.out.println("sql ������ �ƴѴ�.");
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
}