<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<style>

		
		</style>
	</head>
	<body>
		<h1 align="center">28�� 4�� ������Ʈ</h1><br>
		<form>
			<table border="1" width="50%" align ="center">
				<thead>
					<tr>
						<td>�̸�</td>
						<td>��ũ</td>
					<tr>
				</thead>
				<tbody>
					<tr>
						<td>1. ������</td>
						<td><a href="<%= request.getContextPath() %>/insertMemberForm.jsp">��� ���</a></td>
					<tr>
					<tr>
						<td>2. �̿���</td>
						<td><a href="<%= request.getContextPath() %>/insertStudentForm.jsp">�л� ���</a></td>
					<tr>
					<tr>
						<td>3. ������</td>
						<td>
							<a href="<%= request.getContextPath() %>/Teacher/insertTeacherForm.jsp">���� ���</a><br>
							<a href="<%= request.getContextPath() %>/Teacher/teacherList.jsp">���� ���</a>
						</td>		
					<tr>
					<tr>
						<td>4. ���μ�</td>
						<td><a href="<%= request.getContextPath() %>/insertEmployeeForm.jsp">��� ���</a></td>
					<tr>
				</tbody>
			</table>
		</form>
	</body>
</html>