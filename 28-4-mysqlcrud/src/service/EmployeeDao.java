// 2018. 06. 25 28�� ���μ�
package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;;

public class EmployeeDao {
	
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