<!-- 2018. 07. 09 28�� ������ -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import = "TeacherDTO.TeacherScore" %>
<%@ page import = "TeacherDAO.TeacherScoreDao" %>

<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertTeacherScoreAction</title>
	</head>
	<body>
		<%
			/*
				������ �޾Ƽ� �����ͺ��̽� ���̺� �Է��ϱ�����
				teacher_no �� score ���� Integer�� ����ȯ�� int Ÿ������ �޽��ϴ�.
				TeacherScore Ŭ���� ��ü�� ������ setter's �޼��带 ȣ���Ͽ� ���� �����մϴ�.
				TeacherScoreDao Ŭ���� ��ü�� �����ϰ� insertTeacherScore �޼��忡 
				teacherScore Ŭ���� ��ü�� �ּҰ��� �����ϰ� ȣ���մϴ�.
				������ �����ͺ��̽� ���̺� �Էµǰ� teacherList.jsp�� �̵��մϴ�.
			*/
		
			request.setCharacterEncoding("EUC-KR");
			int teacherNo = Integer.parseInt(request.getParameter("no"));
			int score =  Integer.parseInt(request.getParameter("score"));
			
			TeacherScore teacherScore = new TeacherScore();
			
			teacherScore.setTeacherNo(teacherNo);
			teacherScore.setScore(score);
			
			System.out.println(teacherScore.getTeacherNo());
			System.out.println(teacherScore.getScore());
			
			TeacherScoreDao teacherScoreDao = new TeacherScoreDao();
			teacherScoreDao.insertTeacherScore(teacherScore);
			
			response.sendRedirect(request.getContextPath()+"/Teacher/teacherList.jsp");
		%>
	</body>
</html>