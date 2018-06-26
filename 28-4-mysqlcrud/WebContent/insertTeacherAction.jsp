<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<!-- 2018. 06. 25 28기 공세준 -->
<%@ page import = "service.TeacherDao" %>

<% request.setCharacterEncoding("EUC-KR"); %> 
<jsp:useBean id="teacher" class="service.Teacher"/>
<jsp:setProperty name="teacher" property="*" />

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertTeacherAction</title>
	</head>
	<body>
	<%
		System.out.println(teacher.getTeacherName());
		System.out.println(teacher.getTeacherAge());
		TeacherDao teacherDao = new TeacherDao();
		teacherDao.insertTeacher(teacher);
	 %>
	</body>
</html>