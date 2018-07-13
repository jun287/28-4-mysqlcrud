<!-- 2018. 07. 10 28기 공세준  -->

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
			<h1>교사점수 목록(평균이상)</h1><br>
			<%
				TeacherScoreDao teacherScoreDao = new TeacherScoreDao();
				int scoreAvg = teacherScoreDao.selectScoreAvg();
				
				ArrayList<TeacherAndScore> arrayList = new ArrayList<TeacherAndScore>();
				arrayList = teacherScoreDao.selectTeacherListAboveAvg();
				
			%>
			평균 : <%=scoreAvg %>점
			<table>
				<thead>
					<tr>
						<th>번호</th>
						<th>교사 이름</th>
						<th>점수</th>
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
			<a href = "<%=request.getContextPath()%>/index.jsp">인덱스로</a>
		</div>
	</body>
</html>