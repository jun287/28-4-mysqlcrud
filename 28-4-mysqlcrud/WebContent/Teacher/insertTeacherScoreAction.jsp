<!-- 2018. 07. 09 28기 공세준 -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import = "TeacherDTO.TeacherScore" %>
<%@ page import = "TeacherDAO.TeacherScoreDao" %>

<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertTeacherScoreAction</title>
	</head>
	<body>
		<%
			/*
				점수를 받아서 데이터베이스 테이블에 입력하기위해
				teacher_no 와 score 값을 Integer로 형변환후 int 타입으로 받습니다.
				TeacherScore 클래스 객체를 생성후 setter's 메서드를 호출하여 값을 대입합니다.
				TeacherScoreDao 클래스 객체를 생성하고 insertTeacherScore 메서드에 
				teacherScore 클래스 객체의 주소값을 대입하고 호출합니다.
				점수가 데이터베이스 테이블에 입력되고 teacherList.jsp로 이동합니다.
			*/
		
			request.setCharacterEncoding("EUC-KR");
			int teacherNo = Integer.parseInt(request.getParameter("no"));
			int score =  Integer.parseInt(request.getParameter("score"));
			
			TeacherScore teacherScore = new TeacherScore();
			
			teacherScore.setTeacherNo(teacherNo);
			teacherScore.setScore(score);
			
			System.out.println(teacherScore.getTeacherNo());
			System.out.println(teacherScore.getScore());
			
			TeacherScoreDao teacherScoreDao = new TeacherScoreDao();
			teacherScoreDao.insertTeacherScore(teacherScore);
			
			response.sendRedirect(request.getContextPath()+"/Teacher/teacherList.jsp");
		%>
	</body>
</html>