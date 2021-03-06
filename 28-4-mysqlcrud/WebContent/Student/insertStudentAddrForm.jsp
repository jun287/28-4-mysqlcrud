<!-- 2018. 7. 3(화) 이원상 insertStudentAddrForm.jsp -->
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
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath() %>/css/main.css">
<script>
window.addEventListener("load", function(){
	var insertButton = document.getElementById("insertButton");
	insertButton.addEventListener("click", function(){
		if(document.getElementById("address").value == "" || document.getElementById("address").value == null){
			document.getElementById("addressHelper").innerHTML = "주소를 입력하여 주세요."
		}else{
			document.getElementById("insertForm").submit();
		}
		
	});
});
</script>
<title>학생 주소입력 화면</title>
</head>
<body>
	<div id="main" align="center">
		<h3>주소 등록</h3>
		<form action="<%=request.getContextPath()%>/Student/insertStudentAddrAction.jsp" method="post" id="insertForm">
			<ul id="mem_form">
				<li>
					<ul class="cols">
						<li class="col1"><label for="name">이름 :</label></li>	
						<li class="col2">
							<input type="text" id="name" name="studentName" value="<%=student.getStudentName() %>" readonly>
							<input type="hidden" name="studentNo" value="<%=studentNo%>">
							<span id="namehelper" class="helper"></span>
						</li>
					</ul>
				</li>
				<li>
					<ul class="cols">	
						<li class="col1"><label for="age">나이 :</label></li>
						<li class="col2"><input type="text" id="age" name="studentAge" value="<%=student.getStudentAge() %>" readonly></li>
					</ul>
				</li>
				<li>
					<ul class="cols">
						<li class="col1"><label for="address">주소 :</label></li>
						<li class="col2"><input type="text" id="address" name="studentAddrContent" required><span id="addressHelper"></span></li>
					</ul>
				</li>
				<li>
					<ul class="cols">
						<li class="col1"></li>
						<li class="col2"><input type="button" id="insertButton" value="등록"></li>
					</ul>
				</li>	
			</ul>	
		</form>
	</div>
</body>
</html>