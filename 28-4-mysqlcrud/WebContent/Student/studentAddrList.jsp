<!-- 28�� �̿��� studentAddrList.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "StudentDAO.StudentAddrDao" %>
<%@ page import = "StudentDTO.StudentAddr" %>
<!DOCTYPE html>
<html>
<head>
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath() %>/css/main.css">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�л� �ּҸ���Ʈ</title>
<style>
tb, tr, th, td{
	border:1px solid blue;
}
</style>
</head>
<body>
	<div id="main" style="align:center">�ּ� ���
		<table>
			<tr>
				<th>�ּҹ�ȣ</th><th>�л���ȣ</th><th>�ּ�</th>
			<tr>
<%
	int studentNo = Integer.parseInt(request.getParameter("studentNo"));
	StudentAddrDao studentAddrDao = new StudentAddrDao();
	StudentAddr studentAddr = new StudentAddr();
	ArrayList<StudentAddr> studentAddrList = studentAddrDao.selectStudentAddrList(studentNo);
	for(int i = 0; i < studentAddrList.size(); i++){
		studentAddr = studentAddrList.get(i);
%>
			<tr>
				<td><%=studentAddr.getStudentAddrNo() %></td>
				<td><%=studentAddr.getStudentNO() %></td>
				<td><%=studentAddr.getStudentAddrContent() %></td>
			<tr>
<%	
	}
%>
		</table>
		<div>
			<a href="<%=request.getContextPath()%>/Student/studentList.jsp">�л� �������</a>
		</div>
	</div>
</body>
</html>