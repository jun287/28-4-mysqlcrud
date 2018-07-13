<!-- 2018. 07. 13 28기 공세준 -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import = "TeacherDTO.Teacher" %>
<%@ page import = "TeacherDTO.TeacherAddr" %>
<%@ page import = "TeacherDAO.TeacherDao" %>
<%@ page import = "TeacherDAO.TeacherAddrDao" %>
<%@ page import = "TeacherDAO.TeacherScoreDao" %>

<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>deleteTeacherAction</title>
	</head>
	<body>
		<%
			/*	
				각 행의 no를 받아서 Integer로 형변환한후 int 타입으로 teacherNo에 대입하고
				TeacherDao, TeacherAddrDao, TeacherScoreDao 를 import 합니다.
				그리고 각각의 클래스 객체의 delete메서드에 teacherNo 매개변수를 대입하고 호출합니다.
				메서드가 호출되고 데이터베이스에 각각의 테이블에 행이 지워지고 teacherList.jsp로 이동합니다.
			*/ 
			int teacherNo = Integer.parseInt(request.getParameter("no"));
			TeacherDao teacherDao = new TeacherDao();
			TeacherAddrDao teacherAddrDao = new TeacherAddrDao();
			TeacherScoreDao teacherScoreDao = new TeacherScoreDao();
			
			teacherScoreDao.deleteTeacherScore(teacherNo);
			teacherAddrDao.deleteTeacherAddr(teacherNo);
			teacherDao.deleteTeacher(teacherNo);
			
			response.sendRedirect(request.getContextPath()+"/Teacher/teacherList.jsp");
		%>
	</body>
</html>