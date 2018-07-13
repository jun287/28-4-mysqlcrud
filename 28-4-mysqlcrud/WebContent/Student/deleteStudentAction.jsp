<!-- 2018. 7. 3(화) 이원상 deleteStudentAction.jsp -->
<%@ page language = "java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "StudentDAO.StudentDao" %>
<%@ page import = "StudentDAO.StudentAddrDao" %>
<%@ page import = "StudentDAO.StudentScoreDao" %>
<%
	request.setCharacterEncoding("euc-kr");
	int studentNo = Integer.parseInt(request.getParameter("studentNo"));
	
	StudentAddrDao studentAddrDao = new StudentAddrDao();		// StudentAddrDao클래스의 메소드 호출 위한 객체참조변수선언,생성,할당
	StudentScoreDao studentScoreDao = new StudentScoreDao();	// StudentScoreDao클래스 메소드 호출 위한 객체참조변수선언,생성,할당
	StudentDao studentDao = new StudentDao();					// StudentDao클래스 메소드 호출 위한 객체참조변수선언,생성,할당
																// DB테이블 내 Student table에 student_no컬럼을 참조한 자식키를 먼저 삭제하기 위하여
																// studentAddrDao,studentScoreDao객체참조변수를 생성하여 삭제메소드를 실행.
	studentAddrDao.deleteStudentAddr(studentNo);				// 자식테이블의 데이터 먼저 삭제
	studentScoreDao.deleteStudentScore(studentNo);				// 자식테이블의 데이터 먼저 삭제
	studentDao.deleteStudent(studentNo);						// 부모테이블의 데이터 마지막 삭제
	response.sendRedirect(request.getContextPath()+"/Student/studentList.jsp");
%>