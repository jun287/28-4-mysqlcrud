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
			
			EmployeeScoreDao employeeAndScoreDao=new EmployeeScoreDao();
			EmployeeAndScore employeeAndScore=employeeAndScoreDao.selectEmployeeAndScore(no);
			String result=employeeAndScore.getEmployee().getEmployeeName();
			System.out.println(result+"<--result");
			
			if(result.equals("���� �����ϴ�.")){
		%>
			<div id="main" align="center">
				<h1>������ �Է����ּ���</h1>
				<form action="<%=request.getContextPath()%>/Employee/insertEmployeeScoreAction.jsp?no=<%=no %>" method="post" id="scoreFrom">
					<label>����</label>
					<input type="text" name="score" id="score">
					<button type="submit" id="btn">�����Է�</button>
					<span id="result"></span>
				</form>
			</div>
			
		<%
			}else{
		%>
				<div id="main" align="center">
					<table>
						<tr>
							<th>��ȣ</th>
							<th>�̸�</th>
							<th>����</th>
							<th>����</th>
						</tr>
						<!--���Ϲ��� employeeAndScore�� �ּҰ��� ã�ư��� �޼ҵ带 ������� �������� �޾ƿ´� -->
						<tr>
							<td><%=employeeAndScore.getEmployee().getEmployeeNo() %></td>
							<td><%=employeeAndScore.getEmployee().getEmployeeName() %></td>
							<td><%=employeeAndScore.getEmployee().getEmployeeAge()%></td>
							<td><%=employeeAndScore.getEmployeescore().getScore() %></td>
							
						</tr>
					</table>
					<div>
						<a href="<%=request.getContextPath()%>/Employee/updateEmployeeScoreForm.jsp?no=<%=employeeAndScore.getEmployee().getEmployeeNo() %>"><button>����</button></a>
					</div>
					<div>
						<a href="<%=request.getContextPath()%>/Employee/EmployeeList.jsp">����Ʈ��</a>
					</div>
				</div>
		<%
			
			}
		%>
	</body>
</html>