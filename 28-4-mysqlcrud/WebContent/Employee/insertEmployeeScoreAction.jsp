<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="EmployeeDAO.EmployeeScoreDao" %>
<%@ page import="EmployeeDTO.EmployeeScore" %>
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
			
			System.out.println(employeeScore.getEmployee_no()+"<--employeeScore.getEmployee_no()");
			System.out.println(employeeScore.getScore()+"<--employeeScore.getEmployee_no()");
			
			//EmployeeScoreDao��ü ������ insertScore�޼��忡employeeScore�ּҰ��� �����Ͽ� db�� ����
			EmployeeScoreDao employeeScoreDao=new EmployeeScoreDao();
			employeeScoreDao.insertScore(employeeScore);
			
			response.sendRedirect(request.getContextPath()+"/Employee/EmployeeList.jsp");
		%>
	</body>
</html>