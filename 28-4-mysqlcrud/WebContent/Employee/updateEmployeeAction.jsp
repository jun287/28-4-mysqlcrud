<!-- 28�� ���μ� 2018. 7. 3(ȭ)insertEmployeeAction.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.EmployeeDao" %>
<%@ page import="service.Employee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="../style/insertEmployeeForm.css">
	</head>
	<body>
		<%
			//request�� �� ������ euckr�� ���ڵ��Ͽ���
			request.setCharacterEncoding("euckr");
			int no=Integer.parseInt(request.getParameter("no"));
			String name=request.getParameter("name");
			int age=Integer.parseInt(request.getParameter("age"));
			//dto�� ��ü�� �����Ѵ�
			Employee employee=new Employee();
			
			//employee�ּҸ� ã�ư��� set�޼��忡 form���� �Ѿ�� �Ķ���� ������ �Ű������� �����ϰ� ȣ���Ѵ�
			employee.setEmployeeNo(no);
			employee.setEmployeeName(name);
			employee.setEmployeeAge(age);
			
			EmployeeDao employeeDao=new EmployeeDao();
			employeeDao.updateEmployee(employee);
			
			
			//������ �Ϸ�Ǹ� EmployeeList�� �������� �̵��Ѵ�.
			response.sendRedirect("./EmployeeList.jsp");
		%>
			
	</body>
</html>