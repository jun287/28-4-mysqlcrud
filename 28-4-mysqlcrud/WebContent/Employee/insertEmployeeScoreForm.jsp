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
			
			if(result.equals("값이 없습니다.")){
		%>
			<div id="main" align="center">
				<h1>점수를 입력해주세요</h1>
				<form action="<%=request.getContextPath()%>/Employee/insertEmployeeScoreAction.jsp?no=<%=no %>" method="post" id="scoreFrom">
					<label>점수</label>
					<input type="text" name="score" id="score">
					<button type="submit" id="btn">점수입력</button>
					<span id="result"></span>
				</form>
			</div>
			
		<%
			}else{
		%>
				<div id="main" align="center">
					<table>
						<tr>
							<th>번호</th>
							<th>이름</th>
							<th>나이</th>
							<th>점수</th>
						</tr>
						<!--리턴받은 employeeAndScore의 주소값을 찾아가서 메소드를 실행시켜 변수값을 받아온다 -->
						<tr>
							<td><%=employeeAndScore.getEmployee().getEmployeeNo() %></td>
							<td><%=employeeAndScore.getEmployee().getEmployeeName() %></td>
							<td><%=employeeAndScore.getEmployee().getEmployeeAge()%></td>
							<td><%=employeeAndScore.getEmployeescore().getScore() %></td>
							
						</tr>
					</table>
					<div>
						<a href="<%=request.getContextPath()%>/Employee/updateEmployeeScoreForm.jsp?no=<%=employeeAndScore.getEmployee().getEmployeeNo() %>"><button>수정</button></a>
					</div>
					<div>
						<a href="<%=request.getContextPath()%>/Employee/EmployeeList.jsp">리스트로</a>
					</div>
				</div>
		<%
			
			}
		%>
	</body>
</html>