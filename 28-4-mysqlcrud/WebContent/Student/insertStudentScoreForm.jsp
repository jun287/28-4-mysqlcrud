<!-- 28�� �̿��� 2018. 7. 10(ȭ) insertStudentScoreForm.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="StudentDTO.StudentScore" %>
<%@ page import="StudentDAO.StudentDao" %>
<%@ page import="StudentDTO.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath() %>/css/main.css">
<title>�����Է� ȭ��</title>
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
	<div id="main" style="align:center">
		<form action="<%=request.getContextPath()%>/Student/insertStudentScoreAction.jsp" method="post">
			<label for="name">�̸�</label>	
			<input type="text" id="name" name="studentName" value="<%=student.getStudentName() %>" readonly>
			<input type="hidden" id="studentNo" name="studentNo" value="<%=student.getStudentNo() %>">
			<label for="score">����</label>
			<input type="text" id="score" name="score" required>
			<input type="submit" value="���">
		</form>
	</div>		
</body>
</html>