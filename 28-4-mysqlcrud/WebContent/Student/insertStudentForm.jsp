<!-- 28�� �̿��� 2018. 7. 12(��) ��������, insertStudentForm.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath() %>/css/main.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/script/insertStudentForm.js">
</script>
<title>�л� �Է� ��</title>
</head>
<body>
	<div id="main" align="center">
		<h3>�л����</h3>
		<form action="<%=request.getContextPath()%>/Student/insertStudentAction.jsp" method="post" id="insertStudentForm">
		<!-- placeholder�Ӽ� : ª�� ��Ʈ�� ª�� ���� ��Ÿ�� ����ڰ� �����͸� �Է��ϴµ� ������ �ֱ� ���� ����ϴ� �Ӽ� -->
			<ul id="mem_form">
				<li>
					<ul class="cols">
						<li class="col1">�̸� :</li>
						<li class="col2"><input type="text" id="name" name="studentName" placeholder="�ѱ� 2�� �̻�, 6�� ����" required><span id="namehelper" class="helper"></span></li>
						
					</ul>
				</li>
				<li>
					<ul class="cols">	
						<li class="col1"><label for="birth">������� :</label></li>
						<li class="col2"><input type="date" id="birth" name="studentBirth" required><span id="birthhelper" class="helper"></span></li>
					</ul>
				</li>
				<li>
					<ul class="cols">	
						<li class="col1"><label for="age">����</label></li>	
						<li class="col2"><input type="text" id="age" name="studentAge" placeholder="������� �Է½� �ڵ��Է�" readonly></li>
					</ul>
				</li>
				<li>
					<ul class="cols">
						<li class="col1"></li>
						<li class="col2"><button type="button" id="signup" >���</button>&nbsp;&nbsp;<button type="button" id="list">�������</button></li>
					</ul>
				</li>	
			</ul>	
		</form>
		<a href = "<%=request.getContextPath()%>/index.jsp">��������</a>
	</div>
</body>
</html>