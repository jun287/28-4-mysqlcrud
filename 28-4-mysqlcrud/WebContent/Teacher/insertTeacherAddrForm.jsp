<!-- 2018. 07. 03 28�� ������ -->

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
			<h3>�ּ� ���</h3>
			<form action="<%= request.getContextPath()%>/Teacher/insertTeacherAddrAction.jsp?no=<%=request.getParameter("no")%>" method="post">
				<ul id="mem_form">
					<li>
						<ul class="cols">
							<li class="col1">�ּ� :</li>
							<li class="col2"><input type="text" name="teacherAddrContent" placeholder="�ּ� �Է�" required></li>
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1"></li>
							<li class="col2">
								<input type="submit" value="���">	
							</li>
						</ul>
					</li>
				</ul>
			</form>
		</div>
	</body>
</html>