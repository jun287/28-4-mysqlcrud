<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.PreparedStatement"%>
<%@ page import = "java.sql.SQLException"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertEmployeeAction.jsp</title>
	</head>
	<body>
		<%
			//request로 온 값들을 euckr로 인코딩하여라
			request.setCharacterEncoding("euckr");
		
			//String data type으로 선언된 변수 name,age에 넘어온 파라미터값들을 할당하여라
			String name=request.getParameter("name");
			String age=request.getParameter("age");
			
			//값이 잘받아 왔는지 console 출력
			System.out.println(name+"<--name");
			System.out.println(age+"<--age");
			
			//class data type으로 변수을 선언하고 null로 초기화 하여라
			Connection conn=null;
			PreparedStatement pstmt=null;
			
			
			//ip주소,포트번호,db명,사용자id,패스워드를 각각 String data type으로 선언된 변수에 담아라
			String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
			String userid="java";
			String userpw="java0000";
			
			//Driver로딩
			String dbDriver="com.mysql.jdbc.Driver";
			Class.forName(dbDriver);
			System.out.println(Class.forName(dbDriver)+"<--Class.forName(dbDriver);");
			
			//db연결
			//Connection 객체를 생성,객체주소를 conn 변수에 할당한다.
			conn=DriverManager.getConnection(dbname,userid,userpw);
			
			//try이에있는 문장들에서 예외가 발생하면 catch로 넘어간다
			try{
				//쿼리 실행준비
				//conn주소를 찾아가서 prepareStatement메서드에  매개변수에 쿼리문을 대입후 PreparedStatement객체생성후 주소를 pstmt에 대입한다. 
				pstmt=conn.prepareStatement("insert into employee(empoyee_name,employee_age) values(?,?)");
				
				//물음표에 변수에 담겨있는 값들을 대입한다
				pstmt.setString(1, name);
				pstmt.setString(2, age);
				
				System.out.println(pstmt+"<--pstmt");
				
				//쿼리 실행
				pstmt.executeUpdate();
				
				System.out.println(pstmt+"<--pstmt");
			
			 //sql에 예외가 발생하여 catch에 있는 문장들을 실행한다.
			}catch(SQLException ex) {
				System.out.println("sql 오류가 아닌다.");
				out.println(ex.getMessage());
				ex.printStackTrace();
			
			 //예외가 발생해도 반드시 실행한다
			}finally{
				//객체 종료
				pstmt.close();
				conn.close();
			}
			
			
		%>
	</body>
</html>