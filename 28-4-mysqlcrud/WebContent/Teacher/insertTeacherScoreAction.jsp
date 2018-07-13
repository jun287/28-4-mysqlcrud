<!-- 2018. 07. 09 28기 공세준 -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import = "TeacherDTO.TeacherScore" %>
<%@ page import = "TeacherDAO.TeacherScoreDao" %>

<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<%
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