<%@page import="EmployeeDAO.EmployeeScoreDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<%
			int no=Integer.parseInt(request.getParameter("no"));
			int score=Integer.parseInt(request.getParameter("score"));
			EmployeeScoreDao employeeScoreDao=new EmployeeScoreDao();
			employeeScoreDao.updateEmployeeScore(no, score);
			
			response.sendRedirect(request.getContextPath()+"/Employee/insertEmployeeScoreForm.jsp?no="+no);
		%>
	</body>
</html>