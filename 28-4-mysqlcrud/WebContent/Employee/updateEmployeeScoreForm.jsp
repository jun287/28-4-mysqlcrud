<%@page import="EmployeeDTO.EmployeeScore"%>
<%@page import="EmployeeDTO.EmployeeAndScore"%>
<%@page import="EmployeeDAO.EmployeeScoreDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	</head>
	<body>
		<%
			int no=Integer.parseInt(request.getParameter("no"));
			System.out.println(no+"<--no");
			EmployeeScoreDao employeeScoreDao=new EmployeeScoreDao();
			EmployeeScore employeeScore=employeeScoreDao.updateSelectEmployee(no);
			
		%>
			<div id="main" align="center">
				<form action="<%=request.getContextPath()%>/Employee/updateEmployeeScoreAction.jsp?no=<%=no %>" method="post" id="scoreFrom">
					<label>점수</label>
					<input type="text" name="score" id="score" value="<%=employeeScore.getScore()%>">
					<button type="submit" id="btn">점수입력</button>
					<span id="result"></span>
				</form>
			</div>
			
	
	
	</body>
</html>