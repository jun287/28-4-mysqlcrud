<!-- 28기 정민수 2018. 7. 3(화)insertEmployeeAction.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.EmployeeDao" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<%
			//넘어온 파라미터 값들을 String data type을 선언되 변수명 no에 값을 담는다
			String no=request.getParameter("no");
			System.out.println(no+"<--no");
			
			//EmployeeDao class data type으로 변수를 선언하고 생성잠 메서드로 EmployeeDao클래스를 통해서 객체를 생성한다음 주소를 대입한다
			EmployeeDao employeeDao=new EmployeeDao();
			
			//employeeDao주소를 찾아가서 deleteEmployee의 메서드의 매개변수에  no에 담겨있는 값을 대입하고 호출한다
			employeeDao.deleteEmployee(no);
			
			//삭제가 완료되면 EmployeeList로 페이지를 이동한다.
			response.sendRedirect("./EmployeeList.jsp");
		%>
	</body>
</html>