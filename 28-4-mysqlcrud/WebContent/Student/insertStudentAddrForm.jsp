<!-- 2018. 7. 3(ȭ) �̿��� insertStudentAddrForm.jsp -->
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
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath() %>/css/studentForm.css">
<script>
window.addEventListener("load", function(){
	var insertButton = document.getElementById("insertButton");
	insertButton.addEventListener("click", function(){
		if(document.getElementById("address").value == "" || document.getElementById("address").value == null){
			document.getElementById("addressHelper").innerHTML = "�ּҸ� �Է��Ͽ� �ּ���."
		}else{
			document.getElementById("insertForm").submit();
		}
		
	});
});
</script>
<title>�л� �ּ��Է� ȭ��</title>
</head>
<body>
	<div>�ּ� ���
		<form action="<%=request.getContextPath()%>/Student/insertStudentAddrAction.jsp" method="post" id="insertForm">
			<ul>
				<li>
					<label for="name">�̸�</label>	
					<input type="text" id="name" name="studentName" value="<%=student.getStudentName() %>" readonly>
					<input type="hidden" name="studentNo" value="<%=studentNo%>">
					<span id="namehelper" class="helper"></span>
				</li>
				<li>	
					<label for="age">����</label>	
					<input type="text" id="age" name="studentAge" value="<%=student.getStudentAge() %>" readonly>
				</li>
				<li>	
					<label for="address">�ּ�</label>	
					<input type="text" id="address" name="studentAddrContent" required>
					<span id="addressHelper"></span>
				</li>
				<li>	
					<input type="button" id="insertButton" value="���">
				</li>	
			</ul>	
		</form>
	</div>
</body>
</html>