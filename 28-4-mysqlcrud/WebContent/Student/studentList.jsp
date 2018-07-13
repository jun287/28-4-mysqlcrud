<!-- 2018. 7. 12(목)수정 28기 이원상, studentList.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "StudentDAO.StudentDao" %>
<%@ page import = "StudentDTO.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>학생 리스트</title>
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath() %>/css/main.css">
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath() %>/css/studentList.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/script/studentList.js">
</script>
</head>
<body>
	<div id="main" style="align:center">학생 목록
<%
	request.setCharacterEncoding("euc-kr");
	StudentDao studentDao = new StudentDao();
	Student student = new Student();
	String ageSelect =(request.getParameter("ageSelect") == null) ? "" : request.getParameter("ageSelect");  
	// 삼항연산자 : (조건) ? 참인경우 : 거짓인경우
	// ageSelect변수는 form에서 받아온 ageSelect Parameter의 값이 null이 참인 경우 ""을 대입하고,
	// ageSelect변수는 form에서 받아온 ageSelect Parameter의 값이 null이 거짓인 경우 받아온 parameter값을 대입한다.
	int currentPage = 1;
	if(request.getParameter("currentPage") != null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int pagePerRow = 10;
	if(request.getParameter("pagePerRow") != null){
		pagePerRow = Integer.parseInt(request.getParameter("pagePerRow"));
	}
	String searchWord = "";
	if(request.getParameter("searchWord")!=null){
		searchWord = request.getParameter("searchWord");
	}
	int lastPage = studentDao.countStudent(pagePerRow);
	if(!searchWord.equals("")){
		lastPage = studentDao.countStudent(pagePerRow, searchWord);
	}	
	
	System.out.println(searchWord+"<--searchWord");
	ArrayList<Student> studentList = studentDao.selectStudentByPage(currentPage, pagePerRow, searchWord, ageSelect); 
%>

		<form action="<%=request.getContextPath() %>/Student/studentList.jsp" method="post" id="selectForm">
<%
	if(pagePerRow == 3){
%>
			<select id="pagePerRow" name="pagePerRow">
				<option value="3" selected>3개씩 보기</option>
				<option value="5">5개씩 보기</option>
				<option value="7">7개씩 보기</option>
				<option value="10">10개씩 보기</option>
			</select>
			<input hidden="text" value="<%=ageSelect%>" name="ageSelect">
			<input hidden="text" name="searchWord" value="<%=searchWord%>">
<%		
	}else if(pagePerRow == 5){
%>
			<select id="pagePerRow" name="pagePerRow">
				<option value="3">3개씩 보기</option>
				<option value="5" selected>5개씩 보기</option>
				<option value="7">7개씩 보기</option>
				<option value="10">10개씩 보기</option>
			</select>
			<input hidden="text" value="<%=ageSelect%>" name="ageSelect">
			<input hidden="text" name="searchWord" value="<%=searchWord%>">
<%		
	}else if(pagePerRow == 7){
%>
			<select id="pagePerRow" name="pagePerRow">
				<option value="3">3개씩 보기</option>
				<option value="5">5개씩 보기</option>
				<option value="7" selected>7개씩 보기</option>
				<option value="10">10개씩 보기</option>
			</select>
			<input hidden="text" value="<%=ageSelect%>" name="ageSelect">
			<input hidden="text" name="searchWord" value="<%=searchWord%>">
<%		
	}else if(pagePerRow == 10){
%>
			<select id="pagePerRow" name="pagePerRow">
				<option value="3">3개씩 보기</option>
				<option value="5">5개씩 보기</option>
				<option value="7">7개씩 보기</option>
				<option value="10" selected>10개씩 보기</option>
			</select>
			<input hidden="text" value="<%=ageSelect%>" name="ageSelect">
			<input hidden="text" name="searchWord" value="<%=searchWord%>">
<%
	}
%>	
			<button type="button" id="pagePerRowButton">보기설정</button>	
		</form>
		<form id="oldAgeForm" style="float:left">
			<button type="button" name="oldAge" id="old">높은 나이순</button>
			<input hidden="text" value="oldAge" name="ageSelect">
			<input hidden="text" name="pagePerRow" value="<%=pagePerRow%>">
			<input hidden="text" name="searchWord" value="<%=searchWord%>">
		</form>
		<form id="youngAgeForm" style="float:left">
			<button type="button" name="youngAge" id="young">낮은 나이순</button>
			<input hidden="text" value="youngAge" name="ageSelect">
			<input hidden="text" name="pagePerRow" value="<%=pagePerRow%>">
			<input hidden="text" name="searchWord" value="<%=searchWord%>">
		</form>
<%
	if(ageSelect.equals("")){
%>
			<span></span>
<%
	}else if(ageSelect.equals("oldAge")){
%>
			<span>나이 오름차순 정렬 중</span>
<%		
	}else if(ageSelect.equals("youngAge")){
%>
			<span>나이 내림차순 정렬 중</span>
<%		
	}
%>	
		<table style="clear:both">
			<tr>
				<th>학생번호</th><th>학생이름</th><th>학생나이</th><th>주소입력</th><th>삭제</th><th>수정</th><th>점수입력 및 수정</th><th>점수보기</th>
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
				<td><a href="<%=request.getContextPath() %>/Student/insertStudentScoreForm.jsp?studentNo=<%=student.getStudentNo() %>">점수입력 및 수정</a></td>
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
				<input hidden="text" name="pagePerRow" value="<%=pagePerRow%>">
				<input hidden="text" value="<%=ageSelect%>" name="ageSelect">
			</div>	
		</form>
		<div>
<%
	if(currentPage !=0 && currentPage != 1){
%>
			<a href="<%=request.getContextPath() %>/Student/studentList.jsp?currentPage=<%=currentPage-1 %>&pagePerRow=<%=pagePerRow%>&ageSelect=<%=ageSelect%>&searchWord=<%=searchWord%>">이전</a>
<%
	}for(int p=1; p<=lastPage; p++){
%>		
			<a href="<%=request.getContextPath() %>/Student/studentList.jsp?currentPage=<%=p%>&pagePerRow=<%=pagePerRow%>&ageSelect=<%=ageSelect%>&searchWord=<%=searchWord%>"><%=p%></a>
<%		
	}if(currentPage < lastPage){
%>	
			<a href="<%=request.getContextPath() %>/Student/studentList.jsp?currentPage=<%=currentPage+1 %>&pagePerRow=<%=pagePerRow%>&ageSelect=<%=ageSelect%>&searchWord=<%=searchWord%>">다음</a>
<%
	}
%>		
		</div>
		<a href = "<%=request.getContextPath()%>/index.jsp">메인으로</a>
	</div>	
</body>
</html>