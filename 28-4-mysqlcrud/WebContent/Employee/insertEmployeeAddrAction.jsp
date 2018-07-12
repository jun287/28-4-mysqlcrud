<!-- 28기 정민수 2018. 7. 3(화)insertEmployeeAction.jsp -->
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
			
			//EmployeeAddr class data type으로 변수를 선언하고 생성잠 메서드로EmployeeAddr클래스를 통해서 객체를 생성한다음 주소를 대입한다
			EmployeeAddr employeeAddr=new EmployeeAddr();
			
			//employeeAddr주소를 찾아가서 번호와 주소를 셋팅하여라
			employeeAddr.setEmployee_no(no);
			employeeAddr.setEmployee_addr_content(addr);
			
			//EmployeeDao class data type으로 변수를 선언하고 생성자 메서드로 EmployeeDao클래스를 통해서 객체를 생성한다음 주소를 대입한다
			EmployeeDao employeeDao =new EmployeeDao();
			//EmployeeDao class data type으로 변수를 선언하고 생성자 메서드로 EmployeeDao클래스를 통해서 객체를 생성한다음 주소를 대입한다
			employeeDao.insertEmployeeAddr(employeeAddr);
			
			response.sendRedirect(request.getContextPath()+"/Employee/EmployeeAddrList.jsp?no="+no);
		%>
	</body>
</html>