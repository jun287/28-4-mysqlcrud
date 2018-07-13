<!-- 2018. 07. 10 28�� ������  -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import = "TeacherDTO.Teacher" %>
<%@ page import = "TeacherDTO.TeacherScore" %>
<%@ page import = "TeacherDAO.TeacherScoreDao" %>
<%@ page import = "TeacherDTO.TeacherAndScore" %>
<%@ page import = "java.util.ArrayList" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>teacherListAboveAvg</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	</head>
	<body>
		<div id="main" align="center">
			<h1>�������� ���(����̻�)</h1><br>
			<%
				TeacherScoreDao teacherScoreDao = new TeacherScoreDao();
				int scoreAvg = teacherScoreDao.selectScoreAvg();
				
				ArrayList<TeacherAndScore> arrayList = new ArrayList<TeacherAndScore>();
				arrayList = teacherScoreDao.selectTeacherListAboveAvg();
				
			%>
			��� : <%=scoreAvg %>��
			<table>
				<thead>
					<tr>
						<th>��ȣ</th>
						<th>���� �̸�</th>
						<th>����</th>
					</tr>
				</thead>
				<tbody>
					<%
						for(int i=0; i<arrayList.size(); i++){
							TeacherAndScore teacherAndScore = arrayList.get(i);
							Teacher teacher = teacherAndScore.getTeacher();
							TeacherScore teacherScore = teacherAndScore.getTeacherScore();
					%>
							<tr>
								<td><%=teacher.getTeacherNo()%></td>
								<td><%=teacher.getTeacherName()%></td>
								<td><%=teacherScore.getScore()%></td>
							</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<a href = "<%=request.getContextPath()%>/index.jsp">�ε�����</a>
		</div>
	</body>
</html>