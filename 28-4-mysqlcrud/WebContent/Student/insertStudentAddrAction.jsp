<!-- 2018. 7. 3(ȭ) �̿��� insertStudentAddrAction.jsp -->
<%@ page language = "java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "service.StudentAddrDao" %>
<%@ page import = "service.StudentAddr" %>
<%
	request.setCharacterEncoding("euc-kr");
	StudentAddr studentAddr = new StudentAddr();
	studentAddr.setStudentNO(Integer.parseInt(request.getParameter("studentNo")));
	studentAddr.setStudentAddrContent(request.getParameter("studentAddrContent"));
	// studentAddr ��ü���������� Form���� �޾ƿ� parameter Setting
	
	StudentAddrDao studentAddrDao = new StudentAddrDao();
	studentAddrDao.insertStudentAddress(studentAddr);
	response.sendRedirect(request.getContextPath()+"/Student/studentAddrList.jsp?studentNo="+studentAddr.getStudentNO());
%>