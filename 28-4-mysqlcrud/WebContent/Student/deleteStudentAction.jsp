<!-- 2018. 7. 3(ȭ) �̿��� deleteStudentAction.jsp -->
<%@ page language = "java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "service.StudentDao" %>
<%@ page import = "service.StudentAddrDao" %>
<%@ page import = "service.StudentScoreDao" %>
<%
	request.setCharacterEncoding("euc-kr");
	int studentNo = Integer.parseInt(request.getParameter("studentNo"));
	
	StudentAddrDao studentAddrDao = new StudentAddrDao();
	StudentScoreDao studentScoreDao = new StudentScoreDao();
	// DB���̺� �� Student table�� student_no�÷��� ������ �ڽ�Ű�� ���� �����ϱ� ���Ͽ�
	// studentAddrDao,studentScoreDao��ü���������� �����Ͽ� �����޼ҵ带 ����.
	
	StudentDao studentDao = new StudentDao();	
	studentAddrDao.deleteStudentAddr(studentNo);
	studentScoreDao.deleteStudentScore(studentNo);
	studentDao.deleteStudent(studentNo);
	response.sendRedirect(request.getContextPath()+"/Student/studentList.jsp");
%>