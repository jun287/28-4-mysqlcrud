<!-- 2018. 07. 03 28�� ������ -->

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
			teacherAddress = "�ּҸ� �Է����ּ���.";
		}else{
			teacherAddress = teacherAddr.getTeacherAddrContent();
		}
	%>
		<h2>�ּ� ���</h2><br>
		<table class="table table-hover">
			<tr>
				<th>��ȣ</th>
				<th>�ּ�</th>
			</tr>
			<tr>
				<td><%=teacherAddr.getTeacherNo()%></td>
				<td><%=teacherAddress%></td>
			</tr>
		</table>
		<a href="<%=request.getContextPath()%>/Teacher/teacherList.jsp">�������</a>
	</body>
</html>