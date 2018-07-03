<!-- 2018. 7. 3(화) 이원상 deleteStudentAction.jsp -->
<%@ page language = "java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "service.StudentDao" %>
<%@ page import = "service.StudentAddrDao" %>
<%
	request.setCharacterEncoding("euc-kr");
	int studentNo = Integer.parseInt(request.getParameter("studentNo"));
	StudentAddrDao studentAddrDao = new StudentAddrDao();
	StudentDao studentDao = new StudentDao();	
	studentAddrDao.deleteStudentAddr(studentNo);
	studentDao.deleteStudent(studentNo);
	response.sendRedirect(request.getContextPath()+"/Student/studentList.jsp");
%>