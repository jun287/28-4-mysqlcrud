<!-- 2018. 07. 03 28扁 傍技霖 -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import = "service.TeacherDao" %>
<%@ page import = "service.TeacherAddr" %>

<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
	<%	
		int teacherNo = Integer.parseInt(request.getParameter("no"));
		TeacherDao teacherDao = new TeacherDao();
		TeacherAddr teacherAddr = teacherDao.selectTeacherAddr(teacherNo);
	%>
		<h2>林家 格废</h2>
		<table border="1">
			<tr>
				<th>锅龋</th>
				<th>林家</th>
			</tr>
			<tr>
				<td><%=teacherAddr.getTeacherNo()%></td>
				<td><%=teacherAddr.getTeacherAddrContent() %></td>
			</tr>
		</table>
	</body>
</html>