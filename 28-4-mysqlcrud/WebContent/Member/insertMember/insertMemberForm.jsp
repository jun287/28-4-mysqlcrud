<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!-- 2018.06.26. 28�� ������. -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
		<title>member��� ȭ��</title>
	</head>
	<body>
		<div id="main" align="center">
			<h3>��� ��� </h3>
			<form action="./insertMemberAction.jsp" method="post">
				<ul id="mem_form">
					<li>
						<ul class="cols">
							<li class="col1">�̸� :</li>
							<li class="col2"><input type="text" name="member_name"  placeholder="5���� �̳�..." maxlength="5"></li>
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1">���� :</li>
							<li class="col2"><input type="text" name="member_age"  placeholder="3�ڸ����̳�..." maxlength="3"></li>
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1"></li>
							<li class="col2"><input type="submit" value="���"></li>
						</ul>
					</li>				
				</ul>
			</form>
			<a href = "<%=request.getContextPath()%>/index.jsp">��������</a>
		</div>
	</body>
</html>