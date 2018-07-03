<!-- 28기 정민수 2018. 6. 25(월)insertEmployeeAction.jsp -->
<%@page import="service.EmployeeDao"%>
<%@page import="service.Employee"%>
<%@page import="service.EmployeeAddr"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>


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
			
			//dto의 객체를 생성한다
			Employee employee=new Employee();
			
			//employee주소를 찾아가서 set메서드에 form에서 넘어온 파라미터 값들을 매개변수에 대입하고 호출한다
			employee.setEmployeeName(request.getParameter("name"));
			employee.setEmployeeAge(Integer.parseInt(request.getParameter("age")));
			
			EmployeeAddr employeeAddr =new EmployeeAddr();
			
			employeeAddr.setEmployee_addr_content(request.getParameter("addr"));

			//dao의 객체를 생성한다
			EmployeeDao employeedao=new EmployeeDao();
			
			//employeedao의 주소값을 찾아가서 메서드 insertEmployee매개변수에 emplyee주소를 대입한후 호출한다.
			employeedao.insertEmployee(employee);
			
			//삭제가 완료되면 EmployeeList로 페이지를 이동한다.
			response.sendRedirect("./EmployeeList.jsp");
		%>
	</body>
</html>