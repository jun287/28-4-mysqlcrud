<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

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
			request.setCharacterEncoding("EUC-KR");
			int teacherNo = Integer.parseInt(request.getParameter("no"));
			String teacherAddrContent = request.getParameter("teacherAddrContent");
			
			System.out.println(teacherNo);
			System.out.println(teacherAddrContent);
			TeacherDao teacherDao = new TeacherDao();
			teacherDao.insertTeacherAddr(teacherNo,teacherAddrContent);
			
			response.sendRedirect(request.getContextPath()+"/Teacher/teacherList.jsp");
		%>
	</body>
</html>