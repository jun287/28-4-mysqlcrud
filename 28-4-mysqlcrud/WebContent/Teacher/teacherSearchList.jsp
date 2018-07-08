<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import = "service.TeacherDao" %>
<%@ page import = "service.Teacher" %>
<%@ page import = "java.util.ArrayList" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("EUC-KR");
			String searchSelect = request.getParameter("searchSelect");
			String searchWord = request.getParameter("searchWord");
			System.out.println(searchSelect);
			System.out.println(searchWord);
			
			TeacherDao teacherDao = new TeacherDao();
			ArrayList<Teacher> arrayListTeacher = new ArrayList<Teacher>();
			arrayListTeacher = teacherDao.searchTeacher(searchSelect, searchWord);
		%>
			<h2>���� ���</h2>
			<table border="1">
				<tr>
					<th>��ȣ</th>
					<th>�̸�</th>
					<th>����</th>
					<th>�ּ��Է�</th>
					<th>����</th>
					<th>����</th>
				</tr>
		<%	
			for(int i=0;i<arrayListTeacher.size(); i++){
				Teacher teacher = arrayListTeacher.get(i);
		%>
				<tr>
					<td><%=teacher.getTeacherNo()%></td> <!-- teacher ��ü����  getTeacherNo()�޼��带 ȣ���Ͽ� �����͸� �����ɴϴ�. -->
					<td><a href ="<%= request.getContextPath() %>/Teacher/teacherAddrList.jsp?no=<%=teacher.getTeacherNo()%>"><%=teacher.getTeacherName()%></a></td> <!-- teacher ��ü����  getTeacherName()�޼��带 ȣ���Ͽ� �����͸� �����ɴϴ�. -->
					<td><%=teacher.getTeacherAge()%></td> <!-- teacher ��ü����  getTeacherAge()�޼��带 ȣ���Ͽ� �����͸� �����ɴϴ�. -->
					<td><a href ="<%= request.getContextPath() %>/Teacher/insertTeacherAddrForm.jsp?no=<%=teacher.getTeacherNo()%>">�ּ��Է�</a></td>
					<td><a href ="<%= request.getContextPath() %>/Teacher/deleteTeacherAction.jsp?no=<%=teacher.getTeacherNo()%>">����</a></td>
					<td><a href ="<%= request.getContextPath() %>/Teacher/updateTeacherForm.jsp?no=<%=teacher.getTeacherNo()%>">����</a></td>
					<!-- updateTeacherForm.jsp -> updateTeacherAction.jsp -->
				</tr>
		<%
			}
		%>
			</table>
	</body>
</html>