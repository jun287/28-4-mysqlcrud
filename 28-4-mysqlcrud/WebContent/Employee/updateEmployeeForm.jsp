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
			int no=Integer.parseInt(request.getParameter("no"));
			EmployeeDao employeeDao = new EmployeeDao();
			Employee employee=employeeDao.updateSelectEmployee(no);
		%>
		<!-- 회원 가입폼 -->
		<form action="./updateEmployeeAction.jsp?no=<%=no %>" method="post">
			<div id="name">
				이름&nbsp;:&nbsp;
				<input type='text' name="name" value=<%=employee.getEmployeeName() %>>
			</div>
			<div id="age">
				나이&nbsp;:&nbsp;
				<input type='text' name="age" value=<%=employee.getEmployeeAge() %>>
			</div>
			<div>
				<input type="submit" value="수정완료" id="ok">
			</div>
		</form>
	</body>
</html>