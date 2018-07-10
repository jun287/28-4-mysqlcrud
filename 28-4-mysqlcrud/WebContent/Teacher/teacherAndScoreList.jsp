<!-- 2018. 07. 09. 28�� ������  -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import = "service.TeacherScoreDao" %>
<%@ page import = "service.Teacher" %>
<%@ page import = "service.TeacherScore" %>
<%@ page import = "service.TeacherAndScore" %>
<%@ page import = "java.util.ArrayList" %>


<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>teacherAndScoreList</title>
	</head>
	<body>
	<%		
		request.setCharacterEncoding("EUC-KR");
		int teacherNo = Integer.parseInt(request.getParameter("no"));
		System.out.println(teacherNo);
		
		TeacherScoreDao teacherScoreDao = new TeacherScoreDao();
		ArrayList<TeacherAndScore> arrayList = teacherScoreDao.selectTeacherAndScored(teacherNo);	
	%>
		<h2>���� ����</h2>	
			<table border="1">
				<tr>
					<th>��ȣ</th>
					<th>�̸�</th>
					<th>����</th>
					<th>����</th>
				</tr>
				<%
					for(int i=0;i<arrayList.size();i++){
						TeacherAndScore teacherAndScore = arrayList.get(i);
						Teacher teacher = teacherAndScore.getTeacher();
						TeacherScore teacherScore = teacherAndScore.getTeacherScore();
						System.out.println(teacherScore.getTeacherNo());
						System.out.println(teacher.getTeacherName());
						System.out.println(teacher.getTeacherAge());
						System.out.println(teacherScore.getScore());
						
				%>
						<tr>
							<td><%=teacherScore.getTeacherNo()%></td>
							<td><%=teacher.getTeacherName()%></td>
							<td><%=teacher.getTeacherAge()%></td>
							<td><%=teacherScore.getScore()%></td>
						</tr>
				<%
					}
				%>
			</table>
	</body>
</html>