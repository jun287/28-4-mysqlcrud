<!-- 28기 정민수 2018. 6. 25(월)insertEmployee.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertEmployeeForm.jsp</title>
		<link rel="stylesheet" type="text/css" href="../style/insertEmployeeForm.css">
	</head>
	<body>
		<!-- 회원 가입폼 -->
		<form action="./insertEmployeeAction.jsp" method="post" >
			<div id="name">
				
				<input type='text' name="name" placeholder="이름(5자 내외)" id=namehelper maxlength="5" autocomplete="off"  required>
			</div>
			<div id="age">
				
				<input type='number' name="age" placeholder="나이" id=agehelper min="1" max="100" maxlength="3" autocomplete="off" width="80px" required >
				
			</div>
			<div>
				<input type="submit" value="가입" id="ok">
			</div>
		</form>
	</body>
</html>