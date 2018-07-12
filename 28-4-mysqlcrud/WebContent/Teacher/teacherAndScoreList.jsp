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
		<div align="center">
	<%		
		request.setCharacterEncoding("EUC-KR");
		int teacherNo = Integer.parseInt(request.getParameter("no"));
		System.out.println(teacherNo);
		
		
		TeacherScoreDao teacherScoreDao = new TeacherScoreDao();
		TeacherAndScore teacherAndScore = teacherScoreDao.selectTeacherAndScored(teacherNo);
		
		Teacher teacher = teacherAndScore.getTeacher();
		TeacherScore teacherScore = teacherAndScore.getTeacherScore();

		int result = teacherScore.getScore();
		System.out.println(result);
		
		if(result == 0) {
			
			response.sendRedirect(request.getContextPath()+"/Teacher/insertTeacherScoreForm.jsp?no="+teacherNo);
			
		}else{
	%>
		
			<h2>���� ����</h2><br>
			<table border="1">
				<tr>
					<th>��ȣ</th>
					<th>�̸�</th>
					<th>����</th>
				</tr>
				<tr>
					<td><%=teacherScore.getTeacherNo()%></td>
					<td><%=teacher.getTeacherName()%></td>
					<td><%=teacherScore.getScore()%></td>
				</tr>
			</table>
	<%		
		}
	%>
			
			<a href = "<%=request.getContextPath()%>/Teacher/teacherList.jsp">�������</a>
		</div>
	</body>
</html>