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
						<td>
							<a href="<%= request.getContextPath() %>/Member/insertMember/insertMemberForm.jsp">��� ���</a>
							<a href="<%= request.getContextPath() %>/Member/listMember/memberList.jsp">��� ���</a>
							<a href="<%= request.getContextPath() %>/Member/listMember/memberListAboveAvg.jsp">������� ����̻� ���</a>
						</td>
					</tr>
					<tr>
						<td>2. �̿���</td>
						<td>
							<a href="<%= request.getContextPath() %>/Student/insertStudentForm.jsp">�л� ���</a>
							<a href="<%= request.getContextPath() %>/Student/studentList.jsp">�л� ���</a>
							<a href="<%= request.getContextPath() %>/Student/StudentListAboveAvg.jsp">�л����� ����̻� ���</a>
						</td>
					</tr>
					<tr>
						<td>3. ������</td>
						<td>
							<a href="<%= request.getContextPath() %>/Teacher/insertTeacherForm.jsp">���� ���</a>
							<a href="<%= request.getContextPath() %>/Teacher/teacherList.jsp">���� ���</a>
							<a href="<%= request.getContextPath() %>/Teacher/teacherListAboveAvg.jsp">�������� ����̻� ���</a>
						</td>		
					</tr>
					<tr>
						<td>4. ���μ�</td>
						<td>
							<a href="<%= request.getContextPath() %>/Employee/insertEmployeeForm.jsp">��� ���</a>
							<a href="<%= request.getContextPath() %>/Employee/EmployeeList.jsp">��� ���</a>
							<a href="<%= request.getContextPath() %>/Employee/EmployeeListAboveAvg.jsp">������� ����̻� ���</a>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</body>
</html>