<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "service.StudentAddrDao" %>
<%@ page import = "service.StudentAddr" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�л� �ּҸ���Ʈ</title>
<style>
tb, tr, th, td{
	border:1px solid blue;
}
</style>
</head>
<body>
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
</body>
</html>