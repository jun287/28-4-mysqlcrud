<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!-- 2018.07.09. 28�� ������ -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Score�Է� ȭ��</title>
	</head>
	<body>
		<div>
			<div>
				<h3>���� ��� </h3>
				<form action="./insertMemberScoreAction.jsp" method="post">
					<ul class="input-group input-group-lg">
						<li class="input-group-addon">
							<ul class="fa fa-user">
								<li class="col1">����: </li>
								<li class="col2"><input type="text" name="memberScore"></li>
								<li><input type="submit" value="�Է�"></li>
							</ul>
						</li>
					</ul>
				</form>
			</div>
		</div>
	</body>
</html>