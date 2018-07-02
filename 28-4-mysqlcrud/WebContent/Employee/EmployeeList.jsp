<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.EmployeeDao" %>
<%@ page import="service.Employee" %>
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<table border="1">
			<tr>
				<th>��ȣ</th>
				<th>�̸�</th>
				<th>����</th>
			</tr>
			<%
				//ȭ�鿡 ��� ����Ұ��� ������ ������ ������
				int StartRow=5;
			
				//���� ������
				int currentPage;
				//���� ���� �������� ���� ������ �޾ƿ� ���� currentpage�� �ٽ� �����ϰ� ������ currentPage�� 1�� �����Ѵ�
				if(request.getParameter("currentPage")!=null){
					currentPage=Integer.parseInt(request.getParameter("currentPage"));
				}else{
					currentPage=1;
				}
				System.out.println(currentPage+"<--currentPage");
				
				EmployeeDao employeedao=new EmployeeDao();
				//employee�� �ּҸ� ã�ư��� �޼��忡 ���������� ���� ��� ����� �������� �Ű������� �����ϰ� ȣ���Ͽ� �ּҰ��� resultList�� �����Ѵ�
				ArrayList<Employee> resultList=employeedao.selectEmployeeAll(currentPage,StartRow);
				
				//ArryaList�� ����Ǿ��ִ� ������ŭ ����ϰ� dto�ּҸ� ã�ư��� ������ ������ ȭ�鿡 ����Ѵ�
				for(int i=0;i<resultList.size();i++){
					Employee employee=resultList.get(i);
					
			%>
					<tr>
						<td><%=employee.getEmployeeNo() %></td>
						<td><%=employee.getEmployeeName() %></td>
						<td><%=employee.getEmployeeAge() %></td>
					</tr>
			<%
				}
				
			%>
		</table>
		<%
			//employeedao�ּҸ� ã�ư��� paging�޼��忡 StartRow���� ������ ȣ���Ѵ�
			int total=employeedao.paging(StartRow);
		
			//������������ 1����ũ�� ������ �ְ� 1�̶� ���ų� ������  ������ ����
			if(currentPage>1){
		%>
				<a href="./EmployeeList.jsp?currentPage=<%=currentPage-1%>">����</a>
		<%
			}
			//currentPage�� �� ����������  ������ ����ȴ� 
			if(currentPage<total){
		%>
				<a href="./EmployeeList.jsp?currentPage=<%=currentPage+1%>">����</a>
		<%
			}
		%>
	</body>
</html>