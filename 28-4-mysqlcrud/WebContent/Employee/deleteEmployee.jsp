<!-- 28�� ���μ� 2018. 7. 3(ȭ)insertEmployeeAction.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.EmployeeDao" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<%
			//�Ѿ�� �Ķ���� ������ String data type�� ����� ������ no�� ���� ��´�
			String no=request.getParameter("no");
			System.out.println(no+"<--no");
			
			//EmployeeDao class data type���� ������ �����ϰ� ������ �޼���� EmployeeDaoŬ������ ���ؼ� ��ü�� �����Ѵ��� �ּҸ� �����Ѵ�
			EmployeeDao employeeDao=new EmployeeDao();
			
			//employeeDao�ּҸ� ã�ư��� deleteEmployee�� �޼����� �Ű�������  no�� ����ִ� ���� �����ϰ� ȣ���Ѵ�
			employeeDao.deleteEmployee(no);
			
			//������ �Ϸ�Ǹ� EmployeeList�� �������� �̵��Ѵ�.
			response.sendRedirect("./EmployeeList.jsp");
		%>
	</body>
</html>