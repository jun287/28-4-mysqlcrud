<%@ page import="service.EmployeeScoreDao"%>
<%@ page import="service.EmployeeScore"%>
<%@ page import="service.EmployeeDao"%>
<%@ page import="service.EmployeeAndScore"%>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>점수평균과 평균이상인사람</h1>
		<%
		 	EmployeeScoreDao employeeScoreDao=new EmployeeScoreDao();
			int score=employeeScoreDao.selectScoreAvg();
			System.out.println(score+"<--score");
			
			ArrayList<EmployeeAndScore> employeeAndScore=employeeScoreDao.selectmemberListAboveAvg();
		%>
		<div>
			평균:<%=score %>점
		</div>
		<table border="1">
			<thead>
				<tr>
					<th>no</th>
					<th>name</th>
					<th>score</th>
				</tr>
			</thead>
			<tbody>
				<%
					for(int i=0;i<employeeAndScore.size();i++){
						EmployeeAndScore result=employeeAndScore.get(i);
				%>
					<tr>
						<td><%=result.getEmployee().getEmployeeName() %></td>
						<td><%=result.getEmployee().getEmployeeName() %></td>
						<td><%=result.getEmployeescore().getScore() %></td>
					</tr>
				<%		
					}
				
				%>
			</tbody>
		</table>
	</body>
</html>