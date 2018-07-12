<!-- 28�� ���μ� 2018. 7.3(ȭ)insertEmployee.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import="EmployeeDAO.EmployeeDao" %>
<%@ page import="EmployeeDTO.Employee" %>
<%@ page import="java.util.ArrayList" %>

<% request.setCharacterEncoding("euckr"); %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	</head>
	<body>
		<div align="center">
			<h2>���� ���</h2><br>
			<table>
				<tr>
					<th>��ȣ</th>
					<th>�̸�</th>
					<th>����</th>
					<th>�ּ�</th>
					<th>����</th>
					<th>����</th>
					<th>��������</th>
				</tr>
				<%
					String searchWord=request.getParameter("searchWord");
					System.out.println(searchWord+"<--searchWord");
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
					ArrayList<Employee> resultList=employeedao.selectEmployeeAll(currentPage,StartRow,searchWord);
					
					//ArryaList�� ����Ǿ��ִ� ������ŭ ����ϰ� dto�ּҸ� ã�ư��� ������ ������ ȭ�鿡 ����Ѵ�
					for(int i=0;i<resultList.size();i++){
						Employee employee=resultList.get(i);
						
				%>
						<tr>
							<td><%=employee.getEmployeeNo() %></td>
							<td><a href="<%=request.getContextPath() %>/Employee/EmployeeAddrList.jsp?no=<%=employee.getEmployeeNo() %>"><%=employee.getEmployeeName() %></a></td>
							<td><%=employee.getEmployeeAge() %></td>
							<!--�ּ��Է��� ��������  insertEmployeeAddrForm.jsp,������ �������� deleteEmployee.jsp,������ �������� updateEmployeeForm.jsp�� ���� no������ employee.getEmployeeNo()���� ��� �ѱ��-->
							<td><a href="<%=request.getContextPath() %>/Employee/insertEmployeeAddrForm.jsp?no=<%=employee.getEmployeeNo() %>">�ּ��Է�</a></td>
							<td><a href="<%=request.getContextPath() %>/Employee/deleteEmployee.jsp?no=<%=employee.getEmployeeNo() %>">����</a></td>
							<td><a href="<%=request.getContextPath() %>/Employee/updateEmployeeForm.jsp?no=<%=employee.getEmployeeNo() %>">����</a></td>						
							<td><a href="<%=request.getContextPath() %>/Employee/insertEmployeeScoreForm.jsp?no=<%=employee.getEmployeeNo() %>">��������</a></td>						
						</tr>
				<%
					}
					
				%>
			</table>
			<form action="./EmployeeList.jsp" method="post">
				�̸� :<input type="text" name="searchWord">
					<button type="submit">�˻�</button>
			</form>
		
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
			<a href="<%=request.getContextPath()%>/index.jsp">���</a>
		</div>
	</body>
</html>