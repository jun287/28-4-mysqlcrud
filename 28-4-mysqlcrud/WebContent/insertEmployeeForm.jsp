<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertEmployeeForm.jsp</title>
		<link rel="stylesheet" type="text/css" href="./style/insertEmployeeForm.css">
	</head>
	<body>
		<!-- 회원 가입폼 -->
		<form action="./insertEmployeeAction.jsp" method="post">
			<div id="name">
				이름&nbsp;:&nbsp;
				<input type='text' name="name">
			</div>
			<div id="age">
				나이&nbsp;:&nbsp;
				<input type='text' name="age">
			</div>
			<div>
				<input type="submit" value="가입" id="ok">
			</div>
		</form>
	</body>
</html>