<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.StudentAndScore" %>
<%@ page import="service.StudentScore" %>
<%@ page import="service.Student" %>
<%@ page import="service.StudentScoreDao" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>학생 점수 리스트</title>
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath()%>/css/studentList.css">
</head>
<body>	
	<table>
		<tr>
			<th>학생번호</th><th>학생이름</th><th>학생나이</th><th>점수번호</th><th>점수</th>
			<!-- 점수는 한번만 입력, join문 연습, 학생의 정보까지  -->
		</tr>
<%
	request.setCharacterEncoding("euc-kr");
	StudentScoreDao studentScoreDao = new StudentScoreDao();
	StudentAndScore studentAndScore = new StudentAndScore();
	ArrayList<StudentAndScore> studentAndScoreList = new ArrayList<StudentAndScore>();
	studentAndScoreList = studentScoreDao.selectStudentAndScore();
	for(int i=0; i<studentAndScoreList.size(); i++){
		studentAndScore=studentAndScoreList.get(i);
%>
		<tr>
			<td><%=studentAndScore.getStudent().getStudentNo()%></td>
			<td><a href="<%=request.getContextPath() %>/Student/studentAddrList.jsp?studentNo=<%=studentAndScore.getStudent().getStudentNo()%>"><%=studentAndScore.getStudent().getStudentName()%></a></td>
			<td><%=studentAndScore.getStudent().getStudentAge()%></td>
			<td><%=studentAndScore.getStudentScore().getStudentScoreNumber()%></td>
			<td><%=studentAndScore.getStudentScore().getScore()%></td>
		</tr>
<%		
	}
%>			
	</table>
	
</body>
</html>