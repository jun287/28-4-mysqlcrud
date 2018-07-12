<!-- 2018. 7. 3(화) 이원상 deleteStudentAction.jsp -->
<%@ page language = "java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "StudentDAO.StudentDao" %>
<%@ page import = "StudentDAO.StudentAddrDao" %>
<%@ page import = "StudentDAO.StudentScoreDao" %>
<%
	request.setCharacterEncoding("euc-kr");
	int studentNo = Integer.parseInt(request.getParameter("studentNo"));
	
	StudentAddrDao studentAddrDao = new StudentAddrDao();
	StudentScoreDao studentScoreDao = new StudentScoreDao();
	// DB테이블 내 Student table에 student_no컬럼을 참조한 자식키를 먼저 삭제하기 위하여
	// studentAddrDao,studentScoreDao객체참조변수를 생성하여 삭제메소드를 실행.
	
	StudentDao studentDao = new StudentDao();	
	studentAddrDao.deleteStudentAddr(studentNo);
	studentScoreDao.deleteStudentScore(studentNo);
	studentDao.deleteStudent(studentNo);
	response.sendRedirect(request.getContextPath()+"/Student/studentList.jsp");
%>