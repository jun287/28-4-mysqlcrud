<!-- 2018. 07. 03 28기 공세준 -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
		<title>insertTeacherForm</title>
	</head>
	<body>
		<div id="main" align="center">
			<h3>주소 등록</h3>
			<form action="<%= request.getContextPath()%>/Teacher/insertTeacherAddrAction.jsp?no=<%=request.getParameter("no")%>" method="post">
				<ul id="mem_form">
					<li>
						<ul class="cols">
							<li class="col1">주소 :</li>
							<li class="col2"><input type="text" name="teacherAddrContent" placeholder="주소 입력" required></li>
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1"></li>
							<li class="col2">
								<input type="submit" value="등록">	
							</li>
						</ul>
					</li>
				</ul>
			</form>
		</div>
	</body>
</html>