<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!-- 2018.06.26 28�� ������ ������. -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="./style/insertMemberForm.css">
		<title>member��� ȭ��</title>
	</head>
	<body>
		<h3>��� ��� </h3>
		<form action="./insertMemberAction.jsp" method="post">
			<ul class="member_list">
				<li>
					<ul class="cols">
						<li class="col1">�̸�: </li>
						<li class="col2"><input type="text" name="member_name"></li>
					</ul>
				</li>
				<li>
					<ul class="cols">
						<li class="col1">����: </li>
						<li class="col2"><input type="text" name="member_age"></li>
						<li><input type="submit" value="��û"></li>
					</ul>
				</li>
			</ul>
		</form>
	</body>
</html>