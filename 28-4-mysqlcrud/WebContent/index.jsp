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
		<div align="center">
			<h1>28�� 4�� ������Ʈ</h1><br>
			<form>
				<table border="1">
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
								<a href="<%= request.getContextPath() %>/Member/insertMember/insertMemberForm.jsp">ȸ�� ���</a>&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="<%= request.getContextPath() %>/Member/listMember/memberList.jsp">ȸ�� ���</a>&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="<%= request.getContextPath() %>/Member/listMember/memberListAboveAvg.jsp">ȸ������ ���(����̻�)</a>&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>2. �̿���</td>
							<td>
								<a href="<%= request.getContextPath() %>/Student/insertStudentForm.jsp">�л� ���</a>&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="<%= request.getContextPath() %>/Student/studentList.jsp">�л� ���</a>&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="<%= request.getContextPath() %>/Student/StudentListAboveAvg.jsp">�л����� ���(����̻�)</a>&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>3. ������</td>
							<td>
								<a href="<%= request.getContextPath() %>/Teacher/insertTeacherForm.jsp">���� ���</a>&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="<%= request.getContextPath() %>/Teacher/teacherList.jsp">���� ���</a>&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="<%= request.getContextPath() %>/Teacher/teacherListAboveAvg.jsp">�������� ���(����̻�)</a>&nbsp;&nbsp;&nbsp;&nbsp;
							</td>		
						</tr>
						<tr>
							<td>4. ���μ�</td>
							<td>
								<a href="<%= request.getContextPath() %>/Employee/insertEmployeeForm.jsp">���� ���</a>&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="<%= request.getContextPath() %>/Employee/EmployeeList.jsp">���� ���</a>&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="<%= request.getContextPath() %>/Employee/EmployeeListAboveAvg.jsp">�������� ���(����̻�)</a>&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</body>
</html>