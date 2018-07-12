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
			//employeeScoreDao객체 생성
		 	EmployeeScoreDao employeeScoreDao=new EmployeeScoreDao();
		
			//employeeScoreDao의 주소값을 찾아가서 selectScoreAvg 메소드 실행하여 평균값을 리턴받아온다
			int score=employeeScoreDao.selectScoreAvg();
			System.out.println(score+"<--score");
			
			////employeeScoreDao의 주소값을 찾아가서 selectmemberListAboveAvg 메소드 실행 평균값이상인사람들을 ArryaList에저장후 리턴하여 리턴값을 가져와employeeAndScore에 주소값을저장함
			ArrayList<EmployeeAndScore> employeeAndScore=employeeScoreDao.selectmemberListAboveAvg();
		%>
			<div align="center">
				<h1>점수평균과 평균이상인사람</h1><br>
					평균:<%=score %>점<br>
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
						//ArrayList에 저장되어 있는 값들을 가져와 출력해준다
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