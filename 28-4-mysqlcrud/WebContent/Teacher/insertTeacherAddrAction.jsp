<!-- 2018. 07. 03 28기 공세준 -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import = "TeacherDTO.TeacherAddr" %>
<%@ page import = "TeacherDAO.TeacherDao" %>
<%@ page import = "TeacherDAO.TeacherAddrDao" %>

<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertTeacherAddrAction</title>
	</head>
	<body>
		<%
			/*
				주소정보를 받아서 데이터베이스 테이블에 저장하기위해 
				teacher_no값과 teacher_addr_content 값을 받아와서 변수에 대입합니다.
				대입된 값을 TeacherAddr 클래스 객체내에 setter's 메서드로 대입시키고
				객체의 주소값을 TeacherAddrDao 클래스 객체내에 insertTeacherAddr 메서드에 대입후 호출합니다.
				주소정보가 저장되고
				teacherList.jsp로 이동합니다.
			*/
			
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