<!-- 2018. 7. 3(화) 이원상 updateStudentForm.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "StudentDAO.StudentDao" %>
<%@ page import = "StudentDTO.Student" %>
<%
	request.setCharacterEncoding("euc-kr");
	int studentNo = Integer.parseInt(request.getParameter("studentNo"));
	Student student = new Student();
	student.setStudentNo(studentNo);
	StudentDao studentDao = new StudentDao();
	student = studentDao.selectStudentDetail(student);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>학생정보 수정 화면</title>
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath() %>/css/main.css">
<script>
window.addEventListener("load", function(){
	var updateButton = document.getElementById("updateButton");
	updateButton.addEventListener("click", function(){
		document.getElementById("updateForm").submit();
	});
});
</script>
</head>
<body>
	<div id="main" align="center">
		<div id="updateStudentForm">
			<h3>학생 수정</h3>
			<form action="<%=request.getContextPath()%>/Student/updateStudentAction.jsp" method="post" id="updateForm">
				<ul id="mem_form">
					<li>
						<ul class="cols">
							<li class="col1"><label for="name">이름 :</label></li>
							<li class="col2">
								<input type="text" id="name" name="studentName" value="<%=student.getStudentName() %>">
								<input type="hidden" name="studentNo" value="<%=studentNo%>">
								<span id="namehelper" class="helper"></span>
							</li>
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1"><label for="age">나이 :</label></li>
							<li class="col2"><input type="text" id="age" name="studentAge" value="<%=student.getStudentAge() %>"></li>
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1"></li>	
							<li class="col2"><input type="button" id="updateButton" value="수정"></li>
						</ul>
					</li>	
				</ul>	
			</form>
		</div>
	</div>
</body>
</html>