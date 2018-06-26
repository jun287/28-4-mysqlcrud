<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<!-- 2018. 06. 25 28기 공세준 -->
<%@ page import = "service.TeacherDao" %>

<% request.setCharacterEncoding("EUC-KR"); %> 
<jsp:useBean id="tdb" class="service.Teacher"/>
<jsp:setProperty name="tdb" property="*" />

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertTeacherAction</title>
	</head>
	<body>
	<%
		System.out.println(tdb.getTeacherName());
		System.out.println(tdb.getTeacherAge());
		TeacherDao tdao = new TeacherDao();
		tdao.insertTeacher(tdb);
	 %>
	</body>
</html>