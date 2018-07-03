<!-- 28기 정민수 2018. 7. 3(화)insertEmployeeAction.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.EmployeeDao" %>
<%@ page import="service.Employee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="../style/insertEmployeeForm.css">
	</head>
	<body>
		<%
			//request로 온 값들을 euckr로 인코딩하여라
			request.setCharacterEncoding("euckr");
			int no=Integer.parseInt(request.getParameter("no"));
			String name=request.getParameter("name");
			int age=Integer.parseInt(request.getParameter("age"));
			//dto의 객체를 생성한다
			Employee employee=new Employee();
			
			//employee주소를 찾아가서 set메서드에 form에서 넘어온 파라미터 값들을 매개변수에 대입하고 호출한다
			employee.setEmployeeNo(no);
			employee.setEmployeeName(name);
			employee.setEmployeeAge(age);
			
			EmployeeDao employeeDao=new EmployeeDao();
			employeeDao.updateEmployee(employee);
			
			
			//삭제가 완료되면 EmployeeList로 페이지를 이동한다.
			response.sendRedirect("./EmployeeList.jsp");
		%>
			
	</body>
</html>