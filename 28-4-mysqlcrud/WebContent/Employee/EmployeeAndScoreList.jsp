<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.EmployeeScoreDao" %>
<%@ page import="service.EmployeeAndScore" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
	<table border="1">
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>나이</th>
				<th>점수</th>
			</tr>
			<%
				int no=Integer.parseInt(request.getParameter("no"));
				EmployeeScoreDao employeeScoreDao=new EmployeeScoreDao();
				
				EmployeeAndScore employeeAndScore=employeeScoreDao.selectEmployeeAndScore(no);
			
			%>
			<tr>
				<td><%=employeeAndScore.getEmployee().getEmployeeNo() %></td>
				<td><%=employeeAndScore.getEmployee().getEmployeeName() %></td>
				<td><%=employeeAndScore.getEmployee().getEmployeeAge()%></td>
				<td><%=employeeAndScore.getEmployeescore().getScore() %></td>
			</tr>
	</table>
	</body>
</html>