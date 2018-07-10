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
		<h1 align="center">28기 4조 프로젝트</h1><br>
		<form>
			<table border="1" width="50%" align ="center">
				<thead>
					<tr>
						<td>이름</td>
						<td>링크</td>
					<tr>
				</thead>
				<tbody>
					<tr>
						<td>1. 전재현</td>
						<td>
							<a href="<%= request.getContextPath() %>/Member/insertMember/insertMemberForm.jsp">멤버 등록</a>
							<a href="<%= request.getContextPath() %>/Member/listMember/memberList.jsp">멤버 목록</a>
							<a href="<%= request.getContextPath() %>/Member/listMember/memberListAboveAvg.jsp">멤버점수 평균이상 목록</a>
						</td>
					</tr>
					<tr>
						<td>2. 이원상</td>
						<td>
							<a href="<%= request.getContextPath() %>/Student/insertStudentForm.jsp">학생 등록</a>
							<a href="<%= request.getContextPath() %>/Student/studentList.jsp">학생 목록</a>
							<a href="<%= request.getContextPath() %>/Student/StudentListAboveAvg.jsp">학생점수 평균이상 목록</a>
						</td>
					</tr>
					<tr>
						<td>3. 공세준</td>
						<td>
							<a href="<%= request.getContextPath() %>/Teacher/insertTeacherForm.jsp">교사 등록</a>
							<a href="<%= request.getContextPath() %>/Teacher/teacherList.jsp">교사 목록</a>
							<a href="<%= request.getContextPath() %>/Teacher/teacherListAboveAvg.jsp">교사점수 평균이상 목록</a>
						</td>		
					</tr>
					<tr>
						<td>4. 정민수</td>
						<td>
							<a href="<%= request.getContextPath() %>/Employee/insertEmployeeForm.jsp">고용 등록</a>
							<a href="<%= request.getContextPath() %>/Employee/EmployeeList.jsp">고용 목록</a>
							<a href="<%= request.getContextPath() %>/Employee/EmployeeListAboveAvg.jsp">고용점수 평균이상 목록</a>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</body>
</html>