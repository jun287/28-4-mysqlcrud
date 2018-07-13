<!-- 2018. 07. 03 28�� ������ -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertTeacherForm</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	</head>
	<body>
		<!-- ���� ��� ���Դϴ�.  -->
		<div id="main" align="center">
			<h3>���� ���</h3>
			<form action="<%= request.getContextPath()%>/Teacher/insertTeacherAction.jsp" method="post">
				<ul id="mem_form">
					<li>
						<ul class="cols">
							<li class="col1">�̸� :</li>
							<!-- �̸��� 5�� �̳��� �޽��ϴ�.-->
							<li class="col2"><input type="text" name="teacherName" maxlength="5" autocomplete="off" placeholder="�̸� �Է�(5�� ����)" required></li>
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1">���� :</li>
							<!-- ���̴� 1����� 100�� �̳��� �޽��ϴ�. -->
							<li class="col2"><input type="number" name="teacherAge" min="1" max="100" maxlength="3" autocomplete="off" required></li>
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
		</div>
	</body>
</html>