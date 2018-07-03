<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<!-- 2018. 06. 25 28�� ������ -->
<%@ page import = "service.TeacherDao" %>
<%@ page import = "service.Teacher" %>
<%@ page import = "service.TeacherAddr" %>

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
		TeacherAddr teacherAddr = new TeacherAddr();
		// Teacher Ŭ���� ��ü���� setter �޼���� insertTeacherForm.jsp ���� ���� �� �����մϴ�.
		teacher.setTeacherName(request.getParameter("teacherName"));
		teacher.setTeacherAge(Integer.parseInt(request.getParameter("teacherAge")));
		teacherAddr.setTeacherAddrContent(request.getParameter("teacherAddrContent"));
	
		System.out.println(teacher.getTeacherName());
		System.out.println(teacher.getTeacherAge());
		System.out.println(teacherAddr.getTeacherAddrContent());
		// ������ �޼���� TeacherDao Ŭ���� ��ü ������ teacherDao ��ü���������� �ּҰ� �����մϴ�.
		TeacherDao teacherDao = new TeacherDao();
		// TeacherDao Ŭ���� ��ü���� insertTeacher �޼��忡 teacher ��ü���������� ��� �ּҰ��� �����ؼ� ȣ���մϴ�.
		teacherDao.insertTeacher(teacher);
		
		response.sendRedirect(request.getContextPath()+"/Teacher/teacherList.jsp");
	 %>
	</body>
</html>