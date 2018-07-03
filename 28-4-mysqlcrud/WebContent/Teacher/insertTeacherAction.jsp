<!-- 2018. 07. 03 28�� ������ -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import = "service.TeacherDao" %>
<%@ page import = "service.Teacher" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertTeacherAction</title>
	</head>
	<body>
	<%	
		request.setCharacterEncoding("EUC-KR");
		// ������ �޼���� Teacher Ŭ���� ��ü �����ϰ� teacher ��ü���������� �ּҰ� �����մϴ�.
		Teacher teacher = new Teacher();
		// Teacher Ŭ���� ��ü���� setter �޼���� insertTeacherForm.jsp ���� ���� �� �����մϴ�.
		teacher.setTeacherName(request.getParameter("teacherName"));
		teacher.setTeacherAge(Integer.parseInt(request.getParameter("teacherAge")));
	
		System.out.println(teacher.getTeacherName());
		System.out.println(teacher.getTeacherAge());
		
		// ������ �޼���� TeacherDao Ŭ���� ��ü ������ teacherDao ��ü���������� �ּҰ� �����մϴ�.
		TeacherDao teacherDao = new TeacherDao();
		// TeacherDao Ŭ���� ��ü���� insertTeacher �޼��忡 teacher ��ü���������� ��� �ּҰ��� �����ؼ� ȣ���մϴ�.
		teacherDao.insertTeacher(teacher);
		
		response.sendRedirect(request.getContextPath()+"/Teacher/teacherList.jsp");
	 %>
	</body>
</html>