<!-- 28�� ���μ� 2018. 6. 25(��)insertEmployee.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertEmployeeForm.jsp</title>
		<link rel="stylesheet" type="text/css" href="../style/insertEmployeeForm.css">
		<script>
			//�ε����� �� ����ǰ��ϱ�
						
			//�̸�,����,���Ժ�����������
			
			//����Ű������ �ƴҶ� �� ��ȯ�ϱ�
			
			//���̰� ���ڰ� �ƴҶ� ���ֱ�
			
		</script>
	</head>
	<body>
		<!-- ȸ�� ������ -->
		<form action="./insertEmployeeAction.jsp" method="post" >
			<div id="name">
				
				<input type='text' name="name" placeholder="�̸�" id=namehelper>
			</div>
			<div id="age">
				
				<input type='text' name="age" placeholder="����" id=agehelper>
				
			</div>
			<div>
				<input type="submit" value="����" id="ok">
			</div>
		</form>
	</body>
</html>