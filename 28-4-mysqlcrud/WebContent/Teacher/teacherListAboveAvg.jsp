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
		<%
			/*
				점수의 평균값을 화면에 출력하기 위해
				TeacherScoreDao 클래스 객체 생성후 seleteScoreAvg 메서드를 호출하고
				리턴값을 scoreAvg 변수에 대입합니다.(평균값 구함)
				
				평균값 보다 이상인 사람 리스트를 가져오기 위해
				ArrayList<TeacherAndScore> 클래스 객체를 생성하고
				arrayList 객체참조변수에 teacherScoreDao 클래스객체에 selectTeacherListAboveAvg 메서드를 호출하여
				리턴값(주소값)을 대입합니다.
				
				for 반복문으로 arrayList.size 메서드를 호출하여 index 갯수(평균점수 이상인 사람 정보 객체)만큼 반복하고
				그 index를 get메서드로 가져와 주소값을 각각의 클래스 타입 객체참조변수에 대입하고 호출하여
				화면에 출력합니다.
			*/
		
			TeacherScoreDao teacherScoreDao = new TeacherScoreDao();
			int scoreAvg = teacherScoreDao.selectScoreAvg();
			
			ArrayList<TeacherAndScore> arrayList = new ArrayList<TeacherAndScore>();
			arrayList = teacherScoreDao.selectTeacherListAboveAvg();
			
		%>
		<div id="main" align="center">
			<h1>교사점수 목록(평균이상)</h1><br>
			
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