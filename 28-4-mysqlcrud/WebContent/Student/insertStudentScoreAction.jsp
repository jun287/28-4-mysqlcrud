<!-- 2018. 7. 9(월) 28기 이원상 insertStudentScoreAction.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="StudentDAO.StudentScoreDao"%>
<%
	request.setCharacterEncoding("euc-kr");
	int studentNo = Integer.parseInt(request.getParameter("studentNo"));
	int score = Integer.parseInt(request.getParameter("score"));
	
	StudentScoreDao studentScoreDao = new StudentScoreDao();
	int count = studentScoreDao.countStudentScore(studentNo);
	// DB student_score테이블의 특정 studentNo를 조회하여 행의 수를 정수로 리턴받는 메소드 
	if(count >= 1){
		// 조회된 결과가 DB student_score테이블에 조회한 studentNo가 1개 행 이상 있을 경우
		studentScoreDao.updateStudentScore(studentNo, score);
	}else{
		// 조회된 결과가 DB student_score테이블에 조회한 studentNo가 없을 경우
		studentScoreDao.insertStudentScore(studentNo, score);
	}
	response.sendRedirect(request.getContextPath()+"/Student/studentList.jsp");
%>
