<!-- 2018. 07. 03 28기 공세준 -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import = "TeacherDTO.Teacher" %>
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
			/* 
				교사 정보를 업데이트 하기위해 번호와 이름 나이 주소를 받아 변수에 대입하고
				대입된 변수들을 각각의 클래스 객체 생성후 객체내에 setter'메서드로 대입후 
				Dao 클래스 객체생성후 내부에 메서드에 객체 주소값을 대입하여 호출하면
				데이터베이스 테이블에 정보가 업데이트 되고 teacherList.jsp 로 이동합니다.
			*/
			
			request.setCharacterEncoding("EUC-KR");
			int teacherNo = Integer.parseInt(request.getParameter("no"));
			String teacherName = request.getParameter("teacherName");
			int teacherAge = Integer.parseInt(request.getParameter("teacherAge"));
			String teacherAddrContent = request.getParameter("teacherAddrContent");
			
			TeacherAddr teacherAddr = new TeacherAddr();
			
			teacherAddr.setTeacherNo(teacherNo);
			teacherAddr.setTeacherAddrContent(teacherAddrContent);
			
			Teacher teacher = new Teacher();
			
			teacher.setTeacherNo(teacherNo);
			teacher.setTeacherName(teacherName);
			teacher.setTeacherAge(teacherAge);
			
			TeacherDao teacherDao = new TeacherDao();
			TeacherAddrDao teacherAddrDao = new TeacherAddrDao();
			teacherAddrDao.updateTeacherAddr(teacherAddr);
			teacherDao.updateTeacher(teacher);
			
			response.sendRedirect(request.getContextPath()+"/Teacher/teacherList.jsp");
		%>
	</body>
</html>