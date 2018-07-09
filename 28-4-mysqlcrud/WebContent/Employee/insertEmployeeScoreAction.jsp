<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.EmployeeScoreDao" %>
<%@ page import="service.EmployeeScore" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<%
			EmployeeScore employeeScore=new EmployeeScore();
			
			employeeScore.setEmployee_no(Integer.parseInt(request.getParameter("no")));
			employeeScore.setScore(Integer.parseInt(request.getParameter("score")));
			
			
			EmployeeScoreDao employeeScoreDao=new EmployeeScoreDao();
			employeeScoreDao.insertScore(employeeScore);
		%>
	</body>
</html>