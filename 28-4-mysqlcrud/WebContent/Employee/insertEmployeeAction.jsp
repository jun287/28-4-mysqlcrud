<!-- 28�� ���μ� 2018. 6. 25(��)insertEmployeeAction.jsp -->
<%@page import="service.EmployeeDao"%>
<%@page import="service.Employee"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>


<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertEmployeeAction.jsp</title>
	</head>
	<body>
		<%
			//request�� �� ������ euckr�� ���ڵ��Ͽ���
			request.setCharacterEncoding("euckr");
			
			//dto�� ��ü�� �����Ѵ�
			Employee employee=new Employee();
			
			//employee�ּҸ� ã�ư��� set�޼��忡 form���� �Ѿ�� �Ķ���� ������ �Ű������� �����ϰ� ȣ���Ѵ�
			employee.setEmployeeName(request.getParameter("name"));
			employee.setEmployeeAge(Integer.parseInt(request.getParameter("age")));
			

			//dao�� ��ü�� �����Ѵ�
			EmployeeDao employeedao=new EmployeeDao();
			
			//employeedao�� �ּҰ��� ã�ư��� �޼��� insertEmployee�Ű������� emplyee�ּҸ� �������� ȣ���Ѵ�.
			employeedao.insertEmployee(employee);
		%>
	</body>
</html>