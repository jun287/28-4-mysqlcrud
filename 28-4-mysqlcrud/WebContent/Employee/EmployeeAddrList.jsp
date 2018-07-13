<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import="EmployeeDTO.EmployeeAddr" %>
<%@ page import="java.util.ArrayList" %>
<%@page import="EmployeeDAO.EmployeeDao"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	</head>
	<body>
		<%
			//Employeeaddrdto
			EmployeeDao employeeDao=new EmployeeDao();
	
			int no=Integer.parseInt(request.getParameter("no"));
			ArrayList<EmployeeAddr> resultList=employeeDao.selectEmployeeAddr(no);
		%>
		
			<div id="main" align="center">
				<!--테이블 만들기  -->
				<table>
					<tr>
						<th>주소</th>
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
				
				<a href="<%=request.getContextPath()%>/Employee/EmployeeList.jsp">직원목록으로이동..</a>
				<!--select해서 값 뿌려주기  -->
			</div>
	</body>
</html>