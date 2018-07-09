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
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath() %>/css/studentList.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/script/studentList.js">
</script>
</head>
<body>
<%
	request.setCharacterEncoding("euc-kr");
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
	
	String searchWord = "";
	if(request.getParameter("searchWord")!=null){
		searchWord = request.getParameter("searchWord");
	}
	System.out.println(searchWord+"<--searchWord");
	ArrayList<Student> studentList = studentDao.selectStudentByPage(currentPage, pagePerRow, searchWord); 
%>
	<form action="<%=request.getContextPath() %>/Student/studentList.jsp" method="post" id="selectForm">
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
			<th>학생번호</th><th>학생이름</th><th>학생나이</th><th>주소입력</th><th>삭제</th><th>수정</th><th>점수입력</th><th>점수보기</th>
			<!-- 점수는 한번만 입력, join문 연습, 학생의 정보까지  -->
		</tr>
<%
	for(int i=0; i<studentList.size(); i++){
		student = studentList.get(i);
%>
		<tr>
			<td><%=student.getStudentNo() %></td>
			<td><a href="<%=request.getContextPath() %>/Student/studentAddrList.jsp?studentNo=<%=student.getStudentNo() %>"><%=student.getStudentName() %></a></td>
			<td><%=student.getStudentAge() %></td>
			<td><a href="<%=request.getContextPath() %>/Student/insertStudentAddrForm.jsp?studentNo=<%=student.getStudentNo() %>">주소입력</a></td>
			<td><a href="<%=request.getContextPath() %>/Student/deleteStudentAction.jsp?studentNo=<%=student.getStudentNo() %>">삭제</a></td>
			<td><a href="<%=request.getContextPath() %>/Student/updateStudentForm.jsp?studentNo=<%=student.getStudentNo() %>">수정</a></td>
			<td><a href="<%=request.getContextPath() %>/Student/insertStudentScoreForm.jsp?studentNo=<%=student.getStudentNo() %>">점수입력</a></td>
			<td><a href="<%=request.getContextPath() %>/Student/studentAndScoreList.jsp?studentNo=<%=student.getStudentNo() %>">점수보기</a></td>
		</tr>
<% 		
	}
%>				
	</table>
	<form action="<%=request.getContextPath()%>/Student/studentList.jsp" method="post">
		<div>
			이름 :
			<input type="text" name="searchWord">
			<input type="submit" id="nameSearch" value="검색">
		</div>	
	</form>
	<div>
<%
	if(currentPage !=0 && currentPage != 1){
%>
		<a href="<%=request.getContextPath() %>/Student/studentList.jsp?currentPage=<%=currentPage-1 %>">이전</a>
<%
	}for(int p=1; p<=lastPage; p++){
%>		
		<a href="<%=request.getContextPath() %>/Student/studentList.jsp?currentPage=<%=p%>"><%=p%></a>
<%		
	}if(currentPage < lastPage){
%>	
		<a href="<%=request.getContextPath() %>/Student/studentList.jsp?currentPage=<%=currentPage+1 %>">다음</a>
<%
	}
%>		
	</div>	
</body>
</html>