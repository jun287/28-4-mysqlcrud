<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.EmployeeAddr" %>
<%@ page import="java.util.ArrayList" %>
<%@page import="service.EmployeeDao"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<%
			//Employeeaddrdto
			EmployeeDao employeeDao=new EmployeeDao();
	
			int no=Integer.parseInt(request.getParameter("no"));
			ArrayList<EmployeeAddr> resultList=employeeDao.selectEmployeeAddr(no);
		%>
		
	
			<!--���̺� �����  -->
			<table border="1">
				<tr>
					<th>�ּ�</th>
				</tr>
				<%
					for(int i=0;i<resultList.size();i++){
						EmployeeAddr employeeAdder=resultList.get(i);
				%>
					<tr>
						<td><%=employeeAdder.getEmployee_addr_content() %></td>
					</tr>
				<%
					}
				%>
			</table>
			
			<a href="<%=request.getContextPath()%>/Employee/EmployeeList.jsp">������������̵�..</a>
			<!--select�ؼ� �� �ѷ��ֱ�  -->
	</body>
</html>