<!-- 2018. 07. 03 28�� ������ -->

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
				���� ������ ������Ʈ �ϱ����� ��ȣ�� �̸� ���� �ּҸ� �޾� ������ �����ϰ�
				���Ե� �������� ������ Ŭ���� ��ü ������ ��ü���� setter'�޼���� ������ 
				Dao Ŭ���� ��ü������ ���ο� �޼��忡 ��ü �ּҰ��� �����Ͽ� ȣ���ϸ�
				�����ͺ��̽� ���̺� ������ ������Ʈ �ǰ� teacherList.jsp �� �̵��մϴ�.
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