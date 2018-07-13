<!-- 28�� �̿��� 2018. 7. 10(ȭ) StudentListAboveAverage.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="StudentDAO.StudentScoreDao" %>
<%@ page import="StudentDTO.StudentAndScore" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath() %>/css/main.css">
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath() %>/css/studentList.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/script/studentList.js">
</script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>����̻� ������ �л� List</title>
</head>
<body>
	<div id="main" style="align:center">�ּ� ���
		<h1>������� �̻� �л� ����Ʈ</h1>
	<%
		StudentScoreDao studentScoreDao = new StudentScoreDao();
		double selectStudentScoreAverage = studentScoreDao.selectStudentScoreAverage();
		// �л� ������� ���� �޼ҵ� �� selectStudentScoreAverage ���� ����
	
		int currentPage = 1;
		if(request.getParameter("currentPage") != null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int pagePerRow = 15;
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
		<form action="<%=request.getContextPath() %>/Student/StudentListAboveAvg.jsp" method="post" id="selectForm">
	<%
	if(pagePerRow == 3){
	%>
			<select id="pagePerRow" name="pagePerRow">
				<option value="3" selected>3���� ����</option>
				<option value="5">5���� ����</option>
				<option value="7">7���� ����</option>
				<option value="10">10���� ����</option>
				<option value="15">15���� ����</option>
			</select>
	<%		
	}else if(pagePerRow == 5){
	%>
			<select id="pagePerRow" name="pagePerRow">
				<option value="3">3���� ����</option>
				<option value="5" selected>5���� ����</option>
				<option value="7">7���� ����</option>
				<option value="10">10���� ����</option>
				<option value="15">15���� ����</option>
			</select>
	<%		
	}else if(pagePerRow == 7){
	%>
			<select id="pagePerRow" name="pagePerRow">
				<option value="3">3���� ����</option>
				<option value="5">5���� ����</option>
				<option value="7" selected>7���� ����</option>
				<option value="10">10���� ����</option>
				<option value="15">15���� ����</option>
			</select>
	<%		
	}else if(pagePerRow == 10){
	%>
			<select id="pagePerRow" name="pagePerRow">
				<option value="3">3���� ����</option>
				<option value="5">5���� ����</option>
				<option value="7">7���� ����</option>
				<option value="10" selected>10���� ����</option>
				<option value="15">15���� ����</option>
			</select>
	<%
	}else if(pagePerRow == 15){
	%>	
			<select id="pagePerRow" name="pagePerRow">
				<option value="3">3���� ����</option>
				<option value="5">5���� ����</option>
				<option value="7">7���� ����</option>
				<option value="10">10���� ����</option>
				<option value="15" selected>15���� ����</option>
			</select>
	<%
	}
	%>	
			<button type="button" id="pagePerRowButton">���⼳��</button>	
		</form>
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
				<a href="<%=request.getContextPath() %>/Student/StudentListAboveAvg.jsp?currentPage=<%=currentPage-1 %>&pagePerRow=<%=pagePerRow%>">����</a>
	<%
		}for(int p=1; p<=lastPage; p++){
	%>		
				<a href="<%=request.getContextPath() %>/Student/StudentListAboveAvg.jsp?currentPage=<%=p%>&pagePerRow=<%=pagePerRow%>"><%=p%></a>
	<%		
		}if(currentPage < lastPage){
	%>	
				<a href="<%=request.getContextPath() %>/Student/StudentListAboveAvg.jsp?currentPage=<%=currentPage+1 %>&pagePerRow=<%=pagePerRow%>">����</a>
	<%
		}
	%>		
		</div>
	</div>
</body>
</html>