<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import="EmployeeDAO.EmployeeScoreDao"%>
<%@ page import="EmployeeDTO.EmployeeScore"%>
<%@ page import="EmployeeDAO.EmployeeDao"%>
<%@ page import="EmployeeDTO.EmployeeAndScore"%>
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
			
			//현재 페이지
			int currentPage;
			//만약 현재 페이지에 값이 있으면 받아온 값을 currentpage에 다시 대입하고 없으면 currentPage에 1을 대입한다
			if(request.getParameter("currentPage")!=null){
				currentPage=Integer.parseInt(request.getParameter("currentPage"));
			}else{
				currentPage=1;
			}
			int startRow=5;
			
			////employeeScoreDao의 주소값을 찾아가서 selectmemberListAboveAvg 메소드 실행 평균값이상인사람들을 ArryaList에저장후 리턴하여 리턴값을 가져와employeeAndScore에 주소값을저장함
			ArrayList<EmployeeAndScore> employeeAndScore=employeeScoreDao.selectmemberListAboveAvg(currentPage,startRow);
			
			
		%>
			<div id="main" align="center">
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
					
				<%
					//employeedao주소를 찾아가서 paging메서드에 StartRow값을 대입후 호출한다
					int total=employeeScoreDao.paging(startRow);
				
					//현재페이지가 1보다크면 이전이 있고 1이랑 같거나 작으면  이전이 없다
					if(currentPage>1){
				%>
						<a href="<%=request.getContextPath() %>/Employee/EmployeeListAboveAvg.jsp?currentPage=<%=currentPage-1%>">이전</a>
				<%
					}
					//currentPage가 총 페이지보다  작으면 실행된다 
					if(currentPage<total){
				%>
						<a href="<%=request.getContextPath() %>/Employee/EmployeeListAboveAvg.jsp?currentPage=<%=currentPage+1%>">다음</a>
				<%
					}
				%>
				<a href="<%=request.getContextPath()%>/index.jsp">목록으로...</a>
			</div>
	</body>
</html>