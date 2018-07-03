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
<script type="text/javascript" src="../script/studentList.js">
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
	<form action="./studentList.jsp" method="post" id="selectForm">
		<select id="pagePerRow" name="pagePerRow">
			<option value="3">3개씩 보기</option>
			<option value="5">5개씩 보기</option>
			<option value="7">7개씩 보기</option>
			<option value="10">10개씩 보기</option>
		</select>
		<button type="button" id="pagePerRowButton">보기설정</button>	
	</form>
	<table>
		<tr>
			<th>학생번호</th><th>학생이름</th><th>학생나이</th><th>삭제</th><th>수정</th>
		</tr>
<%
	for(int i=0; i<studentList.size(); i++){
		student = studentList.get(i);
%>
		<tr>
			<td><%=student.getStudentNO() %></td><td><%=student.getStudentName() %></td><td><%=student.getStudentAge() %></td>
			<!-- 네임에 주소 링크걸어 studentList.jsp -->
			<!-- deleteStudentAction.jsp -->
			<!-- updateStudentForm.jsp -> Action -->
		</tr>
<% 		
	}
%>				
	</table>
	<form>
		<div>
			이름 :
			<input type="text" name="searchWord">
			<button type="button" id="nameSearch"></button>
		</div>	
	</form>
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