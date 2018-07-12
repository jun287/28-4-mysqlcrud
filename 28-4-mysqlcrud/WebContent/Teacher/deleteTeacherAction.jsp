<!-- 2018. 07. 03 28�� ������ -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import = "TeacherDTO.Teacher" %>
<%@ page import = "TeacherDTO.TeacherAddr" %>
<%@ page import = "TeacherDAO.TeacherDao" %>
<%@ page import = "TeacherDAO.TeacherScoreDao" %>

<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<%
			int teacherNo = Integer.parseInt(request.getParameter("no"));
			TeacherDao teacherDao = new TeacherDao();
			TeacherScoreDao teacherScoreDao = new TeacherScoreDao();
			
			teacherScoreDao.deleteTeacherScore(teacherNo);
			teacherDao.deleteTeacherAddr(teacherNo);
			teacherDao.deleteTeacher(teacherNo);
			
			response.sendRedirect(request.getContextPath()+"/Teacher/teacherList.jsp");
		%>
	</body>
</html>