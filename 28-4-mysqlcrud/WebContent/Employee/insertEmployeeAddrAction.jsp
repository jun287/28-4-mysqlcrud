<!-- 28�� ���μ� 2018. 7. 3(ȭ)insertEmployeeAction.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.EmployeeAddr" %>
<%@ page import="service.EmployeeDao" %>
<!DOCTYPE html">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("EUC-KR");
			String no=request.getParameter("no");
			String addr=request.getParameter("addr");
			
			//EmployeeAddr class data type���� ������ �����ϰ� ������ �޼����EmployeeAddrŬ������ ���ؼ� ��ü�� �����Ѵ��� �ּҸ� �����Ѵ�
			EmployeeAddr employeeAddr=new EmployeeAddr();
			
			//employeeAddr�ּҸ� ã�ư��� ��ȣ�� �ּҸ� �����Ͽ���
			employeeAddr.setEmployee_no(no);
			employeeAddr.setEmployee_addr_content(addr);
			
			//EmployeeDao class data type���� ������ �����ϰ� ������ �޼���� EmployeeDaoŬ������ ���ؼ� ��ü�� �����Ѵ��� �ּҸ� �����Ѵ�
			EmployeeDao employeeDao =new EmployeeDao();
			//EmployeeDao class data type���� ������ �����ϰ� ������ �޼���� EmployeeDaoŬ������ ���ؼ� ��ü�� �����Ѵ��� �ּҸ� �����Ѵ�
			employeeDao.insertEmployeeAddr(employeeAddr);
			
			response.sendRedirect(request.getContextPath()+"/Employee/EmployeeAddrList.jsp?no="+no);
		%>
	</body>
</html>