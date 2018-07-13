<!-- 2018. 07. 13 28�� ������ -->

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
				�� ���� no�� �޾Ƽ� Integer�� ����ȯ���� int Ÿ������ teacherNo�� �����ϰ�
				TeacherDao, TeacherAddrDao, TeacherScoreDao �� import �մϴ�.
				�׸��� ������ Ŭ���� ��ü�� delete�޼��忡 teacherNo �Ű������� �����ϰ� ȣ���մϴ�.
				�޼��尡 ȣ��ǰ� �����ͺ��̽��� ������ ���̺� ���� �������� teacherList.jsp�� �̵��մϴ�.
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