<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.EmployeeScoreDao" %>
<%@ page import="service.EmployeeAndScore" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<script>
			var resultno=document.getElementById("id");
			id.addEventListener("load", function() {
				resultno.innerHTML="점수를 입력하세요.";
				
			});
		</script>
	</head>
	<body>
	<table border="1">
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>나이</th>
				<th>점수</th>
			</tr>
			<%
				//피라미터값을 받아서 int형 변수 no에 대입한다.
				int no=Integer.parseInt(request.getParameter("no"));
			
				//EmployeeScoreDao객체생성
				EmployeeScoreDao employeeScoreDao=new EmployeeScoreDao();
				
				//employeeScoreDao의 주소를 찾아가서selectEmployeeAndScore메소드의 매개변수에 no값을대입한후  employeeAndScore의 주소값을 리턴받는다
				EmployeeAndScore employeeAndScore=employeeScoreDao.selectEmployeeAndScore(no);
				
				System.out.println(employeeAndScore.getEmployee().getEmployeeNo()+"<---employeeAndScore.getEmployee().getEmployeeNo()");
				
			if(true){	
			%>
					<!--리턴받은 employeeAndScore의 주소값을 찾아가서 메소드를 실행시켜 변수값을 받아온다 -->
					<tr>
						<td><%=employeeAndScore.getEmployee().getEmployeeNo() %></td>
						<td><%=employeeAndScore.getEmployee().getEmployeeName() %></td>
						<td><%=employeeAndScore.getEmployee().getEmployeeAge()%></td>
						<td><%=employeeAndScore.getEmployeescore().getScore() %></td>
					</tr>
			<%
			}else{
			%>
			<div id="resultno"></div>
			<%	
			}
			%>
	</table>
	</body>
</html>