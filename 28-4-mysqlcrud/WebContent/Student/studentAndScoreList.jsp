<!-- 28�� �̿��� 2018. 7. 10(ȭ) studentAndScoreList.jsp  -->
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
<title>�л� ���� ����Ʈ</title>
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath() %>/css/main.css">
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath()%>/css/studentList.css">
</head>
<body>	
	<div id="main" style="align:center">���� ���
		<table>
			<tr>
				<th>�л���ȣ</th><th>�л��̸�</th><th>�л�����</th><th>������ȣ</th><th>����</th>
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
			<a href="<%=request.getContextPath()%>/Student/studentList.jsp">�л� �������</a>
		</div>
	</div>	
</body>
</html>