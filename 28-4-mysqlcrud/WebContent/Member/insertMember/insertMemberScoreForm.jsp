<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!-- 2018.07.09. 28기 전재현 -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Score입력 화면</title>
	</head>
	<body>
		<div>
			<div>
				<h3>점수 등록 </h3>
				<form action="./insertMemberScoreAction.jsp" method="post">
					<ul class="input-group input-group-lg">
						<li class="input-group-addon">
							<ul class="fa fa-user">
								<li class="col1">점수: </li>
								<li class="col2"><input type="text" name="memberScore"></li>
								<li><input type="submit" value="입력"></li>
							</ul>
						</li>
					</ul>
				</form>
			</div>
		</div>
	</body>
</html>