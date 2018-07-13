<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!-- 2018.06.26. 28기 전재현. -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
		<title>member등록 화면</title>
	</head>
	<body>
		<div id="main" align="center">
			<h3>멤버 등록 </h3>
			<form action="./insertMemberAction.jsp" method="post">
				<ul id="mem_form">
					<li>
						<ul class="cols">
							<li class="col1">이름 :</li>
							<li class="col2"><input type="text" name="member_name"  placeholder="5글자 이내..." maxlength="5"></li>
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1">나이 :</li>
							<li class="col2"><input type="text" name="member_age"  placeholder="3자릿수이내..." maxlength="3"></li>
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
			<a href = "<%=request.getContextPath()%>/index.jsp">메인으로</a>
		</div>
	</body>
</html>