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
			//employeeScoreDao��ü ����
		 	EmployeeScoreDao employeeScoreDao=new EmployeeScoreDao();
		
			//employeeScoreDao�� �ּҰ��� ã�ư��� selectScoreAvg �޼ҵ� �����Ͽ� ��հ��� ���Ϲ޾ƿ´�
			int score=employeeScoreDao.selectScoreAvg();
			System.out.println(score+"<--score");
			
			//���� ������
			int currentPage;
			//���� ���� �������� ���� ������ �޾ƿ� ���� currentpage�� �ٽ� �����ϰ� ������ currentPage�� 1�� �����Ѵ�
			if(request.getParameter("currentPage")!=null){
				currentPage=Integer.parseInt(request.getParameter("currentPage"));
			}else{
				currentPage=1;
			}
			int startRow=5;
			
			////employeeScoreDao�� �ּҰ��� ã�ư��� selectmemberListAboveAvg �޼ҵ� ���� ��հ��̻��λ������ ArryaList�������� �����Ͽ� ���ϰ��� ������employeeAndScore�� �ּҰ���������
			ArrayList<EmployeeAndScore> employeeAndScore=employeeScoreDao.selectmemberListAboveAvg(currentPage,startRow);
			
			
		%>
			<div id="main" align="center">
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
					
				<%
					//employeedao�ּҸ� ã�ư��� paging�޼��忡 StartRow���� ������ ȣ���Ѵ�
					int total=employeeScoreDao.paging(startRow);
				
					//������������ 1����ũ�� ������ �ְ� 1�̶� ���ų� ������  ������ ����
					if(currentPage>1){
				%>
						<a href="<%=request.getContextPath() %>/Employee/EmployeeListAboveAvg.jsp?currentPage=<%=currentPage-1%>">����</a>
				<%
					}
					//currentPage�� �� ����������  ������ ����ȴ� 
					if(currentPage<total){
				%>
						<a href="<%=request.getContextPath() %>/Employee/EmployeeListAboveAvg.jsp?currentPage=<%=currentPage+1%>">����</a>
				<%
					}
				%>
				<a href="<%=request.getContextPath()%>/index.jsp">�������...</a>
			</div>
	</body>
</html>