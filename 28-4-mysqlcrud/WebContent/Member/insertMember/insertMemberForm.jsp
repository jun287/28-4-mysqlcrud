<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!-- 2018.06.26. 28�� ������. -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="./style/insertMemberForm.css">
		<title>member��� ȭ��</title>
	</head>
	<body>
		<div>
			<div>
				<h3>��� ��� </h3>
				<form action="./insertMemberAction.jsp" method="post">
					<ul class="input-group input-group-lg">
						<li class="input-group-addon">
							<ul class="fa fa-user">
								<li class="col1">�̸�: </li>
								<li class="col2"><input type="text" name="member_name"></li>
							</ul>
						</li>
						<li class="input-group input-group-lg">
							<ul class="cols">
								<li class="col1">����: </li>
								<li class="col2"><input type="text" name="member_age"></li>
							</ul>
						</li>
						<li class="input-group input-group-lg">
							<ul class="cols">
								<li class="col1">�ּ��Է� </li>
								<li class="col2"><input type="text" name="memberAddr"></li>
								<li><input type="submit" value="��û"></li>
							</ul>
						</li>
					</ul>
				</form>
			</div>
		</div>
	</body>
</html>