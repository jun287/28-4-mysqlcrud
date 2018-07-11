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
		
					<%
						//피라미터값을 받아서 int형 변수 no에 대입한다.
						int no=Integer.parseInt(request.getParameter("no"));
					
						//EmployeeScoreDao객체생성
						EmployeeScoreDao employeeScoreDao=new EmployeeScoreDao();
						
						//employeeScoreDao의 주소를 찾아가서selectEmployeeAndScore메소드의 매개변수에 no값을대입한후  employeeAndScore의 주소값을 리턴받는다
						EmployeeAndScore employeeAndScore=employeeScoreDao.selectEmployeeAndScore(no);
						
						//만약 selectEmployeeAndScore에서 조회 하여 값이 없으면 getEmployeeName (값이 없습니다.)저장되는데  만약 조회가된다면 if문안에 실행문 실행 아니면 점수를 입력해주세요 실행
						if(employeeAndScore.getEmployee().getEmployeeName()!="값이 없습니다."){
					%>
							<table border="1">
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
					<%
						//만약 에러가 발생하면 값이 없어서 예외가 발생하기 때문에 값이 없다라는 것을 자바스크립트로 넣어준다.
						}else{
					%>
						<h1>점수를 입력해주세요</h1>
						<a href="<%=request.getContextPath()%>/Employee/EmployeeList.jsp">돌아가기</a>
					<%
						}
					%>
			
	</body>
</html>