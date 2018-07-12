<!-- 2018. 07. 09. 28기 공세준  -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import = "TeacherDAO.TeacherScoreDao" %>
<%@ page import = "TeacherDTO.Teacher" %>
<%@ page import = "TeacherDTO.TeacherScore" %>
<%@ page import = "TeacherDTO.TeacherAndScore" %>
<%@ page import = "java.util.ArrayList" %>


<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>teacherAndScoreList</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
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
			<form action="<%= request.getContextPath()%>/Teacher/insertTeacherScoreAction.jsp?no=<%=request.getParameter("no")%>" method="post">
				<h2>교사 점수</h2><br>
				<table>
					<tr>
						<th>번호</th>
						<th>이름</th>
						<th>점수</th>
						<th>수정</th>
					</tr>
					<tr>
						<td><%=teacherScore.getTeacherNo()%></td>
						<td><%=teacher.getTeacherName()%></td>
						<td><input type="number" name="score" min="1" max="100" maxlength="3" autocomplete="off" value="<%=teacherScore.getScore()%>" required></td>
						<td><input type="submit" value="수정"></td>
					</tr>
				</table>
			</form>
	<%		
		}
	%>
			
			<a href = "<%=request.getContextPath()%>/Teacher/teacherList.jsp">목록으로</a>
		</div>
	</body>
</html>