<%@ page import="service.EmployeeScoreDao"%>
<%@ page import="service.EmployeeScore"%>
<%@ page import="service.EmployeeDao"%>
<%@ page import="service.EmployeeAndScore"%>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%request.setCharacterEncoding("euckr"); %>
<!DOCTYPE html>
<html>
	<head>
			<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
			<title>Insert title here</title>
	</head>
	<body>
			<h1>������հ� ����̻��λ��</h1>
			
			<%
				//employeeScoreDao��ü ����
			 	EmployeeScoreDao employeeScoreDao=new EmployeeScoreDao();
			
				//employeeScoreDao�� �ּҰ��� ã�ư��� selectScoreAvg �޼ҵ� �����Ͽ� ��հ��� ���Ϲ޾ƿ´�
				int score=employeeScoreDao.selectScoreAvg();
				System.out.println(score+"<--score");
				
				////employeeScoreDao�� �ּҰ��� ã�ư��� selectmemberListAboveAvg �޼ҵ� ���� ��հ��̻��λ������ ArryaList�������� �����Ͽ� ���ϰ��� ������employeeAndScore�� �ּҰ���������
				ArrayList<EmployeeAndScore> employeeAndScore=employeeScoreDao.selectmemberListAboveAvg();
			%>
			
			<div>
				���:<%=score %>��
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
	</body>
</html>