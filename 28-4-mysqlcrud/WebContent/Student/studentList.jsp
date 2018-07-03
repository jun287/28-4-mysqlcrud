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
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath() %>/css/studentList.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/script/studentList.js">
</script>
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
	int lastPage = studentDao.countStudent(pagePerRow);
	
	ArrayList<Student> studentList = studentDao.selectStudentByPage(currentPage, pagePerRow); 
%>
	<form action="<%=request.getContextPath() %>/Student/studentList.jsp" method="post" id="selectForm">
		<select id="pagePerRow" name="pagePerRow">
			<option value="3">3���� ����</option>
			<option value="5">5���� ����</option>
			<option value="7">7���� ����</option>
			<option value="10">10���� ����</option>
		</select>
		<button type="button" id="pagePerRowButton">���⼳��</button>	
	</form>
	<table>
		<tr>
			<th>�л���ȣ</th><th>�л��̸�</th><th>�л�����</th><th>�ּ��Է�</th><th>����</th><th>����</th>
		</tr>
<%
	for(int i=0; i<studentList.size(); i++){
		student = studentList.get(i);
%>
		<tr>
			<td><%=student.getStudentNo() %></td>
			<td><a href="<%=request.getContextPath() %>/Student/studentAddrList.jsp?studentNo=<%=student.getStudentNo() %>"><%=student.getStudentName() %></a></td>
			<td><%=student.getStudentAge() %></td>
			<td><a href="<%=request.getContextPath() %>/Student/insertStudentAddrForm.jsp?studentNo=<%=student.getStudentNo() %>">�ּ��Է�</a></td>
			<td><a href="<%=request.getContextPath() %>/Student/deleteStudentAction.jsp?studentNo=<%=student.getStudentNo() %>">����</a></td>
			<td><a href="<%=request.getContextPath() %>/Student/updateStudentForm.jsp?studentNo=<%=student.getStudentNo() %>">����</a></td>
		</tr>
<% 		
	}
%>				
	</table>
	<form>
		<div>
			�̸� :
			<input type="text" name="searchWord">
			<button type="button" id="nameSearch"></button>
		</div>	
	</form>
	<div>
<%
	if(currentPage !=0 && currentPage != 1){
%>
		<a href="<%=request.getContextPath() %>/Student/studentList.jsp?currentPage=<%=currentPage-1 %>">����</a>
<%
	}for(int p=1; p<=lastPage; p++){
%>		
		<a href="<%=request.getContextPath() %>/Student/studentList.jsp?currentPage=<%=p%>"><%=p%></a>
<%		
	}if(currentPage < lastPage){
%>	
		<a href="<%=request.getContextPath() %>/Student/studentList.jsp?currentPage=<%=currentPage+1 %>">����</a>
<%
	}
%>		
	</div>	
</body>
</html>