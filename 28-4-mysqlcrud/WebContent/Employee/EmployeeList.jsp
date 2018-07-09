<!-- 28�� ���μ� 2018. 7.3(ȭ)insertEmployee.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.EmployeeDao" %>
<%@ page import="service.Employee" %>
<%@ page import="java.util.ArrayList" %>
<% request.setCharacterEncoding("euckr"); %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<form action="./EmployeeList.jsp" method="post">
			<div>
				�̸� :
				<input type="text" name="searchWord">
				<button type="button">�˻�</button>
			</div>
		</form>
		<table border="1">
			<tr>
				<th>��ȣ</th>
				<th>�̸�</th>
				<th>����</th>
				<th>�ּ�</th>
				<th>����</th>
				<th>����</th>
				<th>�����Է�</th>
				<th>��������</th>
			</tr>
			<%
				String word=request.getParameter("searchWord");
				System.out.println(word+"<--word");
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
				ArrayList<Employee> resultList=employeedao.selectEmployeeAll(currentPage,StartRow,word);
				
				//ArryaList�� ����Ǿ��ִ� ������ŭ ����ϰ� dto�ּҸ� ã�ư��� ������ ������ ȭ�鿡 ����Ѵ�
				for(int i=0;i<resultList.size();i++){
					Employee employee=resultList.get(i);
					
			%>
					<tr>
						<td><%=employee.getEmployeeNo() %></td>
						<td><a href="./EmployeeAddrList.jsp"><%=employee.getEmployeeName() %></a></td>
						<td><%=employee.getEmployeeAge() %></td>
						<!--�ּ��Է��� ��������  insertEmployeeAddrForm.jsp,������ �������� deleteEmployee.jsp,������ �������� updateEmployeeForm.jsp�� ���� no������ employee.getEmployeeNo()���� ��� �ѱ��-->
						<td><a href="./insertEmployeeAddrForm.jsp?no=<%=employee.getEmployeeNo() %>">�ּ��Է�</a></td>
						<td><a href="./deleteEmployee.jsp?no=<%=employee.getEmployeeNo() %>">����</a></td>
						<td><a href="./updateEmployeeForm.jsp?no=<%=employee.getEmployeeNo() %>">����</a></td>						
						<td><a href="./insertEmployeeScoreForm.jsp?no=<%=employee.getEmployeeNo() %>">�����Է�</a></td>						
						<td><a href="./EmployeeAndScoreList.jsp?no=<%=employee.getEmployeeNo() %>">��������</a></td>						
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