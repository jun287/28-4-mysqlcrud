<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import="service.EmployeeScoreDao"%>
<%@ page import="service.EmployeeScore"%>
<%@ page import="service.EmployeeDao"%>
<%@ page import="service.EmployeeAndScore"%>
<%@ page import="java.util.ArrayList" %>

<%request.setCharacterEncoding("euckr"); %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	</head>
	<body>
		<%
			//employeeScoreDao��ü ����
		 	EmployeeScoreDao employeeScoreDao=new EmployeeScoreDao();
		
			//employeeScoreDao�� �ּҰ��� ã�ư��� selectScoreAvg �޼ҵ� �����Ͽ� ��հ��� ���Ϲ޾ƿ´�
			int score=employeeScoreDao.selectScoreAvg();
			System.out.println(score+"<--score");
			
			////employeeScoreDao�� �ּҰ��� ã�ư��� selectmemberListAboveAvg �޼ҵ� ���� ��հ��̻��λ������ ArryaList�������� �����Ͽ� ���ϰ��� ������employeeAndScore�� �ּҰ���������
			ArrayList<EmployeeAndScore> employeeAndScore=employeeScoreDao.selectmemberListAboveAvg();
		%>
			<div align="center">
				<h1>������հ� ����̻��λ��</h1><br>
					���:<%=score %>��<br>
				<table>
					<thead>
						<tr>
							<th>no</th>
							<th>name</th>
							<th>score</th>
						</tr>
					</thead>
					<tbody>
					<%
						//ArrayList�� ����Ǿ� �ִ� ������ ������ ������ش�
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
			</div>
	</body>
</html>