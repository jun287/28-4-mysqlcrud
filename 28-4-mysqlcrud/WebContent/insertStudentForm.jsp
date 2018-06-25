<!-- 28기 이원상 2018. 6. 25(월)insertStudentForm.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<style>
div{
	border:solid 1px #212121;
	padding:10px 0 0 10px;
}
ul{
	list-style-type:none;	
}
input{
	margin-bottom:15px;
}
input#address{
	width:650px;
}
</style>
<title>학생 입력 폼</title>
</head>
<body>
	<div id="insertStudentForm">학생등록
		<form action="<%=request.getContextPath()%>/insertStudentAction.jsp" method="post">
			<ul>
				<li>
					<label for="name">이름</label>	
					<input type="text" id="name" name="studentName">
				</li>
				<li>	
					<label for="age">나이</label>	
					<input type="text" id="age" name="studentAge">
				</li>
				<li>
					<label for="address">주소</label>	
					<input type="text" id="address" name="studentAddrContent">	
				</li>
				<li>	
					<input type="submit" value="등록">
				</li>	
			</ul>	
		</form>
	</div>
</body>
</html>