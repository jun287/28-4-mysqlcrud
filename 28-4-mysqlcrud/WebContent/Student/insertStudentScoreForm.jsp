<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.StudentScore" %>
<%@ page import="service.StudentDao" %>
<%@ page import="service.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>점수입력 화면</title>
</head>
<body>
<%
	request.setCharacterEncoding("euc-kr");
	int studentNo = Integer.parseInt(request.getParameter("studentNo")); 
	StudentDao studentDao = new StudentDao();
	Student student = new Student();
	student.setStudentNo(studentNo);
	studentDao.selectStudentDetail(student);
%>
	<form action="<%=request.getContextPath()%>/Student/insertStudentScoreAction.jsp" method="post">
		<label for="name">이름</label>	
		<input type="text" id="name" name="studentName" value="<%=student.getStudentName() %>" readonly>
		<input type="hidden" id="studentNo" name="studentNo" value="<%=student.getStudentNo() %>">
		<label for="score">점수</label>
		<input type="text" id="score" name="score">
		<input type="submit" value="등록">
	</form>	
</body>
</html>