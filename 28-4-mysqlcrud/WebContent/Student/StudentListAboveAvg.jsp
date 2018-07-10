<!-- 28�� �̿��� 2018. 7. 10(ȭ) StudentListAboveAverage.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.StudentScoreDao" %>
<%@ page import="service.StudentAndScore" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath() %>/css/studentList.css">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>����̻� ������ �л� List</title>
</head>
<body>
	<h1>������� �̻� �л� ����Ʈ</h1>
	<%
		StudentScoreDao studentScoreDao = new StudentScoreDao();
		double selectStudentScoreAverage = studentScoreDao.selectStudentScoreAverage();
		// �л� ������� ���� �޼ҵ� �� selectStudentScoreAverage ���� ����
	
		int currentPage = 1;
		if(request.getParameter("currentPage") != null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int pagePerRow = 5;
		if(request.getParameter("pagePerRow") != null){
			pagePerRow = Integer.parseInt(request.getParameter("pagePerRow"));
		}
		int lastPage = studentScoreDao.countStudentAndScoreAboveAverage(pagePerRow);
		System.out.println(lastPage+"<--lastPage");
		// ������� �̻� �л� ��ä���� ������ ���� �� �������� ���� lastPage�� ����(%�� 0�� �ƴҽ� lastPage�� 1����)
		
		
		StudentAndScore studentAndScore = new StudentAndScore();
		
		ArrayList<StudentAndScore> StudentAndScoreAboveAverageList = new ArrayList<>();
		StudentAndScoreAboveAverageList = studentScoreDao.selectStudentAndScoreAboveAverage(currentPage,pagePerRow);
	%>
	<div>
		������� : <%=selectStudentScoreAverage %>
	</div>
	<table>
		<thead>
			<tr>
				<th>�л���ȣ</th><th>�л��̸�</th><th>�л�����</th><th>��������</th><th>����</th>
			</tr>
		</thead>
		<tbody>
	<%	
		for(int i=0; i<StudentAndScoreAboveAverageList.size(); i++){
			studentAndScore = StudentAndScoreAboveAverageList.get(i);
	%>
			<tr>
				<td><%=studentAndScore.getStudent().getStudentNo() %></td>
				<td><%=studentAndScore.getStudent().getStudentName() %></td>
				<td><%=studentAndScore.getStudent().getStudentAge() %></td>
				<td><%=studentAndScore.getStudentScore().getStudentScoreNumber() %></td>
				<td><%=studentAndScore.getStudentScore().getScore()%></td>
			</tr>
		</tbody>
	<%			
		}
	%>	
	</table>
	<div>
	<%
		if(currentPage !=0 && currentPage != 1){
	%>
			<a href="<%=request.getContextPath() %>/Student/StudentListAboveAverage.jsp?currentPage=<%=currentPage-1 %>">����</a>
	<%
		}for(int p=1; p<=lastPage; p++){
	%>		
			<a href="<%=request.getContextPath() %>/Student/StudentListAboveAverage.jsp?currentPage=<%=p%>"><%=p%></a>
	<%		
		}if(currentPage < lastPage){
	%>	
			<a href="<%=request.getContextPath() %>/Student/StudentListAboveAverage.jsp?currentPage=<%=currentPage+1 %>">����</a>
	<%
		}
	%>		
	</div>
</body>
</html>