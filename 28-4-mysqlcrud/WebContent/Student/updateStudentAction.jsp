<!-- 2018. 7. 3(화) 이원상 updateStudentAction.jsp -->
<%@ page language = "java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "StudentDAO.StudentDao" %>
<%@ page import = "StudentDTO.Student" %>
<%
	request.setCharacterEncoding("euc-kr");
	String studentName = request.getParameter("studentName");
	int studentAge = Integer.parseInt(request.getParameter("studentAge"));
	int studentNo = Integer.parseInt(request.getParameter("studentNo"));
	StudentDao studentDao = new StudentDao();
	Student student = new Student();
	student.setStudentName(studentName);
	student.setStudentAge(studentAge);
	student.setStudentNo(studentNo);
	studentDao.updateStudent(student);
	response.sendRedirect(request.getContextPath()+"/Student/studentList.jsp");
%>