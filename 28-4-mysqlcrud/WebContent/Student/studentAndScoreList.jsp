<!-- 28기 이원상 2018. 7. 10(화) studentAndScoreList.jsp  -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="StudentDTO.StudentAndScore" %>
<%@ page import="StudentDTO.StudentScore" %>
<%@ page import="StudentDTO.Student" %>
<%@ page import="StudentDAO.StudentScoreDao" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>학생 점수 리스트</title>
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath() %>/css/main.css">
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath()%>/css/studentList.css">
</head>
<body>	
	<div id="main" style="align:center">점수 목록
		<table>
			<tr>
				<th>학생번호</th><th>학생이름</th><th>학생나이</th><th>점수번호</th><th>점수</th>
			</tr>
<%
	request.setCharacterEncoding("euc-kr");
	int studentNo = Integer.parseInt(request.getParameter("studentNo"));
	StudentScoreDao studentScoreDao = new StudentScoreDao();
	StudentAndScore studentAndScore = new StudentAndScore();
	ArrayList<StudentAndScore> studentAndScoreList = new ArrayList<StudentAndScore>();
	studentAndScoreList = studentScoreDao.selectStudentAndScore(studentNo);
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
		<div>
			<a href="<%=request.getContextPath()%>/Student/studentList.jsp">학생 목록으로</a>
		</div>
	</div>	
</body>
</html>