package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;;

public class EmployeeDao {
	
	public void insertEmployee(Employee employee) {
		
		//class data type으로 변수을 선언하고 null로 초기화 하여라
		Connection conn=null;
		PreparedStatement pstmt=null;
		
	
		
		//try이에있는 문장들에서 예외가 발생하면 catch로 넘어간다
		try{
			//ip주소,포트번호,db명,사용자id,패스워드를 각각 String data type으로 선언된 변수에 담아라
			String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
			String userid="java";
			String userpw="java0000";
		
			//Driver로딩
			String dbDriver="com.mysql.jdbc.Driver";
			Class.forName(dbDriver);
			//db연결
			//Connection 객체를 생성,객체주소를 conn 변수에 할당한다.
			conn=DriverManager.getConnection(dbname,userid,userpw);
			
			//쿼리 실행준비
			//conn주소를 찾아가서 prepareStatement메서드에  매개변수에 쿼리문을 대입후 PreparedStatement객체생성후 주소를 pstmt에 대입한다. 
			pstmt=conn.prepareStatement("insert into employee(employee_name,employee_age) values(?,?)");
			
			//물음표에 변수에 담겨있는 값들을 대입한다
			pstmt.setString(1, employee.getEmployeeName());
			pstmt.setInt(2, employee.getEmployeeAge());
			
			System.out.println(pstmt+"<--pstmt");
			
			//쿼리 실행
			pstmt.executeUpdate();
			
			System.out.println(pstmt+"<--pstmt");
		
		 //sql에 예외가 발생하여 catch에 있는 문장들을 실행한다.
		}catch(SQLException ex) {
			System.out.println("sql 오류가 아닌다.");
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		
		 //예외가 발생해도 반드시 실행한다
		}catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}finally{
			//객체 종료
			try {pstmt.close();	} catch (SQLException e) {e.printStackTrace();}
			try {conn.close();	} catch (SQLException e) {e.printStackTrace();}
			
		}
	}
}
