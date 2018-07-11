<!-- 28기 이원상 studentAddrList.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "service.StudentAddrDao" %>
<%@ page import = "service.StudentAddr" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>학생 주소리스트</title>
<style>
tb, tr, th, td{
	border:1px solid blue;
}
</style>
</head>
<body>
	<table>
		<tr>
			<th>주소번호</th><th>학생번호</th><th>주소</th>
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
	<div><a href="<%=request.getContextPath()%>/Student/studentList.jsp">학생 목록으로</a></div>
</body>
</html>