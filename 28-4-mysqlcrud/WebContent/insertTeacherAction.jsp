<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import = "service.TeacherDao" %>

<% request.setCharacterEncoding("EUC-KR"); %> 
<jsp:useBean id="tb" class="service.Teacher"/>
<jsp:useBean id="tab" class="service.TeacherAddr"/>
<jsp:setProperty name="tb" property="*" />
<jsp:setProperty name="tab" property="*" />

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertTeacherAction</title>
	</head>
	<body>
	<%
		System.out.println(tb.getTeacherName());
		System.out.println(tb.getTeacherAge());
		System.out.println(tab.getTeacherAddrContent());
		TeacherDao tdao = new TeacherDao();
		tdao.getCon();
	 %>
	</body>
</html>