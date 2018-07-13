<!-- 2018. 07. 03 28기 공세준 -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertTeacherForm</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	</head>
	<body>
		<!-- 교사 등록 폼입니다.  -->
		<div id="main" align="center">
			<h3>교사 등록</h3>
			<form action="<%= request.getContextPath()%>/Teacher/insertTeacherAction.jsp" method="post">
				<ul id="mem_form">
					<li>
						<ul class="cols">
							<li class="col1">이름 :</li>
							<!-- 이름은 5자 이내로 받습니다.-->
							<li class="col2"><input type="text" name="teacherName" maxlength="5" autocomplete="off" placeholder="이름 입력(5자 내외)" required></li>
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1">나이 :</li>
							<!-- 나이는 1살부터 100살 이내로 받습니다. -->
							<li class="col2"><input type="number" name="teacherAge" min="1" max="100" maxlength="3" autocomplete="off" required></li>
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1"></li>
							<li class="col2"><input type="submit" value="등록"></li>
						</ul>
					</li>
				</ul>
			</form>
		</div>
	</body>
</html>