<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!-- 2018.06.26. 28�� ������. -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="../../css/member/insertMemberForm.css">
		<title>member��� ȭ��</title>
	</head>
	<body>
		<div id="wrap">
			<br><br><br>
			<h3>��� ��� </h3>
			<form action="./insertMemberAction.jsp" method="post">
				<table>
					<tr>
						<td id="title">�̸� </td>
						<td><input type="text" name="member_name" maxlength="20"></td>
					</tr>
					<tr>
						<td id="title">���� </td>
						<td><input type="text" name="member_age" maxlength="3"></td>
					</tr>
				</table>
				<br><input type="submit" value="���"> &nbsp;<input type="button" value="���">
			</form>
		</div>
	</body>
</html>