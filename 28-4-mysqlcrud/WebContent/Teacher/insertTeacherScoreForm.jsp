<!-- 2018. 07. 09 28기 공세준 -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import = "TeacherDAO.TeacherScoreDao" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertTeacherForm</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	</head>
	<body>
		<%
			request.setCharacterEncoding("EUC-KR");
		
			int teacherNo = Integer.parseInt(request.getParameter("no"));
			System.out.println(teacherNo);
			
		%>
		<div id="main" align="center">
			<h3>점수 입력</h3>
			<form action="<%= request.getContextPath()%>/Teacher/insertTeacherScoreAction.jsp?no=<%=request.getParameter("no")%>" method="post">
				<ul id="mem_form">
					<li>
						<ul class="cols">
							<li class="col1">점수 :</li>
							<li class="col2"><input type="number" name="score" min="1" max="100" maxlength="3" autocomplete="off" required></li>
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1"></li>
							<li class="col2">
								<input type="submit" value="입력">	
							</li>
						</ul>
					</li>
				</ul>
			</form>
		</div>
	</body>
</html>