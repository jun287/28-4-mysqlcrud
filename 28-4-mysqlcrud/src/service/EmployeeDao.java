// 2018. 06. 25 28기 정민수
package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;;

public class EmployeeDao {
	
	//db에 있는 회원 정보를 가져오기위한 메스드 
	//매개변수에는  현재  페이지와 몇개를 출력할것인지나타내는 매개변수를 대입한다
	public ArrayList<Employee> selectEmployeeAll(int currentPage,int StartRow){
		
		System.out.println("selectEmployeeAll()");
		
		ArrayList<Employee> list=new ArrayList<Employee>();
	
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultset=null;
		
		//ip주소,포트번호,db명,사용자id,패스워드를 각각 String data type으로 선언된 변수에 담아라
		String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
		String userid="java";
		String userpw="java0000";
	
		//Driver로딩
		String dbDriver="com.mysql.jdbc.Driver";
		
		//Employee table을 오름차순으로 ?부터 ?개를 employee_no,employee_name,employee_age 조회하여라
		String sql="SELECT employee_no,employee_name,employee_age FROM Employee ORDER BY employee_no asc LIMIT ?,?";
		
		int start=(currentPage-1)*StartRow;
		
		try {
			//driver 로딩
			Class.forName(dbDriver);
			
			//db연결
			connection=DriverManager.getConnection(dbname, userid, userpw);
			
			//쿼리 실행준비
			statement=connection.prepareStatement(sql);
			statement.setInt(1, start);
			statement.setInt(2, StartRow);
			
			//쿼리실행
			resultset=statement.executeQuery();
			
			//회원 정보가 있으면 dto에 데이터를 셋팅하고 dto의 주소를 list에 저장한다
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
		*List: 갯수가 동전,list.size()<--list의 갯수
		*배열: 갯수가 정적,배열.length<--배열의 갯수,
		resultset으로 return 해줄수 있지만 여기서 완전 끝내고 넘겨줘야지 종속되지 않는다.
		*/	
		return list;
	}
	
	//페이징 작업
	public int paging(int StartRow) {
		
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultset=null;
		//ip주소,포트번호,db명,사용자id,패스워드를 각각 String data type으로 선언된 변수에 담아라
		String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
		String userid="java";
		String userpw="java0000";
	
		//Driver로딩
		String dbDriver="com.mysql.jdbc.Driver";
		
		//employee table에 있는 갯수를 조회하여라
		String sql="select count(*) from employee";
		
		int total=0;
		
		try {
			//드라이버 로딩
			Class.forName(dbDriver);
			
			//db연결
			connection=DriverManager.getConnection(dbname, userid, userpw);
			//쿼리 실행 준비
			statement=connection.prepareStatement(sql);
			//쿼리 실행
			resultset=statement.executeQuery();
			
			//총 몇페이지인지로 할건지 total변수를 선언하고 값을 0으로 초기화한다
			
			
			//쿼리 실행해서 조회한 결과 가있으면 실행문을 실행
			if(resultset.next()){
				// 총갯수에 출력할 갯수를 나눠서 0이면 total에 나눈 값을 대입하고 아니면 나눈값에 1을 더해서 대입한다
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
	
	//회원 정보를 db에다가 저장하기위한 메서드
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