<!-- 2018. 07. 03 28�� ������ -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import = "TeacherDTO.TeacherAddr" %>
<%@ page import = "TeacherDAO.TeacherDao" %>
<%@ page import = "TeacherDAO.TeacherAddrDao" %>

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
			
			TeacherAddr teacherAddr = new TeacherAddr();
			
			teacherAddr.setTeacherNo(teacherNo);
			teacherAddr.setTeacherAddrContent(teacherAddrContent);
			
			System.out.println(teacherAddr.getTeacherNo());
			System.out.println(teacherAddr.getTeacherAddrContent());
			
			TeacherAddrDao teacherAddrDao = new TeacherAddrDao();
			teacherAddrDao.insertTeacherAddr(teacherAddr);
			
			response.sendRedirect(request.getContextPath()+"/Teacher/teacherList.jsp");
		%>
	</body>
</html>