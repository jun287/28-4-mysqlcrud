<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertEmployeeForm.jsp</title>
		<link rel="stylesheet" type="text/css" href="./style/insertEmployeeForm.css">
	</head>
	<body>
		<!-- ȸ�� ������ -->
		<form action="./insertEmployeeAction.jsp" method="post">
			<div id="name">
				�̸�&nbsp;:&nbsp;
				<input type='text' name="name">
			</div>
			<div id="age">
				����&nbsp;:&nbsp;
				<input type='text' name="age">
			</div>
			<div>
				<input type="submit" value="����" id="ok">
			</div>
		</form>
	</body>
</html>