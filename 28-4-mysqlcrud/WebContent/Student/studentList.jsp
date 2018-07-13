<!-- 2018. 7. 12(��)���� 28�� �̿���, studentList.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "StudentDAO.StudentDao" %>
<%@ page import = "StudentDTO.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�л� ����Ʈ</title>
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath() %>/css/main.css">
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath() %>/css/studentList.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/script/studentList.js">
</script>
</head>
<body>
	<div id="main" style="align:center">�л� ���
<%
	request.setCharacterEncoding("euc-kr");
	StudentDao studentDao = new StudentDao();
	Student student = new Student();
	String ageSelect =(request.getParameter("ageSelect") == null) ? "" : request.getParameter("ageSelect");  
	// ���׿����� : (����) ? ���ΰ�� : �����ΰ��
	// ageSelect������ form���� �޾ƿ� ageSelect Parameter�� ���� null�� ���� ��� ""�� �����ϰ�,
	// ageSelect������ form���� �޾ƿ� ageSelect Parameter�� ���� null�� ������ ��� �޾ƿ� parameter���� �����Ѵ�.
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
				<option value="3" selected>3���� ����</option>
				<option value="5">5���� ����</option>
				<option value="7">7���� ����</option>
				<option value="10">10���� ����</option>
			</select>
			<input hidden="text" value="<%=ageSelect%>" name="ageSelect">
			<input hidden="text" name="searchWord" value="<%=searchWord%>">
<%		
	}else if(pagePerRow == 5){
%>
			<select id="pagePerRow" name="pagePerRow">
				<option value="3">3���� ����</option>
				<option value="5" selected>5���� ����</option>
				<option value="7">7���� ����</option>
				<option value="10">10���� ����</option>
			</select>
			<input hidden="text" value="<%=ageSelect%>" name="ageSelect">
			<input hidden="text" name="searchWord" value="<%=searchWord%>">
<%		
	}else if(pagePerRow == 7){
%>
			<select id="pagePerRow" name="pagePerRow">
				<option value="3">3���� ����</option>
				<option value="5">5���� ����</option>
				<option value="7" selected>7���� ����</option>
				<option value="10">10���� ����</option>
			</select>
			<input hidden="text" value="<%=ageSelect%>" name="ageSelect">
			<input hidden="text" name="searchWord" value="<%=searchWord%>">
<%		
	}else if(pagePerRow == 10){
%>
			<select id="pagePerRow" name="pagePerRow">
				<option value="3">3���� ����</option>
				<option value="5">5���� ����</option>
				<option value="7">7���� ����</option>
				<option value="10" selected>10���� ����</option>
			</select>
			<input hidden="text" value="<%=ageSelect%>" name="ageSelect">
			<input hidden="text" name="searchWord" value="<%=searchWord%>">
<%
	}
%>	
			<button type="button" id="pagePerRowButton">���⼳��</button>	
		</form>
		<form id="oldAgeForm" style="float:left">
			<button type="button" name="oldAge" id="old">���� ���̼�</button>
			<input hidden="text" value="oldAge" name="ageSelect">
			<input hidden="text" name="pagePerRow" value="<%=pagePerRow%>">
			<input hidden="text" name="searchWord" value="<%=searchWord%>">
		</form>
		<form id="youngAgeForm" style="float:left">
			<button type="button" name="youngAge" id="young">���� ���̼�</button>
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
			<span>���� �������� ���� ��</span>
<%		
	}else if(ageSelect.equals("youngAge")){
%>
			<span>���� �������� ���� ��</span>
<%		
	}
%>	
		<table style="clear:both">
			<tr>
				<th>�л���ȣ</th><th>�л��̸�</th><th>�л�����</th><th>�ּ��Է�</th><th>����</th><th>����</th><th>�����Է� �� ����</th><th>��������</th>
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
				<td><a href="<%=request.getContextPath() %>/Student/insertStudentScoreForm.jsp?studentNo=<%=student.getStudentNo() %>">�����Է� �� ����</a></td>
				<td><a href="<%=request.getContextPath() %>/Student/studentAndScoreList.jsp?studentNo=<%=student.getStudentNo() %>">��������</a></td>
			</tr>
<% 		
	}
%>				
		</table>
		<form action="<%=request.getContextPath()%>/Student/studentList.jsp" method="post">
			<div>
				�̸� :
				<input type="text" name="searchWord">
				<input type="submit" id="nameSearch" value="�˻�">
				<input hidden="text" name="pagePerRow" value="<%=pagePerRow%>">
				<input hidden="text" value="<%=ageSelect%>" name="ageSelect">
			</div>	
		</form>
		<div>
<%
	if(currentPage !=0 && currentPage != 1){
%>
			<a href="<%=request.getContextPath() %>/Student/studentList.jsp?currentPage=<%=currentPage-1 %>&pagePerRow=<%=pagePerRow%>&ageSelect=<%=ageSelect%>&searchWord=<%=searchWord%>">����</a>
<%
	}for(int p=1; p<=lastPage; p++){
%>		
			<a href="<%=request.getContextPath() %>/Student/studentList.jsp?currentPage=<%=p%>&pagePerRow=<%=pagePerRow%>&ageSelect=<%=ageSelect%>&searchWord=<%=searchWord%>"><%=p%></a>
<%		
	}if(currentPage < lastPage){
%>	
			<a href="<%=request.getContextPath() %>/Student/studentList.jsp?currentPage=<%=currentPage+1 %>&pagePerRow=<%=pagePerRow%>&ageSelect=<%=ageSelect%>&searchWord=<%=searchWord%>">����</a>
<%
	}
%>		
		</div>
		<a href = "<%=request.getContextPath()%>/index.jsp">��������</a>
	</div>	
</body>
</html>