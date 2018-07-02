<!-- 2018. 7. 2(월) 28기 이원상, studentList.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "service.StudentDao" %>
<%@ page import = "service.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>학생 리스트</title>
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
			<option value="3">3개씩 보기</option>
			<option value="5">5개씩 보기</option>
			<option value="7">7개씩 보기</option>
			<option value="10">10개씩 보기</option>
		</select>	
	</form>
	<table>
		<tr>
			<th>학생번호</th><th>학생이름</th><th>학생나이</th>
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
		<a href="./studentList.jsp?currentPage=<%=currentPage-1 %>">이전</a>
<%
	}for(int p=1; p<=lastPage; p++){
%>		
		<a href="./studentList.jsp?currentPage=<%=p%>"><%=p%></a>
<%		
	}if(currentPage < lastPage){
%>	
		<a href="./studentList.jsp?currentPage=<%=currentPage+1 %>">다음</a>
<%
	}
%>		
	</div>	
</body>
</html>