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
				<th>��ȣ</th>
				<th>�̸�</th>
				<th>����</th>
				<th>����</th>
			</tr>
			<%
				//�Ƕ���Ͱ��� �޾Ƽ� int�� ���� no�� �����Ѵ�.
				int no=Integer.parseInt(request.getParameter("no"));
			
				//EmployeeScoreDao��ü����
				EmployeeScoreDao employeeScoreDao=new EmployeeScoreDao();
				
				//employeeScoreDao�� �ּҸ� ã�ư���selectEmployeeAndScore�޼ҵ��� �Ű������� no������������  employeeAndScore�� �ּҰ��� ���Ϲ޴´�
				EmployeeAndScore employeeAndScore=employeeScoreDao.selectEmployeeAndScore(no);
			
			%>
			<!--���Ϲ��� employeeAndScore�� �ּҰ��� ã�ư��� �޼ҵ带 ������� �������� �޾ƿ´� -->
			<tr>
				<td><%=employeeAndScore.getEmployee().getEmployeeNo() %></td>
				<td><%=employeeAndScore.getEmployee().getEmployeeName() %></td>
				<td><%=employeeAndScore.getEmployee().getEmployeeAge()%></td>
				<td><%=employeeAndScore.getEmployeescore().getScore() %></td>
			</tr>
	</table>
	</body>
</html>