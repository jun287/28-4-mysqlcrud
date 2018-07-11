<!-- 2018. 07. 03 28기 공세준 -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import = "service.Teacher" %>
<%@ page import = "service.TeacherAddr" %>
<%@ page import = "service.TeacherDao" %>

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
			teacherDao.deleteTeacherScore(teacherNo);
			teacherDao.deleteTeacherAddr(teacherNo);
			teacherDao.deleteTeacher(teacherNo);
			
			response.sendRedirect(request.getContextPath()+"/Teacher/teacherList.jsp");
		%>
	</body>
</html>