// 2018. 06. 25 28기 정민수
package EmployeeDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DBConnection.DBconnection;
import EmployeeDTO.Employee;
import EmployeeDTO.EmployeeAddr;;

public class EmployeeDao {

	
	//db에 있는 회원 정보를 가져오기위한 메스드 
	//매개변수에는  현재  페이지와 몇개를 출력할것인지나타내는 매개변수를 대입한다
	public ArrayList<Employee> selectEmployeeAll(int currentPage,int StartRow,String searchWord,String order){
		
		System.out.println("selectEmployeeAll()");
		
		ArrayList<Employee> list=new ArrayList<Employee>();
	
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultset=null;
		
		int start=(currentPage-1)*StartRow;
		
		try {
			DBconnection dbConnection=new DBconnection();
			connection=dbConnection.getConnection();
			//쿼리 실행준비
			if(searchWord=="") {
				if(order=="asc") {
					statement=connection.prepareStatement("SELECT employee_no,employee_name,employee_age FROM Employee ORDER BY employee_age asc LIMIT ?,?");
					statement.setInt(1, start);
					statement.setInt(2, StartRow);
					System.out.println("NOSearch");
				}else if(order=="desc") {
					statement=connection.prepareStatement("SELECT employee_no,employee_name,employee_age FROM Employee ORDER BY employee_age desc LIMIT ?,?");
					statement.setInt(1, start);
					statement.setInt(2, StartRow);
					System.out.println("desc");
				}else {
					statement=connection.prepareStatement("SELECT employee_no,employee_name,employee_age FROM Employee ORDER BY employee_no asc LIMIT ?,?");
					statement.setInt(1, start);
					statement.setInt(2, StartRow);
					System.out.println("asc");
				}
			}else {
				if(order=="asc") {
					statement=connection.prepareStatement("SELECT employee_no,employee_name,employee_age FROM Employee where employee_name like ? order by employee_age asc LIMIT ?,?");
					statement.setString(1, "%"+searchWord+"%");
					statement.setInt(2, start);
					statement.setInt(3, StartRow);
					System.out.println("yesSearch");
				}else if(order=="desc"){
					statement=connection.prepareStatement("SELECT employee_no,employee_name,employee_age FROM Employee where employee_name like ? order by employee_age desc LIMIT ?,?");
					statement.setString(1, "%"+searchWord+"%");
					statement.setInt(2, start);
					statement.setInt(3, StartRow);
					System.out.println("yesSearch");
				}else {
					statement=connection.prepareStatement("SELECT employee_no,employee_name,employee_age FROM Employee where employee_name like ? order by employee_no desc LIMIT ?,?");
					statement.setString(1, "%"+searchWord+"%");
					statement.setInt(2, start);
					statement.setInt(3, StartRow);
					System.out.println("yesSearch");
				}
			}
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
			
		}catch (SQLException e) {
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
	
	public int paging(int StartRow,String searchWord) {
		
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultset=null;
		
		//employee table에 있는 갯수를 조회하여라
		String sql="select count(*) from employee";
		
		//employee_score table에 있는 갯수를 조회하여라
		String sql1="select count(*) from employee where employee_name like ?";
		

		//총 몇페이지인지로 할건지 total변수를 선언하고 값을 0으로 초기화한다
		int total=0;
		
		try {
			
			
			//db연결
			DBconnection dbConnection=new DBconnection();
			connection=dbConnection.getConnection();
			
			//쿼리 실행 준비
			if(searchWord=="") {
				statement=connection.prepareStatement(sql);
				System.out.println("paging");
			}else {
				statement=connection.prepareStatement(sql1);
				statement.setString(1, '%'+searchWord+'%');
				System.out.println("Likepaging");
			}
			
			//쿼리 실행
			resultset=statement.executeQuery();
			
			
			//쿼리 실행해서 조회한 결과 가있으면 실행문을 실행
			if(resultset.next()){
				// 총갯수에 출력할 갯수를 나눠서 0이면 total에 나눈 값을 대입하고 아니면 나눈값에 1을 더해서 대입한다
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
	
	//회원 정보를 db에다가 저장하기위한 메서드
	public void insertEmployee(Employee employee) {

		
		
		//class data type으로 변수을 선언하고 null로 초기화 하여라
		Connection connection=null;
		PreparedStatement statment=null;
		
	
		
		//try이에있는 문장들에서 예외가 발생하면 catch로 넘어간다
		try{
			//db연결
			DBconnection dbConnection=new DBconnection();
			connection=dbConnection.getConnection();
			
			//쿼리 실행준비
			//conn주소를 찾아가서 prepareStatement메서드에  매개변수에 쿼리문을 대입후 PreparedStatement객체생성후 주소를 pstmt에 대입한다. 
			statment=connection.prepareStatement("insert into employee(employee_name,employee_age) values(?,?)");
			
			//물음표에 변수에 담겨있는 값들을 대입한다
			statment.setString(1, employee.getEmployeeName());
			statment.setInt(2, employee.getEmployeeAge());
			
			System.out.println(statment+"<--pstmt");
			
			//쿼리 실행
			statment.executeUpdate();
			
			System.out.println(statment+"<--pstmt");
		
		 //sql에 예외가 발생하여 catch에 있는 문장들을 실행한다.
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		
		 //예외가 발생해도 반드시 실행한다
		}finally{
			//객체 종료
			try {statment.close();	} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();	} catch (SQLException e) {e.printStackTrace();}
			
		}
	}
	
	//회웝정보 삭제
	//회원번호를 알아야 삭제를 할 수 있기 때문에 회원번호를 매개변수에 대입한다
	public void deleteEmployee(String no) {
		Connection connection=null;
		PreparedStatement statement=null;
		PreparedStatement statement1=null;
		PreparedStatement statement2=null;
		
		
		//employee table에서 employee_no가?인 행을 삭제하여라
		String sql="delete from employee where employee_no=?";
		
		//employee table에서 employee_addr가?인 행을 삭제하여라
		String sql1="delete from employee_addr where employee_no=?";
		
		String sql2="delete from employee_score where employee_no=?";
		
		try {
			//db연결
			DBconnection dbConnection=new DBconnection();
			connection=dbConnection.getConnection();
			
			statement2=connection.prepareStatement(sql2);
			statement2.setString(1, no);
			
			statement2.executeUpdate();
			
			statement1=connection.prepareStatement(sql1);
			statement1.setString(1, no);
			
			statement1.executeUpdate();
			
			statement=connection.prepareStatement(sql);
			statement.setString(1, no);
		
			statement.executeUpdate();
			
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		
		 //예외가 발생해도 반드시 실행한다
		}finally {
			try {statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();} catch (SQLException e) {e.printStackTrace();}
		}
	
	}
	
	
	//db에서 주소데이터를 검색하는메소드
	//매개변수개 EmployeeAddrDto를 대입하여 주소 정보들을 담는다.
	public ArrayList<EmployeeAddr> selectEmployeeAddr(int no) {
		System.out.println("selectEmployeeAddr");
		
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		
		ArrayList<EmployeeAddr> arrayList=new ArrayList<EmployeeAddr>();
		
		
		//employee_addr table에 있는 employee_addr_content컬럼을 조회하여라 employee_no가?일때
		String sql="select employee_addr_content from employee_addr where employee_no=?";

		
		try {
			//db연결
			DBconnection dbConnection=new DBconnection();
			connection=dbConnection.getConnection();
			
			//쿼리실행준비
			statement=connection.prepareStatement(sql);
			statement.setInt(1, no);
			//쿼리실행
			resultSet=statement.executeQuery();
			
			while(resultSet.next()) {
				EmployeeAddr employeeAdder=new EmployeeAddr();
				employeeAdder.setEmployee_addr_content(resultSet.getString("employee_addr_content"));
				
				arrayList.add(employeeAdder);
			}
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		
		 //예외가 발생해도 반드시 실행한다
		}finally {
			try {resultSet.close();} catch (SQLException e) {e.printStackTrace();}
			try {statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();} catch (SQLException e) {e.printStackTrace();}
		}
	
	
	
		return arrayList;
	}
	
	//회원정보에서 주소 추가
	//dto의 변수에 담겨있는 값을 사용하기위해서 매개변수에 dto의 주소를 담는다
	public void insertEmployeeAddr(EmployeeAddr employeeAddr) {
		
		Connection connection=null;
		PreparedStatement statement=null;
		
		//employee_addr table의 칼럼명이 employee_no,employee_addr_content인 곳에 ?를 대입하여라
		String sql="insert into employee_addr(employee_no,employee_addr_content) values(?,?);";
	
		try {
			//db연결
			DBconnection dbConnection=new DBconnection();
			connection=dbConnection.getConnection();
			
			statement=connection.prepareStatement(sql);
			statement.setString(1, employeeAddr.getEmployee_no());
			statement.setString(2, employeeAddr.getEmployee_addr_content());
			
			statement.executeUpdate();
			
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		
		 //예외가 발생해도 반드시 실행한다
		}finally {
			try {statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();} catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	//회원정보 검색
	//회원번호를 알아야 회원 정보를 검색하기 때문에 매개변수에 회원 번호르 대입한다.
	public Employee updateSelectEmployee(int no) {
		System.out.println("updateEmployee");
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		Employee employee=new Employee();
		
		
		//employee table의 employee_name,employee_age컬럼을 employee_no가?인것에 대해 조회하여라
		String sql="select employee_name,employee_age from employee where employee_no=?";
	
		try {
			//db연결
			DBconnection dbConnection=new DBconnection();
			connection=dbConnection.getConnection();
			
		
			
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
		
		 //예외가 발생해도 반드시 실행한다
		}finally {
			try {statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		return employee;
	}
	
	//회원정보 수정
	//dto의 변수에 담겨잇는 값들을 가져와서 수정을 해야기 때문에 dto 주소를 매개변수에 대입한다.
	public Employee updateEmployee(Employee employee) {

		
		//class data type으로 변수을 선언하고 null로 초기화 하여라
		Connection connection=null;
		PreparedStatement statement=null;
		
		String sql="update employee set employee_name=?,employee_age=?  where employee_no=? ";
		
		//try이에있는 문장들에서 예외가 발생하면 catch로 넘어간다
		try{
		
			
			//db연결
			DBconnection dbConnection=new DBconnection();
			connection=dbConnection.getConnection();
			
			//쿼리 실행준비
			//conn주소를 찾아가서 prepareStatement메서드에  매개변수에 쿼리문을 대입후 PreparedStatement객체생성후 주소를 pstmt에 대입한다. 
			//employee table에 employee_no가?인행ㅔ employee_name,employee_age에 각각 값는 수정한다
			statement=connection.prepareStatement(sql);
			
			//물음표에 변수에 담겨있는 값들을 대입한다
			statement.setString(1, employee.getEmployeeName());
			statement.setInt(2, employee.getEmployeeAge());
			statement.setInt(3, employee.getEmployeeNo());
			
			System.out.println(statement+"<--pstmt");
			
			//쿼리 실행
			statement.executeUpdate();
			
			System.out.println(statement+"<--pstmt");
		
		 //sql에 예외가 발생하여 catch에 있는 문장들을 실행한다.
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		
		 //예외가 발생해도 반드시 실행한다
		}finally{
			//객체 종료
			try {statement.close();	} catch (SQLException e) {e.printStackTrace();}
			try {connection.close();	} catch (SQLException e) {e.printStackTrace();}
			
		}
		
		return employee;
	}
}