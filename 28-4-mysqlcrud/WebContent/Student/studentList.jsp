<!-- 2018. 7. 2(��) 28�� �̿���, studentList.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "service.StudentDao" %>
<%@ page import = "service.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�л� ����Ʈ</title>
<link rel= "stylesheet" type= "text/css" href="../css/studentList.css">
</head>
<body>
<%
	StudentDao studentDao = new StudentDao();
	Student student = new Student();
	
	int currentPage = 1;
	if(request.getParameter("currentPage") != null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int pagePerRow = 10;
	if(request.getParameter("pagePerRow") != null){
		pagePerRow = Integer.parseInt(request.getParameter("pagePerRow"));
	}
	int totalRow = studentDao.countStudent();
	
	int lastPage = totalRow/pagePerRow;
		if(totalRow%pagePerRow!=0){
			lastPage++;
		}
	ArrayList<Student> studentList = studentDao.selectStudentByPage(currentPage, pagePerRow); 
%>
	<form action="./studentList.jsp" method="post" id="selectPagePerRow">
		<select name="pagePerRow">
			<option value="3">3���� ����</option>
			<option value="5">5���� ����</option>
			<option value="7">7���� ����</option>
			<option value="10">10���� ����</option>
		</select>	
	</form>
	<table>
		<tr>
			<th>�л���ȣ</th><th>�л��̸�</th><th>�л�����</th>
		</tr>
<%
	for(int i=0; i<studentList.size(); i++){
		student = studentList.get(i);
%>
		<tr>
			<td><%=student.getStudentNO() %></td><td><%=student.getStudentName() %></td><td><%=student.getStudentAge() %></td>
		</tr>
<% 		
	}
%>				
	</table>
	<div>
<%
	if(currentPage !=0 && currentPage != 1){
%>
		<a href="./studentList.jsp?currentPage=<%=currentPage-1 %>">����</a>
<%
	}for(int p=1; p<=lastPage; p++){
%>		
		<a href="./studentList.jsp?currentPage=<%=p%>"><%=p%></a>
<%		
	}if(currentPage < lastPage){
%>	
		<a href="./studentList.jsp?currentPage=<%=currentPage+1 %>">����</a>
<%
	}
%>		
	</div>	
</body>
</html>