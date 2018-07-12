<!-- 2018. 07. 03 28기 공세준 -->

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
		String teacherAddress= null;
		
		if(teacherAddr.getTeacherAddrContent() == null){
			teacherAddress = "주소를 입력해주세요.";
		}else{
			teacherAddress = teacherAddr.getTeacherAddrContent();
		}
	%>
		<h2>주소 목록</h2><br>
		<table class="table table-hover">
			<tr>
				<th>번호</th>
				<th>주소</th>
			</tr>
			<tr>
				<td><%=teacherAddr.getTeacherNo()%></td>
				<td><%=teacherAddress%></td>
			</tr>
		</table>
		<a href="<%=request.getContextPath()%>/Teacher/teacherList.jsp">목록으로</a>
	</body>
</html>