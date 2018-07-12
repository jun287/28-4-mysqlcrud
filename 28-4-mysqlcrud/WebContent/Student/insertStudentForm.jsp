<!-- 28�� �̿��� 2018. 7. 12(��) ��������, insertStudentForm.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath() %>/css/studentForm.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/script/insertStudentForm.js">
</script>
<title>�л� �Է� ��</title>
</head>
<body>
	<div>�л����
		<form action="<%=request.getContextPath()%>/Student/insertStudentAction.jsp" method="post" id="insertStudentForm">
		<!-- placeholder�Ӽ� : ª�� ��Ʈ�� ª�� ���� ��Ÿ�� ����ڰ� �����͸� �Է��ϴµ� ������ �ֱ� ���� ����ϴ� �Ӽ� -->
			<ul>
				<li>
					<label for="name">�̸�</label>	
					<input type="text" id="name" name="studentName" placeholder="�ѱ� 2�� �̻�, 6�� ����" required>
					<span id="namehelper" class="helper"></span>
				</li>
				<li>	
					<label for="birth">�������</label>	
					<input type="date" id="birth" name="studentBirth" required>
					<span id="birthhelper" class="helper"></span>
				</li>
				<li>	
					<label for="age">����</label>	
					<input type="text" id="age" name="studentAge" placeholder="������� �Է½� �ڵ��Է�" readonly>
				</li>
				<li>
					<label for="address">�ּ�</label>	
					<input type="text" id="address" name="studentAddrContent" required>	
				</li>
				<li>	
					<button type="button" id="signup" >���</button>
					<button type="button" id="list">�������</button>
				</li>	
			</ul>	
		</form>
	</div>
</body>
</html>